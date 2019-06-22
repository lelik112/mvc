package net.tcheltsou.springmvclearning.web.controller;

import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.service.BankService;
import net.tcheltsou.springmvclearning.service.EventService;
import net.tcheltsou.springmvclearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/events")
public class EventsController {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private BankService bankService;

	@GetMapping
	public String handleEvents(Model model) {
		model.addAttribute("sentence", "Choose events to buy tickets:");
		model.addAttribute("events", eventService.getEvents());
		return "events";
	}

	@GetMapping("/{id}/tickets")
	public String handleTicketsOfEvent(Model model, @PathVariable long id) {
		model.addAttribute("sentence", "Choose ticket to buy:");
		model.addAttribute("event", eventService.getEventWithAvailableTicketsByEventId(id));
		return "tickets";
	}

	@GetMapping("/{eventId}/ticket/{id}")
	public String handlBuyTicket(Model model, @PathVariable Long eventId, @PathVariable Long id,
								 @SessionAttribute User user, HttpSession session) {
		model.addAttribute("ticket", userService.buyTicket(id, user));
		model.addAttribute("sentence", "You have bought ticket. Choose another one:");
		model.addAttribute("event", eventService.getEventWithAvailableTicketsByEventId(eventId));
		if (session.getAttribute("bank") != null) {
			session.setAttribute("bank", bankService.getBank());
		}
		return "tickets";
	}



}

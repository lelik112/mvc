package net.tcheltsou.springmvclearning.web.controller;

import net.tcheltsou.springmvclearning.entity.Ticket;
import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.service.BankService;
import net.tcheltsou.springmvclearning.service.EventService;
import net.tcheltsou.springmvclearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/rest")
public class RestTicketController {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private BankService bankService;

	@ResponseBody
	@GetMapping("/event/${eventId}/ticket/${id}")
	public Ticket handlBuyTicket(Model model, @PathVariable Long eventId, @PathVariable Long id,
								 @SessionAttribute User user, HttpSession session) {
		Ticket ticket = userService.buyTicket(id, user);
		if (session.getAttribute("bank") != null) {
			session.setAttribute("bank", bankService.getBank());
		}
		return ticket;
	}
}

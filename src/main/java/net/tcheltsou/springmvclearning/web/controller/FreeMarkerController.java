package net.tcheltsou.springmvclearning.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class FreeMarkerController {

	@RequestMapping("/userAccount/{id}")
	public String handleUserByID(Model model, @PathVariable int id) {
		model.addAttribute("sentence", "Hello userAccount with id = ");
		model.addAttribute("parameter", id);
		return "template";
	}

	@RequestMapping(value = "/userAccount", params = {"name"})
	public String handleUserByName(Model model, @RequestParam String name) {
		model.addAttribute("sentence", "Hello userAccount with name ");
		model.addAttribute("parameter", name);
		return "template";
	}

	@RequestMapping(value = "/userAccount/tickets")
	public String handleUserTickets(Model model) {
		model.addAttribute("sentence", "Your tickets are: ");
		model.addAttribute("parameters", Arrays.asList("Ticket 1", "Ticket 2"));
		return "template_list";
	}

	@GetMapping("/login")
	public String handleLogin(Model model, String error, String logout, HttpSession session) {
		if (error != null) {
			model.addAttribute("error" , "Bad credentials");
		}
		if (logout != null) {
			session.removeAttribute("userAccount");
			model.addAttribute("message", "You have been logged out successfully");
		}
		return "login";
	}


}

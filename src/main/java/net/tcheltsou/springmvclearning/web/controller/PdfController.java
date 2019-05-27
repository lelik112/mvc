package net.tcheltsou.springmvclearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PdfController {

	@RequestMapping(value = "/users", headers = {"accept=application/pdf"})
	public String handleUsers(@RequestHeader String[] accept, Model model) {
		model.addAttribute("acceptHeader", accept);
		return "viewUsersPdf";
	}

	@RequestMapping(value = "/events", headers = {"accept=application/pdf"})
	public String handleEvents(HttpServletRequest request, Model model) {
		model.addAttribute("headerNames", request.getHeaderNames());
		return "viewEventsPdf";
	}
}

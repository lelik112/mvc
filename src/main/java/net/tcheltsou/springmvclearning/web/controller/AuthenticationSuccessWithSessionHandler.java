package net.tcheltsou.springmvclearning.web.controller;

import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.service.BankService;
import net.tcheltsou.springmvclearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessWithSessionHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private UserService userService;
	@Autowired
	private BankService bankService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = userService.read(request.getParameter("username"));
		request.getSession().setAttribute("user", user);
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_BOOKING_MANAGER"))) {
			request.getSession().setAttribute("bank", bankService.getBank());
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}

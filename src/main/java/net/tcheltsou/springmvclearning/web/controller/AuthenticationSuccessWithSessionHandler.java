package net.tcheltsou.springmvclearning.web.controller;

import net.tcheltsou.springmvclearning.entity.UserAccount;
import net.tcheltsou.springmvclearning.repository.BankRepository;
import net.tcheltsou.springmvclearning.repository.UserAccountRepository;
import net.tcheltsou.springmvclearning.service.BankService;
import net.tcheltsou.springmvclearning.service.UserAccountService;
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
	private UserAccountService userAccountService;
	@Autowired
	private BankService bankService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		UserAccount userAccount = userAccountService.read(request.getParameter("username"));
		request.getSession().setAttribute("userAccount", userAccount);
		System.out.println(authentication.getAuthorities());
		System.out.println(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_BOOKING_MANAGER")));
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_BOOKING_MANAGER"))) {
			request.getSession().setAttribute("bank", bankService.getBank());
		}
		System.out.println(authentication.getAuthorities());
		super.onAuthenticationSuccess(request, response, authentication);
	}

}

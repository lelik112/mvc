package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.UserAccount;
import net.tcheltsou.springmvclearning.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = userRepository.getUserByUserName(username);
		if (userAccount == null) {
			throw new UsernameNotFoundException(username);
		}
		return userAccount.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.collectingAndThen(Collectors.toSet(),
						it -> new User(userAccount.getUsername(), userAccount.getPassword(), it)));
	}
}

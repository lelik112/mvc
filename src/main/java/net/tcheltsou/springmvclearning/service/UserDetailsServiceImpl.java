package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.collectingAndThen(Collectors.toSet(),
						it -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), it)));
	}
}

package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		net.tcheltsou.springmvclearning.entity.User user = userRepository.getUserByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.collectingAndThen(Collectors.toSet(),
						it -> new User(user.getUsername(), user.getPassword(), it)));
//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		user.getRoles().forEach(it -> grantedAuthorities.add(new SimpleGrantedAuthority(it)));
//		return new org.springframework.security.core.userdetails.User(user.getUsername(),
//				user.getPassword(), grantedAuthorities);
	}
}

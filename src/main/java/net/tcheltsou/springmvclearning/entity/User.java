package net.tcheltsou.springmvclearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {
	private Long id;
	private String username;
	private String password;
	private Set<String> roles;
}

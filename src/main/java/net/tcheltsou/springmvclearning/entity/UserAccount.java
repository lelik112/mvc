package net.tcheltsou.springmvclearning.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class UserAccount {
	@NonNull
	private Long id;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	private Set<String> roles;
	private BigDecimal amount;
	private Set<Ticket> tickets;
}

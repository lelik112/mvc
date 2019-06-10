package net.tcheltsou.springmvclearning.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Ticket {
	@NonNull
	Long id;
	@NonNull
	BigDecimal price;
	@NonNull
	boolean isSold;
	UserAccount userAccount;
	Event event;
}

package net.tcheltsou.springmvclearning.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Ticket {
	@NonNull
	Long id;
	@NonNull
	BigDecimal price;
	@NonNull
	boolean isSold;
	User user;
	Event event;
}

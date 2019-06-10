package net.tcheltsou.springmvclearning.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class Event {
	@NonNull
	Long id;
	@NonNull
	String name;
	@NonNull
	LocalDate date;
	List<Ticket> tickets;
}

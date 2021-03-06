package net.tcheltsou.springmvclearning.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
	@NonNull
	@XmlAttribute
	Long id;
	@NonNull
	String name;
	@NonNull
	LocalDate date;
	@XmlTransient
	List<Ticket> tickets;
}

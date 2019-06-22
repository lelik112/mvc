package net.tcheltsou.springmvclearning.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNull;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	@NonNull
	@XmlAttribute
	Long id;
	@NonNull
	String username;
	@NonNull
	String password;
	@NonNull
	Set<String> roles;
	BigDecimal amount;
	@XmlTransient
	Set<Ticket> tickets;
}

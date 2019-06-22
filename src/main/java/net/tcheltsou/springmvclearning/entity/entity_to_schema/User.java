package net.tcheltsou.springmvclearning.entity.entity_to_schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;
import java.util.Set;


@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	@XmlAttribute
	private Long id;
	private String username;
	private String password;
	private Set<String> roles;
	private BigDecimal amount;
}

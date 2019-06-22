package net.tcheltsou.springmvclearning.entity.entity_to_schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
	@XmlAttribute
	private Long id;
	private String name;
	private LocalDate date;
}

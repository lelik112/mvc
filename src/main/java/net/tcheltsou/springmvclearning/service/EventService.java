package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.Event;
import net.tcheltsou.springmvclearning.entity.Ticket;
import net.tcheltsou.springmvclearning.repository.EventRepository;
import net.tcheltsou.springmvclearning.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public List<Event> getEvents() {
		return eventRepository.readAll()
				.stream()
				.sorted(Comparator.comparing(Event::getDate))
				.collect(Collectors.toList());
	}

	public Event getEventWithAvailableTicketsByEventId(Long id) {
		Event event = eventRepository.read(id);
		event.setTickets(ticketRepository.getTicketsOfEvent(event)
				.stream()
				.filter(it -> !it.isSold())
				.peek(it -> it.setEvent(event))
				.sorted(Comparator.comparing(Ticket::getId))
				.collect(Collectors.toList()));
		return event;
	}


}

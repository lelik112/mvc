package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.Ticket;
import net.tcheltsou.springmvclearning.entity.User;
import net.tcheltsou.springmvclearning.repository.TicketRepository;
import net.tcheltsou.springmvclearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private BankService bankService;

	@Transactional
	public Ticket buyTicket(Long ticketId, User user) {
		Ticket ticket = ticketRepository.read(ticketId);
		if (ticket.isSold()) {
			throw new IllegalStateException("The ticket with id: " + ticketId + "has been already sold");
		}
		User userFromDs = userRepository.getUserByUserName(user.getUsername());
		if (userFromDs.getAmount().compareTo(ticket.getPrice()) < 0) {
			throw new IllegalStateException("Do not have enough money");
		}
		ticket.setSold(true);
		ticket.setUser(user);
		user.setAmount(userFromDs.getAmount().subtract(ticket.getPrice()));
		userRepository.updateBalance(user);
		ticketRepository.update(ticket);
		bankService.addMoney(ticket.getPrice());
		return ticket;
	}

	public User read(String  name) {
		return userRepository.getUserByUserName(name);
	}


}

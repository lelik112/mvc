package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.Ticket;
import net.tcheltsou.springmvclearning.entity.UserAccount;
import net.tcheltsou.springmvclearning.repository.TicketRepository;
import net.tcheltsou.springmvclearning.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private BankService bankService;

	@Transactional
	public Ticket buyTicket(Long ticketId, UserAccount user) {
		Ticket ticket = ticketRepository.read(ticketId);
		if (ticket.isSold()) {
			throw new IllegalStateException("The ticket with id: " + ticketId + "has been already sold");
		}
		UserAccount userFromDs = userAccountRepository.getUserByUserName(user.getUsername());
		if (userFromDs.getAmount().compareTo(ticket.getPrice()) < 0) {
			throw new IllegalStateException("Do not have enough money");
		}
		ticket.setSold(true);
		ticket.setUserAccount(user);
		user.setAmount(userFromDs.getAmount().subtract(ticket.getPrice()));
		userAccountRepository.updateBalance(user);
		ticketRepository.update(ticket);
		bankService.addMoney(ticket.getPrice());
		return ticket;
	}

	public UserAccount read(String  name) {
		return userAccountRepository.getUserByUserName(name);
	}


}

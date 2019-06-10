package net.tcheltsou.springmvclearning.service;

import net.tcheltsou.springmvclearning.entity.Bank;
import net.tcheltsou.springmvclearning.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;

	@Transactional
	public void addMoney(BigDecimal money) {
		Bank bank = bankRepository.read(1L);
		bank.setAmount(bank.getAmount().add(money));
		bankRepository.update(bank);
	}

	public Bank getBank() {
		return bankRepository.read(1L);
	}
}

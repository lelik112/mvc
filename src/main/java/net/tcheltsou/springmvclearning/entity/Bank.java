package net.tcheltsou.springmvclearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Bank {
	Long id;
	BigDecimal amount;
}

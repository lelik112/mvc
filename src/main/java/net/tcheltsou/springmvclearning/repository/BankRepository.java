package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class BankRepository {
	private static final BankRowMapper BANK_ROW_MAPPER = new BankRowMapper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_UPDATE = "UPDATE bank SET amount = ?";

	private static final String SQL_READ = "SELECT id, amount FROM bank WHERE id = ?";

	public void update(Bank bank) {
		jdbcTemplate.update(SQL_UPDATE, bank.getAmount());
	}

	@Transactional(readOnly = true)
	public Bank read(Long id) {
		return jdbcTemplate.queryForObject(SQL_READ, BANK_ROW_MAPPER, id);
	}


	private static class BankRowMapper implements RowMapper<Bank> {

		@Override
		public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Bank(rs.getLong(1), rs.getBigDecimal(2));
		}
	}
}

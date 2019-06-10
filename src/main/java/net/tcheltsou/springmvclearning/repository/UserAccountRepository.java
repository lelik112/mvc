package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class UserAccountRepository {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_GET_USER_BY_NAME = "SELECT u.id, u.name, u.password, r.name, u.amount FROM user AS u " +
			"JOIN user_role AS u_r ON u.id = u_r.user_id JOIN role AS r ON u_r.role_id = r.id " +
			"WHERE u.name = ?";

	private static final String SQL_UPDATE_BALANCE = "UPDATE user SET amount = ? WHERE id = ?";

	@Transactional(readOnly = true)
	public UserAccount getUserByUserName(String username) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_NAME);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				UserAccount userAccount = new UserAccount(resultSet.getLong(1), resultSet.getString(2),
						resultSet.getString(3), new HashSet<>(Collections.singletonList(resultSet.getString(4))));
				userAccount.setAmount(resultSet.getBigDecimal(5));
				while (resultSet.next()) {
					userAccount.getRoles().add(resultSet.getString(4));
				}
				return userAccount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public void updateBalance(UserAccount user) {
		jdbcTemplate.update(SQL_UPDATE_BALANCE, user.getAmount(), user.getId());
	}
}

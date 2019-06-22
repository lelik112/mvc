package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class UserRepository {

	private final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_GET_USER_BY_NAME = "SELECT u.id, u.name, u.password, r.name, u.amount FROM user AS u " +
			"JOIN user_role AS u_r ON u.id = u_r.user_id JOIN role AS r ON u_r.role_id = r.id " +
			"WHERE u.name = ?";
	private static final String SQL_CREATE_USER = "INSERT INTO user (name, password, amount) VALUES (?, ?, ?)";
	private static final String SQL_ADD_ROLE = "INSERT INTO user_role (role_id, user_id) VALUES (?, ?)";
	private static final String SQL_GET_ROLE_ID = "SELECT id FROM ROLE WHERE name = ?";
	private static final String SQL_READ_USER = "SELECT id, name, password, amount FROM user WHERE id = ?";
	private static final String SQL_READ_ALL_USER = "SELECT id, name, password, amount FROM user";
	private static final String SQL_GET_ROLES = "SELECT name FROM role WHERE user_id = ?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET name = ?, password = ?, amount = ?,  WHERE id = ?";
	private static final String SQL_UPDATE_BALANCE = "UPDATE user SET amount = ? WHERE id = ?";
	private static final String SQL_DELETE_USER = "DELETE FROM user WERE id = ?";

	@Transactional(readOnly = true)
	public User getUserByUserName(String username) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_NAME);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				User user = new User(resultSet.getLong(1), resultSet.getString(2),
						resultSet.getString(3), new HashSet<>(Collections.singletonList(resultSet.getString(4))));
				user.setAmount(resultSet.getBigDecimal(5));
				while (resultSet.next()) {
					user.getRoles().add(resultSet.getString(4));
				}
				return user;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public void updateBalance(User user) {
		jdbcTemplate.update(SQL_UPDATE_BALANCE, user.getAmount(), user.getId());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(User user) {
		jdbcTemplate.update(SQL_CREATE_USER, user.getUsername(), user.getPassword(), user.getAmount());
		user.getRoles().stream()
				.map(it -> jdbcTemplate.queryForObject(SQL_GET_ROLE_ID, Long.class, it))
				.forEach(it -> jdbcTemplate.update(SQL_ADD_ROLE, it, user.getId()));
	}

	public User read(Long id) {
		return jdbcTemplate.queryForObject(SQL_READ_USER, USER_ROW_MAPPER, id);
	}

	public List<User> readAll() {
		return jdbcTemplate.query(SQL_READ_ALL_USER, USER_ROW_MAPPER);
	}

	public void update(User user) {
		jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), user.getAmount());
	}

	public void delete(Long id) {
		jdbcTemplate.update(SQL_DELETE_USER, id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	private class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User(rs.getLong(1), rs.getString(2),
					rs.getString(3), new HashSet<>());
			user.setAmount(rs.getBigDecimal(4));
			jdbcTemplate.queryForList(SQL_GET_ROLES, String.class, user.getId())
					.forEach(it -> user.getRoles().add(it));
			return user;
		}
	}
}

package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;

@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;

	private static final String SQL_GET_USER_BY_NAME = "SELECT u.id, u.name, u.password, r.name FROM user AS u " +
			"JOIN user_role AS u_r ON u.id = u_r.user_id JOIN role AS r ON u_r.role_id = r.id " +
			"WHERE u.name = ?";

	public User getUserByUserName(String username) {
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_NAME);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				User user = new User(resultSet.getLong(1), resultSet.getString(2),
						resultSet.getString(3), new HashSet<>(Collections.singletonList(resultSet.getString(4))));
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
}

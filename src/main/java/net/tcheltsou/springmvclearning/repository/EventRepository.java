package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class EventRepository  {
	private static final EventRowMapper EVENT_ROW_MAPPER = new EventRowMapper();
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_READ_EVENTS = "SELECT id, name, date FROM event";
	private static final String SQL_READ_EVENT = "SELECT id, name, date FROM event WHERE id = ?";
	private static final String SQL_UPDATE_EVENT = "UPDATE event SET name = ?, date = ? WHERE id = ?";
	private static final String SQL_DELETE_EVENT = "DELETE FROM event WHERE id = ?";
	private static final String SQL_CREATE_EVENT = "INSERT INTO event (name, date) VALUES (?, ?)";

	@Transactional(readOnly = true)
	public List<Event> readAll() {
		return jdbcTemplate.query(SQL_READ_EVENTS, EVENT_ROW_MAPPER);
	}

	public void update(Event event) {
		jdbcTemplate.update(SQL_UPDATE_EVENT, event.getName(), event.getDate());
	}

	public void delete(Long id) {
		jdbcTemplate.update(SQL_DELETE_EVENT, id);
	}

	public void create(Event event) {
		jdbcTemplate.update(SQL_CREATE_EVENT, event.getName(), event.getDate());
	}

	@Transactional(readOnly = true)
	public Event read(Long id) {
		return jdbcTemplate.queryForObject(SQL_READ_EVENT, EVENT_ROW_MAPPER, id);
	}

	private static class EventRowMapper implements RowMapper<Event> {
		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Event(rs.getLong(1),
					rs.getString(2), rs.getDate(3).toLocalDate());
		}
	}
}

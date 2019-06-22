package net.tcheltsou.springmvclearning.repository;

import net.tcheltsou.springmvclearning.entity.Event;
import net.tcheltsou.springmvclearning.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class TicketRepository{
	private static final TicketRowMapper TICKET_ROW_MAPPER = new TicketRowMapper();
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_READ_TICKETS_OF_EVENT = "SELECT id, price, is_sold, event_id, user_id " +
			"FROM ticket WHERE event_id = ?";

	private static final String SQL_READ = "SELECT id, price, is_sold FROM ticket WHERE id = ?";

	private static final String SQL_UPDATE = "UPDATE ticket SET price = ?, is_sold = ?, user_id = ? WHERE id = ?";

	public List<Ticket> getTicketsOfEvent(Event event) {
		return jdbcTemplate.query(SQL_READ_TICKETS_OF_EVENT, TICKET_ROW_MAPPER, event.getId());
	}

	@Transactional(readOnly = true)
	public Ticket read(Long ticketId) {
		return jdbcTemplate.queryForObject(SQL_READ, TICKET_ROW_MAPPER, ticketId);
	}

	public void update(Ticket ticket) {
		jdbcTemplate.update(SQL_UPDATE, ticket.getPrice(), ticket.isSold(),
				ticket.getUser().getId(), ticket.getId());
	}

	public static class TicketRowMapper implements RowMapper<Ticket> {

		@Override
		public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Ticket(rs.getLong(1), rs.getBigDecimal(2),
					rs.getBoolean(3));
		}
	}
}

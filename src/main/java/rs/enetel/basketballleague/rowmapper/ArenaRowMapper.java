package rs.enetel.basketballleague.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rs.enetel.basketballleague.dao.Arena;

public class ArenaRowMapper implements RowMapper<Arena>
{

	@Override
	public Arena mapRow(ResultSet results, int row) throws SQLException
	{
		Arena arena = new Arena();
		
		arena.setId(results.getInt("ID"));
		arena.setCountry(results.getString("COUNTRY"));
		arena.setCity(results.getString("CITY"));
		arena.setTimeZone(results.getString("TIME_ZONE_ID"));
		arena.setName(results.getString("NAME"));
		arena.setCapacity(results.getInt("CAPACITY"));
		
		return arena;
	}

}

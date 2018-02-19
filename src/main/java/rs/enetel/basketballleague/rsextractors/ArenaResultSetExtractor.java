package rs.enetel.basketballleague.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import rs.enetel.basketballleague.dao.Arena;

public class ArenaResultSetExtractor implements ResultSetExtractor<Arena>
{

	@Override
	public Arena extractData(ResultSet result) throws SQLException, DataAccessException
	{
		if (result.next())
		{

			Arena arena = new Arena();

			arena.setId(result.getInt("ID"));
			arena.setCountry(result.getString("COUNTRY"));
			arena.setCity(result.getString("CITY"));
			arena.setTimeZone(result.getString("TIME_ZONE_ID"));
			arena.setName(result.getString("NAME"));
			arena.setCapacity(result.getInt("CAPACITY"));

			int activeFlag = result.getInt("ACTIVE");

			if (activeFlag == 0)
			{
				arena.setActive(false);
			}
			else
			{
				arena.setActive(true);
			}

			return arena;
		}

		return null;
	}

}

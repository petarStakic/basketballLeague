package rs.enetel.basketballleague.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rs.enetel.basketballleague.dao.AppImage;
import rs.enetel.basketballleague.dao.Arena;

public class ArenaRowMapper implements RowMapper<Arena>
{

	@Override
	public Arena mapRow(ResultSet results, int row) throws SQLException
	{
		Arena arena = new Arena();
		AppImage img = new AppImage();
		
		arena.setId(results.getInt("ID"));
		arena.setCountry(results.getString("COUNTRY"));
		arena.setCity(results.getString("CITY"));
		arena.setTimeZone(results.getString("TIME_ZONE_ID"));
		arena.setName(results.getString("NAME"));
		arena.setCapacity(results.getInt("CAPACITY"));
		
		img.setId(results.getInt("IMAGE_ID"));
		arena.setImage(img);
		
		return arena;
	}

}

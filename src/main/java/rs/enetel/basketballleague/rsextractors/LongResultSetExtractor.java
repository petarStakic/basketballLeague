package rs.enetel.basketballleague.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class LongResultSetExtractor implements ResultSetExtractor<Long>
{

	@Override
	public Long extractData(ResultSet result) throws SQLException, DataAccessException
	{
		if (result.next())
		{
			return result.getLong(1);
		}
		return null;
	}

}

package rs.enetel.basketballleague.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class IntegerResultSetExtractor implements ResultSetExtractor<Integer>
{

	@Override
	public Integer extractData(ResultSet result) throws SQLException, DataAccessException
	{
		if(result.next())
			return result.getInt(1);
		return null;
	}

}

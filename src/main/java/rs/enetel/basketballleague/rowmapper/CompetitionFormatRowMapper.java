package rs.enetel.basketballleague.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rs.enetel.basketballleague.dao.CompetitionFormat;
import rs.enetel.basketballleague.dao.EliminationCompetitionFormat;
import rs.enetel.basketballleague.dao.GroupCompetitionFormat;

public class CompetitionFormatRowMapper implements RowMapper<CompetitionFormat>
{

	@Override
	public CompetitionFormat mapRow(ResultSet results, int row) throws SQLException
	{
		// TODO Auto-generated method stub
		CompetitionFormat competitionFormat = new CompetitionFormat();
		
		competitionFormat.setId(results.getInt("ID"));
		competitionFormat.setType(results.getString("TYPE"));
		competitionFormat.setDescription(results.getString("DESCRIPTION"));
		competitionFormat.setMatchesPerFixture(results.getInt("MATCHES_PER_FIXTURE"));
		
		Integer numberOfGroups = results.getInt("number_of_groups"); 
		if(numberOfGroups != null)
		{
			((GroupCompetitionFormat) competitionFormat).setNumberOfGroups(numberOfGroups);
		}
		
		String tiebraker = results.getString("TIEBRAKER");
		if(tiebraker != null)
		{
			((EliminationCompetitionFormat) competitionFormat).setTiebraker(tiebraker);
		}
		
		return competitionFormat;
	}

}

package rs.enetel.basketballleague.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rs.enetel.basketballleague.dao.CompetitionFormat;
import rs.enetel.basketballleague.dao.EliminationCompetitionFormat;
import rs.enetel.basketballleague.dao.GroupCompetitionFormat;
import rs.enetel.basketballleague.dao.TournamentPhase;

public class TournamentPhaseRowMapper implements RowMapper<TournamentPhase>
{

	@Override
	public TournamentPhase mapRow(ResultSet results, int row) throws SQLException
	{
		TournamentPhase phase = new TournamentPhase();
		CompetitionFormat phaseFormat = new CompetitionFormat();
		
		phase.setId(results.getLong("ID"));
		phase.setPhaseNum(results.getInt("PHASE_NUM"));
		phase.setPhaseName(results.getString("PHASE_NAME"));
		phase.setStartDate(results.getDate("START_DATE"));
		phase.setEndDate(results.getDate("END_DATE"));
		
		phaseFormat.setId(results.getInt("FORMAT_ID"));
		phaseFormat.setType(results.getString("TYPE"));
		phaseFormat.setDescription(results.getString("DESCRIPTION"));
		phaseFormat.setMatchesPerFixture(results.getInt("MATCHES_PER_FIXTURE"));
		
		Integer numberOfGroups = results.getInt("number_of_groups"); 
		if(numberOfGroups != null)
		{
			((GroupCompetitionFormat) phaseFormat).setNumberOfGroups(numberOfGroups);
		}
		
		String tiebraker = results.getString("TIEBRAKER");
		if(tiebraker != null)
		{
			((EliminationCompetitionFormat) phaseFormat).setTiebraker(tiebraker);
		}
		
		phase.setFormat(phaseFormat);
		
		return phase;
	}

}

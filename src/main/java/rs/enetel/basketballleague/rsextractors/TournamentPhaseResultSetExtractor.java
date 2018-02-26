package rs.enetel.basketballleague.rsextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import rs.enetel.basketballleague.dao.CompetitionFormat;
import rs.enetel.basketballleague.dao.Season;
import rs.enetel.basketballleague.dao.Team;
import rs.enetel.basketballleague.dao.TeamSeasonRanking;
import rs.enetel.basketballleague.dao.TournamentPhase;

public class TournamentPhaseResultSetExtractor implements ResultSetExtractor<TournamentPhase>
{

	@Override
	public TournamentPhase extractData(ResultSet results) throws SQLException, DataAccessException
	{
		TournamentPhase phase = new TournamentPhase();
		CompetitionFormat format = new CompetitionFormat();
		Season season = new Season();
		List<TeamSeasonRanking> teamRankings = new LinkedList<>(); 
		
		while(results.next())
		{
			boolean firstPass = true;

			TeamSeasonRanking ranking = new TeamSeasonRanking();
			Team team = new Team();
			
			if(firstPass)
			{
				phase.setId(results.getLong("ID"));
				phase.setPhaseNum(results.getInt("PHASE_NUM"));
				phase.setPhaseName(results.getString("PHASE_NAME"));
				phase.setStartDate(results.getDate("START_DATE"));
				phase.setEndDate(results.getDate("END_DATE"));
				
				format.setId(results.getInt("COMPETITION_FORMAT_ID"));
				phase.setFormat(format);
				
				season.setId(results.getInt("SEASON_ID"));
				phase.setSeason(season);
			}
			
			ranking.setSeason(season);
			ranking.setRanking(results.getDouble("RANKING"));
			
			team.setId(results.getInt("TEAMS_TABLE_ID"));
			team.setCountry(results.getString("COUNTRY"));
			team.setCity(results.getString("CITY"));
			team.setNational(results.getBoolean("NATIONAL"));
			
			ranking.setTeam(team);
			
			teamRankings.add(ranking);
		}
		
		phase.setTeams(teamRankings);
		
		return phase;
	}

}

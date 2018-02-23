package rs.enetel.basketballleague.dao;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Season
{
	private Integer id;
	private League league;
	private Date startDate;
	private Date endDate;
	private Integer maxPlayersPerTeam;
	private Integer maxFoulsPerGame;
	private Integer gameLength;
	private Integer numberOfSegments;
	private Integer segmentLength;
	private Integer numberOfTeams;
	private TournamentFormat tournamentFormat;
	private List<Team> teams;
}

package rs.enetel.basketballleague.dao;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentPhase
{
	private Long id;
	private Season season;
	private Integer phaseNum;
	private String phaseName;
	private Date startDate;
	private Date endDate;
	private CompetitionFormat format;
	private List<TeamSeasonRanking> teams;
}

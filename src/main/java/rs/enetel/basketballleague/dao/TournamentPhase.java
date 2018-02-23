package rs.enetel.basketballleague.dao;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentPhase
{
	private Integer id;
	private Integer phaseNum;
	private Integer phaseName;
	private Date startDate;
	private Date endDate;
	private CompetitionFormat format;
	private List<Team> teams;
}

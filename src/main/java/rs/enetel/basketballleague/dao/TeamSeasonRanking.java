package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamSeasonRanking
{
	private Team team;
	private Season season;
	private Double ranking;
}

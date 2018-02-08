package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamStats
{
	private Team team;
	private Season season;
	private Integer won;
	private Integer lost;
	private Integer draw;
}

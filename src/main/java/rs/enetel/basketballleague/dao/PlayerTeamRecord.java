package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTeamRecord
{
	private Team team;
	private Player player;
	private Integer jersyNumber;
}

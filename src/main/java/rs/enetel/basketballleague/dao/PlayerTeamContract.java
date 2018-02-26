package rs.enetel.basketballleague.dao;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerTeamContract
{
	private Player player;
	private Team team;
	private Integer jersyNumber;
	private Date contractStartDate;
	private Date contractEndDate;
}

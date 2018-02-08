package rs.enetel.basketballleague.dao;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Season
{
	private int leagueID;
	private int id;
	private Date startDate;
	private Date endDate;
}

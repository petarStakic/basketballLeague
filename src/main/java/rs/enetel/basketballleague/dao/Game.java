package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game
{
	private int id;
	private Team homeTeam;
	private Team guestTeam;
	private Arena arena;
	private long date; // PROMENITI KAD NAUCIM O DATE-TIME TIPOVIMA
	private boolean isRunning;
	private Integer homeTeamScore; // dok utakmica ne pocne oba polja su null
	private Integer guestTeamScore;

}

package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentGame
{
	private Game game;
	private CurrentGameRecord homeTeamRecord;
	private CurrentGameRecord guestTeamRecord;
	private List<PlayerGameRecord> homePlayerRecord;
	private List<PlayerGameRecord> guestPlayerRecord;
	private Integer timer;
	private Integer currentSegment;
}

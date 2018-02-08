package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketballStats
{
	private Player player;
	private Game game;
	private Integer score;
	private Integer freeThrowsTries;
	private Integer twoPoIntegerTries;
	private Integer threePoIntegerTries;
	private Integer freeThrowsMade;
	private Integer twoPoIntegerMade;
	private Integer threePoIntegerMade;
	private Integer blocks;
	private Integer turnovers;
	private Integer steals;
	private Integer offensiveRebound;
	private Integer defensiveRebound;
	private Integer assists;
	private Integer fouls;
	private Double rankScore;
	private Integer secondsPlayed;
}

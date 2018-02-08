package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League
{
	private int ID;
	private String name;
	private LeagueType type;
	private int maxPlayersPerTeam;
	private int maxFoulsPerGame; // broj faulova koje igrac moze napravati pre nego sto je trajno izbacen iz igre
	private int gameLength;
	private byte numberOfSegments;
	private int segmentLength;

}

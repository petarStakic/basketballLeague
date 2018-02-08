package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentGameRecord
{
	private Team team;
	private Integer teamFouls; // na pocetku svakog segmenta utakmice oba polja se postavljaju na 0
	private boolean inBonus;
}

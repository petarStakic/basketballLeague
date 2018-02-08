package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerGameRecord
{
	private Team team;
	private Player player;
	private BasketballStats gameStats;
	private boolean onField;
	private Integer personalFouls;
	private Integer secondsPlayed; // od poslednje izmene. Kad se onField polje postavi na false trebalo bi da se vrati na 0, nakon sto se dalje obrade podaci
}

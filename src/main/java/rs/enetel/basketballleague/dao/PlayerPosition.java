package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerPosition
{
	private Player player;
	private Position position;
	private Integer priority;
}

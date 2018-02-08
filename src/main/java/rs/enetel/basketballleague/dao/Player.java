package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Person
{
	private int id;
    private Double height;
    private List<PlayerPosition> positions;
}

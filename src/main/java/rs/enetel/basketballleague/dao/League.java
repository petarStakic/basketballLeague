package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class League
{
	private Integer id;
	private String name;
	private LeagueGeoScope geoScope;
	private LeagueType type;
	private List<Season> seasons;
}

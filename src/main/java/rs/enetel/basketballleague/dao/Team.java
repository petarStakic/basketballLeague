package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team
{
	private Integer id;
	private String name;
	private String country;
	private String city;
	private Boolean national;
	private List<PlayerTeamContract> team;
	private List<Coach> coaches;
}

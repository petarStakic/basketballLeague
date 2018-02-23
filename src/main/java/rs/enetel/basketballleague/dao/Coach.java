package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coach extends Person
{
	private Integer id;
	private Integer coachSince;
	private List<Team> teams;
}

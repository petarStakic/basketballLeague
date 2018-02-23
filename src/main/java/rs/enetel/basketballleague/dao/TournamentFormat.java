package rs.enetel.basketballleague.dao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentFormat
{
	private Integer id;
	private String description;
	private List<TournamentPhase> phases;
}

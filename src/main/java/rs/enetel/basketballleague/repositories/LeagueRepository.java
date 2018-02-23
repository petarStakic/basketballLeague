package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.League;

public interface LeagueRepository
{
	League add(League league);
	
	League getById(int id);
	
	List<League> all();
	
	League edit(League league);
}

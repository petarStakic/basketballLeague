package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.Season;

public interface SeasonRepository
{
	Season add(Season season);
	
	Season getById(int id);
	
	List<Season> all();
	
	Season edit(Season season);
}

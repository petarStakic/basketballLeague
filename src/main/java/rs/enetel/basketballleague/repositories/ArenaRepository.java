package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.searchcrits.ArenaSearchCriteria;

public interface ArenaRepository
{
	Arena add(Arena arena) throws Exception;

	List<Arena> all();

	List<Arena> search(ArenaSearchCriteria criteria) throws Exception; //QUESTION: da li je ArenaSearchCriteria previse specificno za interface?

	Arena searchFor(String name, String city, String country) throws Exception;

	Arena edit(Arena arena) throws Exception;

	Arena remove(Arena arena) throws Exception;

	Arena getById(int id);

}

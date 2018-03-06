package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.searchcrits.ArenaSearchCriteria;

public interface ArenaRepository
{
	Arena add(Arena arena) throws RuntimeException;

	List<Arena> all();

	List<Arena> search(ArenaSearchCriteria criteria) throws RuntimeException; //QUESTION: da li je ArenaSearchCriteria previse specificno za interface?

	Arena searchFor(String name, String city, String country) throws RuntimeException;

	Arena edit(Arena arena) throws RuntimeException;

	Arena remove(Arena arena) throws RuntimeException;

	Arena getById(int id);

}

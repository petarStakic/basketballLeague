package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.TournamentFormat;

public interface TournamentFormatRepository
{
	TournamentFormat add(TournamentFormat tournamentFormat);
	
	TournamentFormat getById(int id);
	
	List<TournamentFormat> all();
	
	TournamentFormat edit(TournamentFormat tournamentFormat);
}

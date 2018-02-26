package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.Season;
import rs.enetel.basketballleague.dao.TournamentPhase;

public interface TournamentPhaseRepository
{
	TournamentPhase add(TournamentPhase phase) throws Exception;
	
	TournamentPhase getById(int id) throws Exception;
	
	TournamentPhase edit(TournamentPhase phase) throws Exception;

	List<TournamentPhase> allForSeason(Season season) throws Exception;
}

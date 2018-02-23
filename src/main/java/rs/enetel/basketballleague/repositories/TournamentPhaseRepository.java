package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.TournamentPhase;

public interface TournamentPhaseRepository
{
	TournamentPhase add(TournamentPhase phase);
	
	TournamentPhase getById(int id);
	
	List<TournamentPhase> all();
	
	TournamentPhase edit(TournamentPhase phase);
}

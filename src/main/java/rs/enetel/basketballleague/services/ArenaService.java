package rs.enetel.basketballleague.services;

import rs.enetel.basketballleague.commands.ArenaCommand;

public interface ArenaService
{
	ArenaCommand addNew(ArenaCommand arenaCmd) throws Exception;

}

package rs.enetel.basketballleague.services;

import rs.enetel.basketballleague.commands.ArenaCommand;

public interface ArenaService
{
	ArenaCommand addNew(ArenaCommand arenaCmd) throws RuntimeException;
	
	ArenaCommand deactivate(ArenaCommand arenaCmd) throws RuntimeException;

	ArenaCommand editExistng(ArenaCommand arenaCmd) throws RuntimeException;

	ArenaCommand getCommandById(Integer id) throws RuntimeException;

}

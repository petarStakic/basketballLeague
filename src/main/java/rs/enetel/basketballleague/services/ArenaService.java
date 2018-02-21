package rs.enetel.basketballleague.services;

import rs.enetel.basketballleague.commands.ArenaCommand;

public interface ArenaService
{
	ArenaCommand addNew(ArenaCommand arenaCmd) throws Exception;
	
	ArenaCommand deactivate(ArenaCommand arenaCmd) throws Exception;

	ArenaCommand editExistng(ArenaCommand arenaCmd) throws Exception;

	ArenaCommand getCommandById(Integer id) throws Exception;

}

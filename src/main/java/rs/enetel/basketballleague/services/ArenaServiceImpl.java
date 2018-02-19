package rs.enetel.basketballleague.services;

import org.springframework.stereotype.Service;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.converters.ArenaCommandToArena;
import rs.enetel.basketballleague.converters.ArenaToArenaCommand;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.repositories.ArenaRepository;

@Service
public class ArenaServiceImpl implements ArenaService
{

	ArenaRepository arenaRepository;
	ArenaCommandToArena arenaCommandToArena;
	ArenaToArenaCommand arenaToArenaCommand;

	public ArenaServiceImpl(ArenaCommandToArena arenaCommandToArena, ArenaToArenaCommand arenaToArenaCommand, ArenaRepository arenaRepository)
	{
		this.arenaCommandToArena = arenaCommandToArena;
		this.arenaToArenaCommand = arenaToArenaCommand;
		this.arenaRepository = arenaRepository;
	}

	@Override
	public ArenaCommand addNew(ArenaCommand arenaCmd) throws Exception
	{

		Arena arenaToInsert = arenaCommandToArena.convert(arenaCmd);
		if (arenaToInsert == null)
		{
			return null;
		}

		Arena arena = arenaRepository.searchFor(arenaToInsert.getName(), arenaToInsert.getCity(),
				arenaToInsert.getCountry());

		if (arena == null)
		{
			arena = arenaRepository.add(arenaToInsert);
		}

		return arenaToArenaCommand.convert(arena);
	}

}

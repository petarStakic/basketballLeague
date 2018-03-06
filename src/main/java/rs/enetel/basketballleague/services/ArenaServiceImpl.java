package rs.enetel.basketballleague.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.converters.ArenaCommandToArena;
import rs.enetel.basketballleague.converters.ArenaToArenaCommand;
import rs.enetel.basketballleague.dao.AppImage;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.repositories.AppImageRepository;
import rs.enetel.basketballleague.repositories.ArenaRepository;

@Service
public class ArenaServiceImpl implements ArenaService
{

	ArenaRepository arenaRepository;
	AppImageRepository appImageRepository;
	ArenaCommandToArena arenaCommandToArena;
	ArenaToArenaCommand arenaToArenaCommand;

	public ArenaServiceImpl(ArenaCommandToArena arenaCommandToArena, ArenaToArenaCommand arenaToArenaCommand,
			ArenaRepository arenaRepository, AppImageRepository appImageRepository)
	{
		this.arenaCommandToArena = arenaCommandToArena;
		this.arenaToArenaCommand = arenaToArenaCommand;
		this.arenaRepository = arenaRepository;
		this.appImageRepository = appImageRepository;
	}

	@Transactional
	@Override
	public ArenaCommand addNew(ArenaCommand arenaCmd) throws RuntimeException
	{
		boolean insertImage = arenaCmd.getImageChanged().equals("changed");
		AppImage appImage = new AppImage();

		if (insertImage)
		{
			appImage = arenaCmd.getImage();
			appImage = appImageRepository.add(appImage);
			arenaCmd.setImage(appImage);
		}

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

		if (!arena.isActive())
		{
			arena.setActive(true);
			arenaRepository.edit(arena);
		}

		return arenaToArenaCommand.convert(arena);
	}

	@Transactional
	@Override
	public ArenaCommand editExistng(ArenaCommand arenaCmd) throws RuntimeException
	{
		boolean insertImage = arenaCmd.getImageChanged().equals("changed");
		AppImage appImage = new AppImage();
		
		if (insertImage)
		{
			appImage = arenaCmd.getImage();
			appImage = appImageRepository.add(appImage);
			arenaCmd.setImage(appImage);
		}

		Arena arenaToEdit = arenaCommandToArena.convert(arenaCmd);

		if (arenaToEdit == null)
		{
			return null;
		}

		arenaCmd = arenaToArenaCommand.convert(arenaRepository.edit(arenaToEdit));

		return arenaCmd;
	}

	@Transactional
	@Override
	public ArenaCommand deactivate(ArenaCommand arenaCmd) throws RuntimeException
	{
		Arena arenaToDeactivate = arenaCommandToArena.convert(arenaCmd);
		if (arenaToDeactivate == null)
		{
			return null;
		}

		arenaToDeactivate.setActive(false);
		arenaRepository.edit(arenaToDeactivate);

		arenaCmd.setActive(false);
		return arenaCmd;
	}

	@Transactional
	@Override
	public ArenaCommand getCommandById(Integer id) throws RuntimeException
	{
		if (id == null)
		{
			return null;
		}

		Arena arena = arenaRepository.getById(id);
		ArenaCommand arenaCmd = arenaToArenaCommand.convert(arena);

		return arenaCmd;
	}
}

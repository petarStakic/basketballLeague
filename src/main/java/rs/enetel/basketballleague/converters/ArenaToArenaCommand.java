package rs.enetel.basketballleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.Arena;

@Component
public class ArenaToArenaCommand implements Converter<Arena, ArenaCommand>
{

	@Synchronized
	@Nullable
	@Override
	public ArenaCommand convert(Arena source)
	{
		if (source == null)
		{
			return null;
		}

		final ArenaCommand arenaCommand = new ArenaCommand();
		arenaCommand.setId(source.getId());
		arenaCommand.setCity(source.getCity());
		arenaCommand.setCountry(source.getCountry());
		arenaCommand.setTimeZone(source.getTimeZone());
		arenaCommand.setName(source.getName());
		arenaCommand.setCapacity(source.getCapacity());
		arenaCommand.setActive(source.isActive());

		return arenaCommand;
	}

}

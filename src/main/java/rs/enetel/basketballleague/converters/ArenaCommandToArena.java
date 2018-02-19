package rs.enetel.basketballleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.Arena;

@Component
public class ArenaCommandToArena implements Converter<ArenaCommand, Arena>
{

	@Synchronized
	@Nullable
	@Override
	public Arena convert(ArenaCommand source)
	{
		if (source == null)
		{
			return null;
		}

		final Arena arena = new Arena();
		arena.setId(source.getId());
		arena.setCity(source.getCity());
		arena.setCountry(source.getCountry());
		arena.setTimeZone(source.getTimeZone());
		arena.setName(source.getName());
		arena.setCapacity(source.getCapacity());

		Boolean active = source.getActive();
		if (active != null)
		{
			arena.setActive(active);
		}
		else
		{
			arena.setActive(true);
		}

		return arena;
	}

}

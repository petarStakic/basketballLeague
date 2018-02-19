package rs.enetel.basketballleague.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.Arena;

public class ArenaCommandToArenaTest
{
	public static final int ID = 1;
	public static final String CITY = "Test City";
	public static final String COUNTRY = "Test Country";
	public static final String TZID = "GPGPG";
	public static final String NAME = "Test Arena";
	public static final Integer CAPACITY = 1122;
	public static final boolean PRIM_ACTIVE = false;
	public static final Boolean WRAP_ACTIVE = null;

	private ArenaCommandToArena converter;

	@Before
	public void setUp() throws Exception
	{
		converter = new ArenaCommandToArena();
	}

	@Test
	public void convertNullTest()
	{
		assertNull(converter.convert(null));
	}

	@Test
	public void convertEmptyObjectTest()
	{
		assertNotNull(converter.convert(new ArenaCommand()));
	}

	@Test
	public void convertActiveNullTest()
	{
		ArenaCommand arenaCommand = new ArenaCommand();
		arenaCommand.setActive(WRAP_ACTIVE);

		assertEquals(0, converter.convert(arenaCommand).getId());

		assertEquals(true, converter.convert(arenaCommand).isActive());
	}

	@Test
	public void convertTest()
	{
		ArenaCommand arenaCommand = new ArenaCommand();
		arenaCommand.setId(ID);
		arenaCommand.setCity(CITY);
		arenaCommand.setCountry(COUNTRY);
		arenaCommand.setTimeZone(TZID);
		arenaCommand.setName(NAME);
		arenaCommand.setCapacity(CAPACITY);
		arenaCommand.setActive(PRIM_ACTIVE);

		Arena arena = converter.convert(arenaCommand);
		assertNotNull(arena);
		assertEquals(ID, arena.getId());
		assertEquals(CITY, arena.getCity());
		assertEquals(COUNTRY, arena.getCountry());
		assertEquals(TZID, arena.getTimeZone());
		assertEquals(NAME, arena.getName());
		assertEquals(CAPACITY, arena.getCapacity());
		assertEquals(PRIM_ACTIVE, arena.isActive());
	}

}

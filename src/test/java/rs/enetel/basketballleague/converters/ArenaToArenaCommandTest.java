package rs.enetel.basketballleague.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.dao.Arena;

public class ArenaToArenaCommandTest
{
	public static final Integer ID = 1;
	public static final String CITY = "Test City";
	public static final String COUNTRY = "Test Country";
	public static final String TZID = "GPGPG";
	public static final String NAME = "Test Arena";
	public static final Integer CAPACITY = 1122;
	public static final boolean PRIM_ACTIVE = true;
	
	private ArenaToArenaCommand converter;
	
	@Before
	public void setUp() throws Exception
	{
		converter = new ArenaToArenaCommand();
	}
	
	@Test
	public void convertNullTest()
	{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void convertEmptyObjectTest()
	{
		assertNotNull(converter.convert(new Arena()));
	}
	
	@Test
	public void convertTest()
	{
		Arena arena = new Arena();
		arena.setId(ID);
		arena.setCity(CITY);
		arena.setCountry(COUNTRY);
		arena.setTimeZone(TZID);
		arena.setName(NAME);
		arena.setCapacity(CAPACITY);
		arena.setActive(PRIM_ACTIVE);
		
		ArenaCommand arenaCommand = converter.convert(arena);
		assertNotNull(arenaCommand);
		assertEquals(ID, arenaCommand.getId());
		assertEquals(CITY, arenaCommand.getCity());
		assertEquals(COUNTRY, arenaCommand.getCountry());
		assertEquals(TZID, arenaCommand.getTimeZone());
		assertEquals(NAME, arenaCommand.getName());
		assertEquals(CAPACITY, arenaCommand.getCapacity());
		assertEquals(PRIM_ACTIVE, arenaCommand.getActive());
	}
}

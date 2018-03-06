package rs.enetel.basketballleague.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.converters.ArenaCommandToArena;
import rs.enetel.basketballleague.converters.ArenaToArenaCommand;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.repositories.AppImageRepository;
import rs.enetel.basketballleague.repositories.ArenaRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class ArenaServiceImplTest
{
	public static final Integer TEST_ID = 11;
	public static final String TEST_NAME = "Test Arena";
	public static final String TEST_CITY = "Test City";
	public static final String TEST_COUNTRY = "Test Country";
	public static final String SUFF_1 = " 1";

	ArenaServiceImpl arenaService;

	@Mock
	ArenaRepository arenaRepository;

	@Mock
	ArenaCommandToArena arenaCommandToArena;

	@Mock
	ArenaToArenaCommand arenaToArenaCommand;
	
	@Mock
	AppImageRepository appImageRepository;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		arenaService = new ArenaServiceImpl(arenaCommandToArena, arenaToArenaCommand, arenaRepository, appImageRepository);
	}

	@Test
	public void addNewTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		ArenaCommand arenaCmdOut = new ArenaCommand();

		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);

		Arena arena = new Arena();
		Arena arenaInserted = new Arena();

		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);

		arenaInserted.setName(TEST_NAME);
		arenaInserted.setCity(TEST_CITY);
		arenaInserted.setCountry(TEST_COUNTRY);
		arenaInserted.setId(TEST_ID);
		arenaInserted.setActive(true);

		arenaCmdOut.setName(TEST_NAME);
		arenaCmdOut.setCity(TEST_CITY);
		arenaCmdOut.setCountry(TEST_COUNTRY);
		arenaCmdOut.setId(TEST_ID);

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);
		when(arenaRepository.searchFor(arena.getName(), arena.getCity(), arena.getCountry())).thenReturn(null);
		when(arenaRepository.add(arena)).thenReturn(arenaInserted);
		when(arenaToArenaCommand.convert(arenaInserted)).thenReturn(arenaCmdOut);

		// When
		ArenaCommand arenaCmdReturned = arenaService.addNew(arenaCmd);

		//Then
		assertEquals(TEST_NAME, arenaCmdReturned.getName());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_ID, arenaCmdReturned.getId());
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(1)).searchFor(arena.getName(), arena.getCity(), arena.getCountry());
		verify(arenaRepository, times(1)).add(arena);
		verify(arenaRepository, times(0)).edit(any());
		verify(arenaToArenaCommand, times(1)).convert(arenaInserted);
	}

	@Test
	public void addNewNullTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmdNull = null;

		when(arenaCommandToArena.convert(null)).thenReturn(null);

		// When
		ArenaCommand arenaCmdReturned = arenaService.addNew(arenaCmdNull);

		// Then
		assertNull(arenaCmdReturned);
		verify(arenaCommandToArena, times(1)).convert(arenaCmdNull);
		verify(arenaRepository, times(0)).searchFor(anyString(), anyString(), anyString());
		verify(arenaRepository, times(0)).add(any());
		verify(arenaRepository, times(0)).edit(any());
		verify(arenaToArenaCommand, times(0)).convert(any());
	}

	@Test
	public void addNewExistingTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);

		ArenaCommand arenaCmdOut = new ArenaCommand();
		arenaCmdOut.setName(TEST_NAME);
		arenaCmdOut.setCity(TEST_CITY);
		arenaCmdOut.setCountry(TEST_COUNTRY);
		arenaCmdOut.setId(TEST_ID);

		Arena arena = new Arena();
		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);

		Arena returnArena = new Arena();
		returnArena.setName(TEST_NAME);
		returnArena.setCity(TEST_CITY);
		returnArena.setCountry(TEST_COUNTRY);
		returnArena.setId(TEST_ID);
		returnArena.setActive(true);

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);
		when(arenaRepository.searchFor(arena.getName(), arena.getCity(), arena.getCountry())).thenReturn(returnArena);
		when(arenaToArenaCommand.convert(returnArena)).thenReturn(arenaCmdOut);

		// When
		ArenaCommand arenaCmdReturned = arenaService.addNew(arenaCmd);

		// Then
		assertEquals(TEST_NAME, arenaCmdReturned.getName());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_ID, arenaCmdReturned.getId());
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(1)).searchFor(arena.getName(), arena.getCity(), arena.getCountry());
		verify(arenaRepository, times(0)).add(any());
		verify(arenaRepository, times(0)).edit(any());
		verify(arenaToArenaCommand, times(1)).convert(returnArena);
	}

	@Test
	public void addNewExistingButDeletedTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);

		ArenaCommand arenaCmdOut = new ArenaCommand();
		arenaCmdOut.setName(TEST_NAME);
		arenaCmdOut.setCity(TEST_CITY);
		arenaCmdOut.setCountry(TEST_COUNTRY);
		arenaCmdOut.setId(TEST_ID);
		arenaCmdOut.setActive(true);

		Arena arena = new Arena();
		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);

		Arena returnArena = new Arena();
		returnArena.setName(TEST_NAME);
		returnArena.setCity(TEST_CITY);
		returnArena.setCountry(TEST_COUNTRY);
		returnArena.setId(TEST_ID);
		returnArena.setActive(false);

		Arena editedArena = new Arena();
		editedArena.setName(TEST_NAME);
		editedArena.setCity(TEST_CITY);
		editedArena.setCountry(TEST_COUNTRY);
		editedArena.setId(TEST_ID);
		editedArena.setActive(true);

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);
		when(arenaRepository.searchFor(arena.getName(), arena.getCity(), arena.getCountry())).thenReturn(returnArena);
		when(arenaRepository.edit(returnArena)).thenReturn(editedArena);
		when(arenaToArenaCommand.convert(returnArena)).thenReturn(arenaCmdOut);

		// When
		ArenaCommand arenaCmdReturned = arenaService.addNew(arenaCmd);

		// Then
		assertEquals(TEST_NAME, arenaCmdReturned.getName());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_CITY, arenaCmdReturned.getCity());
		assertEquals(TEST_ID, arenaCmdReturned.getId());
		assertEquals(true, arenaCmdReturned.getActive());
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(1)).searchFor(arena.getName(), arena.getCity(), arena.getCountry());
		verify(arenaRepository, times(0)).add(any());
		verify(arenaRepository, times(1)).edit(returnArena);
		verify(arenaToArenaCommand, times(1)).convert(returnArena);

	}

	@Test
	public void deactivateNullTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = null;
		Arena arena = null;

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);

		// When
		ArenaCommand deactivatedArena = arenaService.deactivate(arenaCmd);

		// then
		assertNull(deactivatedArena);
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(0)).edit(any());
	}

	@Test
	public void deactivateTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);
		arenaCmd.setActive(true);

		Arena arena = new Arena();
		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);
		arena.setActive(true);

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);

		// When
		ArenaCommand arenaCmdOut = arenaService.deactivate(arenaCmd);

		// Then
		assertEquals(false, arenaCmdOut.getActive());
		assertEquals(false, arena.isActive());
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(1)).edit(arena);
	}

	@Test
	public void editExistingNullTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmdNull = null;
		Arena arenaNull = null;

		when(arenaCommandToArena.convert(arenaCmdNull)).thenReturn(arenaNull);

		// When
		ArenaCommand arenaCmdOut = arenaService.editExistng(arenaCmdNull);

		// Then
		assertNull(arenaCmdOut);
		verify(arenaCommandToArena, times(1)).convert(arenaCmdNull);
		verify(arenaRepository, times(0)).edit(any());
		verify(arenaToArenaCommand, times(1)).convert(any());
	}

	@Test
	public void editExistingTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);

		Arena arena = new Arena();
		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);

		when(arenaCommandToArena.convert(arenaCmd)).thenReturn(arena);
		when(arenaRepository.edit(arena)).thenReturn(arena);
		when(arenaToArenaCommand.convert(arena)).thenReturn(arenaCmd);

		// When
		ArenaCommand arenaCmdOut = arenaService.editExistng(arenaCmd);

		// Then
		assertEquals(TEST_NAME, arenaCmdOut.getName());
		assertEquals(TEST_CITY, arenaCmdOut.getCity());
		assertEquals(TEST_CITY, arenaCmdOut.getCity());
		assertNull(arenaCmdOut.getId());
		verify(arenaCommandToArena, times(1)).convert(arenaCmd);
		verify(arenaRepository, times(1)).edit(arena);
		verify(arenaToArenaCommand, times(1)).convert(arena);

	}

	@Test
	public void getCommandByIdNullTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		Arena arena = new Arena();

		when(arenaRepository.getById(anyInt())).thenReturn(arena);
		when(arenaToArenaCommand.convert(arena)).thenReturn(arenaCmd);

		// When
		ArenaCommand arenaCmdOut = arenaService.getCommandById(null);

		// Then
		assertNull(arenaCmdOut);
		verify(arenaRepository, times(0)).getById(anyInt());
		verify(arenaToArenaCommand, times(0)).convert(any());
	}

	@Test
	public void getCommandByIdTest() throws Exception
	{
		// Given
		ArenaCommand arenaCmd = new ArenaCommand();
		arenaCmd.setName(TEST_NAME);
		arenaCmd.setCity(TEST_CITY);
		arenaCmd.setCountry(TEST_COUNTRY);
		arenaCmd.setId(TEST_ID);

		Arena arena = new Arena();
		arena.setName(TEST_NAME);
		arena.setCity(TEST_CITY);
		arena.setCountry(TEST_COUNTRY);
		arena.setId(TEST_ID);
		
		when(arenaRepository.getById(anyInt())).thenReturn(arena);
		when(arenaToArenaCommand.convert(arena)).thenReturn(arenaCmd);
		
		// When
		ArenaCommand arenaCmdOut = arenaService.getCommandById(TEST_ID);
		
		// Then
		assertEquals(TEST_NAME, arenaCmdOut.getName());
		assertEquals(TEST_CITY, arenaCmdOut.getCity());
		assertEquals(TEST_CITY, arenaCmdOut.getCity());
		assertEquals(TEST_ID, arenaCmdOut.getId());
		verify(arenaRepository, times(1)).getById(TEST_ID);
		verify(arenaToArenaCommand, times(1)).convert(arena);
	}
}

package rs.enetel.basketballleague.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.printers.ArenaPrintHelper;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;

// TODO: pitanje: kako unit-testirati metode koje koriste JdbcTemplate da komuniciraju sa bazom 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArenaRepositoryImplIT
{
	public static final Integer EXISTING_ID = 11;
	public static final String TEST_NAME = "Test Arena";
	public static final String TEST_CITY = "Test City";
	public static final String TEST_COUNTRY = "Test Country";
	public static final String EXISTING_NAME = "stark Arena";
	public static final String EXISTING_CITY = "belgrade";
	public static final String EXISTING_COUNTRY = "SERBIA";

	@Autowired
	ArenaRepositoryImpl arenaRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	IntegerResultSetExtractor intResultSetExtractor;

	@Before
	public void setUp() throws Exception
	{
		intResultSetExtractor = new IntegerResultSetExtractor();
	}

	@Test
	public void allTest() throws Exception
	{
		List<Arena> arenas = arenaRepository.all();
		String countQuery = "select count(*) from ARENAS";
		int numberOfRows = jdbcTemplate.query(countQuery, intResultSetExtractor);

		assertEquals(numberOfRows, arenas.size());
	}

	@Test
	public void getByIdTest()
	{
		Arena arena = arenaRepository.getById(EXISTING_ID);
		assertEquals(EXISTING_ID, arena.getId());
	}

	@Test
	public void searchTest() throws Exception
	{
		Arena arena = arenaRepository.searchFor(TEST_NAME, TEST_CITY, TEST_COUNTRY);
		assertNull(arena);

		arena = arenaRepository.searchFor(EXISTING_NAME, EXISTING_CITY, EXISTING_COUNTRY);
		assertNotNull(arena);
		assertEquals(EXISTING_NAME.toLowerCase(), arena.getName().toLowerCase());
		assertEquals(EXISTING_CITY.toLowerCase(), arena.getCity().toLowerCase());
		assertEquals(EXISTING_COUNTRY.toLowerCase(), arena.getCountry().toLowerCase());

	}

}

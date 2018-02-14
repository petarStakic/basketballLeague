package rs.enetel.basketballleague.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;

// TODO: pitanje: kako unit-testirati metode koje koriste JdbcTemplate da komuniciraju sa bazom 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArenaRepositoryImplIT
{
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

}

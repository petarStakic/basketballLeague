package rs.enetel.basketballleague.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rs.enetel.basketballleague.commands.ArenaCommand;
import rs.enetel.basketballleague.converters.ArenaCommandToArena;
import rs.enetel.basketballleague.converters.ArenaToArenaCommand;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.printers.ArenaPrintHelper;
import rs.enetel.basketballleague.repositories.ArenaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArenaServiceImplIT
{
	@Autowired
	ArenaServiceImpl arenaService;
	
	@Autowired
	ArenaRepository arenaRepository;
	
	@Autowired
	ArenaCommandToArena arenaCommandToArena;
	
	@Autowired
	ArenaToArenaCommand arenaToArenaCommand;
	
	@Test
	public void addNewTest() throws Exception {
		List<Arena> allArenas = arenaRepository.all();
		Arena someArena = allArenas.get(14);
		ArenaCommand someArenaCmd = arenaToArenaCommand.convert(someArena);
		
		ArenaCommand arenaCmdInserted = arenaService.addNew(someArenaCmd);
		Arena arenaInserted = arenaCommandToArena.convert(arenaCmdInserted);
		
		List<Arena> arenas = new ArrayList<>();
		arenas.add(someArena);
		arenas.add(arenaInserted);
		
		ArenaPrintHelper.pirintInConsole(arenas);
		assertEquals(someArena.getId(), arenaInserted.getId());
		assertEquals(someArena.getCity(), arenaInserted.getCity());
		assertEquals(someArena.getCountry(), arenaInserted.getCountry());
		assertEquals(someArena.getTimeZone(), arenaInserted.getTimeZone());
		assertEquals(someArena.getName(), arenaInserted.getName());
		assertEquals(someArena.getCapacity(),arenaInserted.getCapacity());
		
	}
}

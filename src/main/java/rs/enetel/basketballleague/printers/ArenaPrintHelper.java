package rs.enetel.basketballleague.printers;

import java.util.List;

import rs.enetel.basketballleague.dao.Arena;

public class ArenaPrintHelper
{
	public static void pirintInConsole(List<Arena> arenas)
	{
		for(Arena arena : arenas) {
			System.out.println("ID:                " + arena.getId());
			System.out.println("CITY:              " + arena.getCity());
			System.out.println("COUNTRY:           " + arena.getCountry());
			System.out.println("TIME ZONE:         " + arena.getTimeZone());
			System.out.println("NAME:              " + arena.getName());
			System.out.println("CAPACITY:          " + arena.getCapacity());
			System.out.println("ACTIVE:            " + arena.isActive());
			System.out.println("***********************************************");
		}
	}
}

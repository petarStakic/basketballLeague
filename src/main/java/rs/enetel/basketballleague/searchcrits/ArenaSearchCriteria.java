package rs.enetel.basketballleague.searchcrits;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArenaSearchCriteria
{
	private String country;
	private String city;
	private String timeZone;
	private String name;
	private Integer minCapacity;
	private Integer maxCapacity;
	private boolean active = true;
	private ArenaOrder orderBy = ArenaOrder.NAME;
	private boolean descending = false;
}

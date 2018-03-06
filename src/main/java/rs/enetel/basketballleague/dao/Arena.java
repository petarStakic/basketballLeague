package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Arena
{
	private Integer id;
	private String city;
	private String country;
	private String timeZone; // razmisliti o vremenskim zonama
	private String name;
	private Integer capacity;
	private boolean active;
	private AppImage image;
}

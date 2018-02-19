package rs.enetel.basketballleague.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArenaCommand
{
	private int id;
	private String city;
	private String country;
	private String timeZone;
	private String name;
	private Integer capacity;
	private Boolean active;
}

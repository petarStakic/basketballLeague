package rs.enetel.basketballleague.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.enetel.basketballleague.dao.AppImage;

@Getter
@Setter
@NoArgsConstructor
public class ArenaCommand
{
	private Integer id;
	private String city;
	private String country;
	private String timeZone;
	private String name;
	private Integer capacity;
	private Boolean active;
	private AppImage image;
	private String imageChanged;
}

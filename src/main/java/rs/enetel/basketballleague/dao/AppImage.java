package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppImage
{
	private Integer id;
	private String description;
	private Byte[] content;
	private String typeExtension;
}

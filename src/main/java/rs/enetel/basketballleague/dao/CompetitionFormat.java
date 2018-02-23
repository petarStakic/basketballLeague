package rs.enetel.basketballleague.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionFormat
{
	private Integer id;
	private String type;
	private String description;
	private Integer matchesPerFixture;
}

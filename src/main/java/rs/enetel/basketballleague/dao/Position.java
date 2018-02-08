package rs.enetel.basketballleague.dao;

public enum Position
{
	PG(1, "PG"), SG(2, "SG"), SF(3, "SF"), PF(4, "PF"), C(5, "C");

	private final int id;
	private final String abbreviation;

	private Position(int id, String abbreviation)
	{
		this.id = id;
		this.abbreviation = abbreviation;
	}

	public int getId()
	{
		return id;
	}

	public String getAbbreviation()
	{
		return abbreviation;
	}

}

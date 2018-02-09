package rs.enetel.basketballleague.searchcrits;

public enum ArenaOrder
{
	CITY("city"), COUNTRY("country"), NAME("name"), CAPACITY("capacity");

	private final String columnName;

	private ArenaOrder(String columnName)
	{
		this.columnName = columnName;
	}

	public String getColumnName()
	{
		return columnName;
	}

}

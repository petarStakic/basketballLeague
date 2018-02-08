package rs.enetel.basketballleague.dao;

public enum Scores
{
	FREE_THROW(1), TWO_POINTS(2), THREE_POINTS(3);

	private final int points;

	Scores(int points)
	{
		this.points = points;
	}

	public int getPoints()
	{
		return points;
	}
}

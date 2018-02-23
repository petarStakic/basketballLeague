package rs.enetel.basketballleague.repositories;

import java.util.List;

import rs.enetel.basketballleague.dao.CompetitionFormat;

public interface CompetitionFormatRepository
{
	CompetitionFormat add(CompetitionFormat competitionFormat) throws Exception;
	
	CompetitionFormat getById(int id) throws Exception;
	
	List<CompetitionFormat> all() throws Exception;
	
	CompetitionFormat edit(CompetitionFormat competitionFormat) throws Exception;
}

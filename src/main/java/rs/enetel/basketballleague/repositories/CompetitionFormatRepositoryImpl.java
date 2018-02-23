package rs.enetel.basketballleague.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.ResourceHelper;
import rs.enetel.basketballleague.dao.CompetitionFormat;
import rs.enetel.basketballleague.dao.EliminationCompetitionFormat;
import rs.enetel.basketballleague.dao.GroupCompetitionFormat;
import rs.enetel.basketballleague.rowmapper.CompetitionFormatRowMapper;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;

@Repository
@Slf4j
public class CompetitionFormatRepositoryImpl implements CompetitionFormatRepository
{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public CompetitionFormat add(CompetitionFormat competitionFormat) throws Exception
	{
		String nextvalSql = ResourceHelper.getResourceText(
				"leagues/seasons/tournament_formats/tournament_phases/competition_formats/sequence_next.sql");
		String insertSql = ResourceHelper
				.getResourceText("leagues/seasons/tournament_formats/tournament_phases/competition_formats/");

		int id = jdbcTemplate.query(nextvalSql, new IntegerResultSetExtractor());
		competitionFormat.setId(id);

		Integer numberOfGroups = null;
		String tiebraker = null;

		if (competitionFormat instanceof GroupCompetitionFormat)
		{
			numberOfGroups = ((GroupCompetitionFormat) competitionFormat).getNumberOfGroups();
		}

		if (competitionFormat instanceof EliminationCompetitionFormat)
		{
			tiebraker = ((EliminationCompetitionFormat) competitionFormat).getTiebraker();
		}

		int rowsAffected = jdbcTemplate.update(insertSql,
				new Object[] { id, competitionFormat.getType(), competitionFormat.getDescription(),
						competitionFormat.getMatchesPerFixture(), numberOfGroups, tiebraker });

		if(rowsAffected != 1)
		{
			log.debug("Error inserting competition format. Rows affected: " + rowsAffected + ", expected: 1");
			throw new Exception("Error inserting competition format. Rows affected: " + rowsAffected + ", expected: 1");
		}
		
		return competitionFormat;
	}

	@Override
	public CompetitionFormat getById(int id) throws Exception
	{
		String sql = ResourceHelper.getResourceText("leagues/seasons/tournament_formats/tournament_phases/competition_formats/by_id.sql");
		
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new CompetitionFormatRowMapper());
	}

	@Override
	public List<CompetitionFormat> all() throws Exception
	{
		String sql = ResourceHelper.getResourceText("leagues/seasons/tournament_formats/tournament_phases/competition_formats/all.sql");
		
		return jdbcTemplate.query(sql, new CompetitionFormatRowMapper());
	}

	@Override
	public CompetitionFormat edit(CompetitionFormat competitionFormat) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}

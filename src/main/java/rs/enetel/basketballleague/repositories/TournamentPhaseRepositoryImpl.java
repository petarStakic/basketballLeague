package rs.enetel.basketballleague.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.ResourceHelper;
import rs.enetel.basketballleague.dao.CompetitionFormat;
import rs.enetel.basketballleague.dao.Season;
import rs.enetel.basketballleague.dao.TournamentPhase;
import rs.enetel.basketballleague.rowmapper.TournamentPhaseRowMapper;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;
import rs.enetel.basketballleague.rsextractors.LongResultSetExtractor;
import rs.enetel.basketballleague.rsextractors.TournamentPhaseResultSetExtractor;

@Slf4j
@Repository
public class TournamentPhaseRepositoryImpl implements TournamentPhaseRepository
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CompetitionFormatRepository competitionFormatRepository;

	@Transactional
	@Override
	public TournamentPhase add(TournamentPhase phase) throws Exception
	{
		String sequenceSql = ResourceHelper.getResourceText("/sql/leagues/seasons/tournament_formats/tournament_phases/sequence_next.sql");
		String insertSql = ResourceHelper.getResourceText("/sql/leagues/seasons/tournament_formats/tournament_phases/add.sql");
		Long id = null;
		
		Integer seasonId = phase.getSeason().getId();
		Integer phaseNum = phase.getPhaseNum();
		CompetitionFormat competitionFormat = phase.getFormat();
		
		if(seasonId == null || phaseNum == null)
		{
			throw new Exception("Phase must be link to a particular season and it must have a phase number!");
		}
		
		id = getForSeasonIdAndPhaseNumber(seasonId, phaseNum);
		
		if(id != null)
		{
			phase.setId(id);
			return phase;
		}
		
		competitionFormat = competitionFormatRepository.add(competitionFormat);
		
		id = jdbcTemplate.query(sequenceSql, new LongResultSetExtractor());
		phase.setId(id);
		
		Integer rowsAffected = jdbcTemplate.update(insertSql, new Object[] {id, phaseNum, phase.getStartDate(),
				phase.getEndDate(), competitionFormat.getId()});
		
		if(rowsAffected != 1)
		{
			log.debug("Error inserting tournament phase. Rows affected: " + rowsAffected + ", expected 1");
			throw new Exception("Error inserting tournament phase. Rows affected: " + rowsAffected + ", expected 1");
		}
		
		return phase;
	}

	@Transactional
	@Override
	public TournamentPhase getById(int id) throws Exception
	{
		// TODO set all season fields for phase before returning
		String sql = ResourceHelper.getResourceText("/sql/leagues/seasons/tournament_formats/tournament_phases/by_id.sql");
		
		TournamentPhase phase = jdbcTemplate.query(sql, new TournamentPhaseResultSetExtractor());
		
		Integer formatId = phase.getFormat().getId();
		phase.setFormat(competitionFormatRepository.getById(formatId));
		
		return phase;
	}

	@Transactional
	@Override
	public List<TournamentPhase> allForSeason(Season season) throws Exception
	{
		String sql = ResourceHelper.getResourceText("/sql/leagues/seasons/tournament_formats/tournament_phases/by_season_id.sql");
		
		List<TournamentPhase> phases = jdbcTemplate.query(sql, new Object[] {season.getId()}, new TournamentPhaseRowMapper());
		
		for(TournamentPhase phase : phases) {
			phase.setSeason(season);
		}
		
		return phases;
	}

	@Transactional
	@Override
	public TournamentPhase edit(TournamentPhase phase) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Long getForSeasonIdAndPhaseNumber(int seasonId, int phaseNum)
	{
		String sql = ResourceHelper.getResourceText("/sql/leagues/seasons/tournament_formats/tournament_phases/by_season_id_and_phase_num.sql");
		
		return jdbcTemplate.query(sql, new Object[] {phaseNum, seasonId}, new LongResultSetExtractor());
	}
}

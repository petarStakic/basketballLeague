package rs.enetel.basketballleague.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.ResourceHelper;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.rowmapper.ArenaRowMapper;
import rs.enetel.basketballleague.searchcrits.ArenaSearchCriteria;

@Repository
@Slf4j
public class ArenaRepositoryImpl implements ArenaRepository
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Arena add(Arena arena) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Arena> all() throws Exception
	{
		String sql = ResourceHelper.getResourceText("/sql/arenas/all.sql");
		
		log.debug("In the arena repository all() method!");
		
		return jdbcTemplate.query(sql, new ArenaRowMapper());
	}

	@Override
	public List<Arena> search(ArenaSearchCriteria criteria) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arena searchFor(String name, String city, String country) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arena edit(Arena arena) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arena remove(Arena arena) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}

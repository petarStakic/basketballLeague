package rs.enetel.basketballleague.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.ResourceHelper;
import rs.enetel.basketballleague.dao.Arena;
import rs.enetel.basketballleague.rowmapper.ArenaRowMapper;
import rs.enetel.basketballleague.rsextractors.ArenaResultSetExtractor;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;
import rs.enetel.basketballleague.searchcrits.ArenaSearchCriteria;

@Repository
@Slf4j
public class ArenaRepositoryImpl implements ArenaRepository
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public Arena add(Arena arena) throws Exception
	{
		String nextvalSql = ResourceHelper.getResourceText("/sql/arenas/sequence_next.sql");
		String insertSql = ResourceHelper.getResourceText("/sql/arenas/add.sql");

		log.debug("In the arena repository's add(arena) method!");

		int id = jdbcTemplate.query(nextvalSql, new IntegerResultSetExtractor());
		arena.setId(id);
		arena.setActive(true);

		int rowsAffected = jdbcTemplate.update(insertSql, new Object[] { id, arena.getCountry(), arena.getCity(),
				arena.getTimeZone(), arena.getName(), arena.getCapacity() });

		if (rowsAffected != 1)
		{
			log.debug("Error inserting an arena. Rows affected: " + rowsAffected + " instead of 1.");
			throw new Exception("Number of rows affected is not equal to one!");
		}
		return arena;
	}

	@Override
	public Arena getById(int id)
	{
		String sql = ResourceHelper.getResourceText("/sql/arenas/by_id.sql");

		log.debug("In the arena repository getbyId() method");

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new ArenaRowMapper());

	}

	@Override
	public List<Arena> all()
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
		String sql = ResourceHelper.getResourceText("/sql/arenas/by_name_city_country.sql");

		log.debug("In the arena repository searchFor(" + name + ", " + city + ", " + country + ") method!");

		return jdbcTemplate.query(sql, new Object[] { name, city, country }, new ArenaResultSetExtractor());
	}

	@Override
	public Arena edit(Arena arena) throws Exception
	{
		String sql = ResourceHelper.getResourceText("/sql/arenas/edit.sql");

		log.debug("In the arena repository's edit(arena) method!");

		int activeInt = arena.isActive() ? 1 : 0;

		int rowsAffected = jdbcTemplate.update(sql, new Object[] { arena.getCountry(), arena.getCity(),
				arena.getTimeZone(), arena.getName(), arena.getCapacity(), activeInt, arena.getId() });

		if (rowsAffected != 1)
		{
			log.debug("Error updating an arena. Rows affected: " + rowsAffected + " instead of 1.");
			throw new Exception("Number of rows affected is not equal to one!");
		}

		return arena;
	}

	@Override
	public Arena remove(Arena arena) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}

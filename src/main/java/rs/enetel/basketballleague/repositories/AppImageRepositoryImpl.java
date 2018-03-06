package rs.enetel.basketballleague.repositories;

import java.sql.Types;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rs.enetel.basketballleague.common.ResourceHelper;
import rs.enetel.basketballleague.dao.AppImage;
import rs.enetel.basketballleague.rowmapper.AppImageRowMapper;
import rs.enetel.basketballleague.rsextractors.IntegerResultSetExtractor;

@Slf4j
@Repository
public class AppImageRepositoryImpl implements AppImageRepository
{

	@Autowired
	JdbcTemplate jdbcTemplate;

	LobHandler lobHandler = new DefaultLobHandler();

	private static final String SQL_DIR = "/sql/app_images/";

	@Transactional
	@Override
	public AppImage add(AppImage img)
	{
		String sequenceSql = ResourceHelper.getResourceText(SQL_DIR + "sequence_next.sql");
		String insertSql = ResourceHelper.getResourceText(SQL_DIR + "add.sql");

		int id = jdbcTemplate.query(sequenceSql, new IntegerResultSetExtractor());
		img.setId(id);

		int rowsAffected = jdbcTemplate.update(insertSql,
				new Object[] { id, img.getDescription(),
						new SqlLobValue(ArrayUtils.toPrimitive(img.getContent()), lobHandler), img.getTypeExtension() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.BLOB, Types.VARCHAR });

		if (rowsAffected != 1)
		{
			log.debug("Error inserting new image. Rows affected = " + rowsAffected + ", but 1 expected");
			throw new RuntimeException(
					"Error inserting new image. Rows affected = " + rowsAffected + ", but 1 expected");
		}

		return img;
	}

	@Transactional
	@Override
	public AppImage getById(int id)
	{
		String sql = ResourceHelper.getResourceText(SQL_DIR + "by_id.sql");

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new AppImageRowMapper());
	}

	@Override
	public AppImage edit(AppImage img)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

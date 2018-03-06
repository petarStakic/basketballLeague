package rs.enetel.basketballleague.rowmapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.RowMapper;

import rs.enetel.basketballleague.dao.AppImage;

public class AppImageRowMapper implements RowMapper<AppImage>
{

	@Override
	public AppImage mapRow(ResultSet results, int row) throws SQLException
	{
		AppImage img = new AppImage();
		Blob blobContent = results.getBlob("CONTENT");
		
		img.setId(results.getInt("ID"));
		img.setDescription(results.getString("DESCRIPTION"));
		img.setContent(ArrayUtils.toObject(blobContent.getBytes(1, (int) blobContent.length())));
		img.setTypeExtension(results.getString("TYPE_EXTENSION"));
		
		return img;
	}

}

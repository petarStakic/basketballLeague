package rs.enetel.basketballleague.repositories;

import rs.enetel.basketballleague.dao.AppImage;

public interface AppImageRepository
{
	AppImage add(AppImage img);
	
	AppImage getById(int id);
	
	AppImage edit(AppImage img);
}

package util.factory;

import dao.AbstractDao;
import dao.LikesDao;

public class LikesDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new LikesDao();
	}
}
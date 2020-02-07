package util.factory;

import dao.AbstractDao;
import dao.FollowDao;

public class FollowDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new FollowDao();
	}
}
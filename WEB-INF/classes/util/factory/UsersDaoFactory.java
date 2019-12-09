package util.factory;

import dao.AbstractDao;
import dao.UsersDao;

public class UsersDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new UsersDao();
	}
}
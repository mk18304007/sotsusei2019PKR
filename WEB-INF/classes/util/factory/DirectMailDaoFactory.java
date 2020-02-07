package util.factory;

import dao.AbstractDao;
import dao.DirectMailDao;

public class DirectMailDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new DirectMailDao();
	}
}
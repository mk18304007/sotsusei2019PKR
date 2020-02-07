package util.factory;

import dao.AbstractDao;
import dao.ActionDao;

public class ActionDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new ActionDao();
	}
}
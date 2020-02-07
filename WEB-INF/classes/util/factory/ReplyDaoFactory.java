package util.factory;

import dao.AbstractDao;
import dao.ReplyDao;

public class ReplyDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new ReplyDao();
	}
}
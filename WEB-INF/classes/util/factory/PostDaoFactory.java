package util.factory;

import dao.AbstractDao;
import dao.PostDao;

public class PostDaoFactory extends AbstractDaoFactory{
	public AbstractDao getAbstractDao(){
		return new PostDao();
	}
}
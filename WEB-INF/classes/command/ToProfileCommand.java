/*----------���O�C�����邽�߂̃R�}���h---------*/
package command;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class ToProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//�]��������Z�b�g����
		resc.setTarget("profile");
		return resc;
	}
}
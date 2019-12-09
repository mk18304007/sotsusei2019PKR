/*----------���[�U�[�o�^���邽�߂̃R�}���h---------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class SignUpCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		
		//RequestContext�������
		String name=reqc.getParameter("name")[0];
		String userId=reqc.getParameter("userId")[0];
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confilm=reqc.getParameter("password")[1];
		
		//�p�X���[�h�Ɗm�F�p�p�X���[�h����v����Ƃ�
		/*if(password.equals(confilm)){
			//�{���͗�O�𑗏o����
			System.out.println("�p�X���[�h����v���܂���");
		}*/
		//�o�^����l���}�b�v�Ɋi�[
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("name",name);
		palams.put("userId",userId);
		palams.put("mailAddress",mailAddress);
		palams.put("password",password);
		
		//�g�����U�N�V�������J�n����
		OracleConnectionManager.getInstance().beginTransaction();
		
		//�C���e�O���[�V�������C���̏������Ăяo��
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		dao.insert(palams);
		
		//�g�����U�N�V�������I������
		//OracleConnectionManager.getInstance().commit();
		
		//�R�l�N�V������ؒf����
		//OracleConnectionManager.getInstance().closeConnection();
		
		//�o�^�����������玩���Ń��O�C���������̂ŁALoginCommand���Ă�
		LoginCommand lc=new LoginCommand();
		lc.init(reqc);
		resc=lc.execute(resc);
		
		Map result=new HashMap();
		result.put("user",resc.getResult());
		resc.setResult(result);
		return resc;
	}
}
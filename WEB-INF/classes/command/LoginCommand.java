/*----------���O�C�����邽�߂̃R�}���h---------*/
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

public class LoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContext�̃C���X�^���X���擾����
		RequestContext reqc=getRequestContext();
		
		//RequestContext������̓p�����[�^���擾����
		String userId=reqc.getParameter("userId")[0];
		String password=reqc.getParameter("password")[0];
		
		//����p��where���userID��Map�Ɋi�[����
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("value",userId);
		palams.put("where","where userId=?");
		
		//�g�����U�N�V�������J�n����
		OracleConnectionManager.getInstance().beginTransaction();
		
		//�C���e�O���[�V�������C���̏������Ăяo��
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		UserBean ub=(UserBean)dao.read(palams);
		
		//���̓p�����[�^��DB����擾�����p�X���[�h��������������
		if(password.equals(ub.getPassword())){
			//��v����ꍇHome�֓]��
			Map<String,Bean> result=new HashMap<String,Bean>();
			result.put("user",ub);
			resc.setResult(result);
			resc.setTarget("home");
			
		}else{
			//��v���Ȃ��ꍇ�A���O�C���y�[�W��(��)�{���͗�O�𑗏o����
			Map<String,String> result=new HashMap<String,String>();
			result.put("failed","failed");
			resc.setResult(result);
			resc.setTarget("login");
			System.out.println("ID���p�X���[�h���Ⴂ�܂�");
		}
		
		//�g�����U�N�V�������I������
		//OracleConnectionManager.getInstance().commit();
		
		//�R�l�N�V������ؒf����
		//OracleConnectionManager.getInstance().closeConnection();
		
		return resc;
	}
}
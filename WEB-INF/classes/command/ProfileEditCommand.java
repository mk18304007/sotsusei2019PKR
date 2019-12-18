/*----------�v���t�B�[������ҏW���邽�߂̃R�}���h----------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;

import dao.AbstractDao;

public class ProfileEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContext�̃C���X�^���X���擾����
		RequestContext reqc=getRequestContext();
		
		//��ӂȒl���X�V����ꍇ�ł��ύX�ł���悤��
		//�Z�b�V���������L�[���擾����
		SessionManager session=new SessionManager(reqc);
		String managementId=((UserBean)session.getAttribute("user")).getManagementId();
		
		//RequestContext������̓p�����[�^���󂯎��
		String userName=reqc.getParameter("userName")[0];
		String userId=reqc.getParameter("userId")[0];
		String profile=reqc.getParameter("profile")[0];
		System.out.println(userName);
		//String profilePicture=reqc.getParameter("profilePicture")[0];
		
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confirm=reqc.getParameter("password")[1];
		String release=reqc.getParameter("release")[0];
		
		//�p�X���[�h�Ɗm�F�p�p�X���[�h����v���Ȃ��Ƃ�
		/*if(password.equals(confilm)==false){
			//�{���͗�O�𑗏o����
			System.out.println("�p�X���[�h����v���܂���");
		}*/
		//�X�V����l���}�b�v�Ɋi�[
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("userName",userName);
		palams.put("userId",userId);
		palams.put("profile",profile);
		palams.put("mailAddress",mailAddress);
		palams.put("password",password);
		palams.put("release",release);
		
		//�X�V����p��where���Map�Ɋi�[
		palams.put("where","where managementId="+managementId);
		
		//�g�����U�N�V�������J�n����
		OracleConnectionManager.getInstance().beginTransaction();
		
		//�C���e�O���[�V�������C���̏������Ăяo��
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		//�X�V����
		dao.update(palams);
		
		//�X�V��̃f�[�^���擾����
		//where��͓������̂��g����
		UserBean ub=(UserBean)dao.read(palams);
		
		//�g�����U�N�V�������I������
		OracleConnectionManager.getInstance().commit();
		
		//�Z�b�V�����̂������ŐV�̂��̂ɂ���
		session=new SessionManager(reqc);
		session.setAttribute("user",ub);
		
		//�X�V��̓v���t�B�[���y�[�W�֔�΂�
		Map<String,Bean> result=new HashMap<String,Bean>();
		result.put("user",ub);
		resc.setResult(result);
		resc.setTarget("profile");
		
		return resc;
	}
}
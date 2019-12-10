package command;

import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;

import java.text.SimpleDateFormat;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;
import bean.PostBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class PostCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContext�̃C���X�^���X���擾����
		RequestContext reqc=getRequestContext();
		
		//SessionManager�̃C���X�^���X�𐶐����A�Z�b�V�����̎����[�U�[�����擾����
		SessionManager session=new SessionManager(reqc);
		String managementId=((UserBean)session.getAttribute("user")).getManagementId();
		
		//RequestContext����p�����[�^���擾����
		String contents=reqc.getParameter("contents")[0];
		String text=reqc.getParameter("text")[0];
		
		//DB�Ɋi�[������e��Map�Ɋi�[
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("managementID",managementId);
		palams.put("contents",contents);
		palams.put("text",text);
		
		//�g�����U�N�V�������J�n����
		OracleConnectionManager.getInstance().beginTransaction();
		
		//�C���e�O���[�V�������C���̏������Ăяo��
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
		AbstractDao dao=factory.getAbstractDao();
		dao.insert(palams);
		
		//�g�����U�N�V�������I������
		OracleConnectionManager.getInstance().commit();
		
		resc.setTarget("home");
		return resc;
	}
}
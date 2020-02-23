/*----------�ԐM��ʂɈړ����邽�߂̃R�}���h----------*/
package command;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.ReplyBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.PostManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class ToReplyCommand extends AbstractCommand
{
	public ResponseContext execute(ResponseContext resc)
	{
		//RequestContext�̃C���X�^���X���擾����
		RequestContext reqc = getRequestContext();
		System.out.println("36�s��: ToReplyCommand��reqc��" + reqc);
		
		//DB�Ɋi�[������e��Map�Ɋi�[
		Map<String,String> palams = new HashMap<String,String>();
		
//		palams.put("1st", "nothing");
		
		System.out.println("42�s��: ToReplyCommand��palams��" + palams);
		String post_id= reqc.getParameter("post_id")[0];
		System.out.println("post_id�� " + post_id);
		palams.put("value",post_id);
		
//		if(palams.get("1st") != "nothing")
//		{
			//�g�����U�N�V�������J�n����
			OracleConnectionManager.getInstance().beginTransaction();

			//�C���e�O���[�V�������C���̏������Ăяo��
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("post");
			AbstractDaoFactory Rfactory = AbstractDaoFactory.getFactory("reply");
			AbstractDao dao = factory.getAbstractDao();
			AbstractDao Rdao = Rfactory.getAbstractDao();
		
			//ArrayList list = Arrays.(dao.read(palams));
			//List list = dao.readAll(palams);
			Bean replybean = dao.read(palams);
			Map rmap = new HashMap();
			rmap.put("post_id", post_id);
			List replyList = Rdao.readAll(rmap);
		
			System.out.println("ToReplyCommand����replybean�� " + replybean);
			/*
			 *WebRequestContext��result�ɓ���
			 *WebApplicationController��handleResponse()��
			 *req.setAttribute("data",resc.getResult())�œo�^����Key��
			 *jsp�� c:forEach�^�O�� items="${data}" �ɍ��킹��
			 */
			//resc.setResult(replybean);

			//�g�����U�N�V�������I������
			OracleConnectionManager.getInstance().commit();
//		}
		resc.add(replybean);
		resc.add(replyList);
		resc.setTarget("reply");
		
		return resc;
	}
}
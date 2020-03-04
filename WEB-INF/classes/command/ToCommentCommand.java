/*----------返信画面に移動するためのコマンド----------*/
package command;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.ReplyBean;
import bean.ReplyDataBean;
import bean.UsersPostLikesBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.PostManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class ToCommentCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc=getRequestContext();
			
			//投稿のIDを取得
			String postId=reqc.getParameter("postId")[0];
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//投稿内容を取得
			Map<String,String> palams=new HashMap<>();
			palams.put("where"," WHERE postId="+postId);
			
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
			AbstractDao dao=factory.getAbstractDao();
			PostBean pb=(PostBean)dao.read(palams);
			
			//リプライ内容を取得
			factory=AbstractDaoFactory.getFactory("reply");
			dao=factory.getAbstractDao();
			ArrayList replyList=(ArrayList)dao.readAll(palams);
			
			//全ユーザーを取得し、投稿・リプライそれぞれのmanagementIdと合致するユーザーを対応したBeanへ格納する
			palams.clear();
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			ArrayList usersList=(ArrayList)dao.readAll(palams);
			
			UsersPostLikesBean postData=new UsersPostLikesBean();
			List<ReplyDataBean> replyDataList = new ArrayList<>();
			for(int i=0;i<usersList.size();i++){
				UsersBean ub=(UsersBean)usersList.get(i);
				//投稿と一致するとき
				if(pb.getManagementId().equals(ub.getManagementId())){
					//投稿の内容を取得して、記号や改行を置換する
					String text = pb.getText();
					text = text.replaceAll("&amp;","&amp;amp;");
					text = text.replaceAll("&lt;","&amp;lt;");
					text = text.replaceAll("&gt;","&amp;gt;");
					text = text.replaceAll("&quot;","&amp;quot;");
					text = text.replaceAll("&apos;","&amp;apos;");
					text = text.replaceAll("&nbsp;","&amp;nbsp;");
					text = text.replaceAll("\"","&quot;");
					text = text.replaceAll("'","&apos;");
					text = text.replaceAll("<","&lt;");
					text = text.replaceAll(">","&gt;");
					text = text.replaceAll("\n","<br>");
					pb.setText(text);
					postData.setUsersBean(ub);
					postData.setPostBean(pb);
				}
			}
			for(int i=0;i<replyList.size();i++){
				ReplyBean rb=(ReplyBean)replyList.get(i);
				ReplyDataBean replyData=new ReplyDataBean();
				for(int j=0;j<usersList.size();j++){
					UsersBean ub=(UsersBean)usersList.get(j);
					//リプライと一致するとき
					if(rb.getManagementId().equals(ub.getManagementId())){
						//リプライのの内容を取得して、記号や改行を置換する
						String text = rb.getReply();
						text = text.replaceAll("&amp;","&amp;amp;");
						text = text.replaceAll("&lt;","&amp;lt;");
						text = text.replaceAll("&gt;","&amp;gt;");
						text = text.replaceAll("&quot;","&amp;quot;");
						text = text.replaceAll("&apos;","&amp;apos;");
						text = text.replaceAll("&nbsp;","&amp;nbsp;");
						text = text.replaceAll("\"","&quot;");
						text = text.replaceAll("'","&apos;");
						text = text.replaceAll("<","&lt;");
						text = text.replaceAll(">","&gt;");
						text = text.replaceAll("\n","<br>");
						rb.setReply(text);
						replyData.setUsersBean(ub);
						replyData.setReplyBean(rb);
					}
				}
				replyDataList.add(replyData);
			}
			//投稿、リプライをそれぞれjspに渡す
			List<Object> first=new ArrayList<>();
			first.add("postData");
			first.add(postData);
			
			List<Object> second=new ArrayList<>();
			second.add("replyList");
			second.add(replyDataList);
			
			List<List> result=new ArrayList<>();
			result.add(first);
			result.add(second);
			resc.setResult(result);
			
			//転送先情報をセットする
			resc.setTarget("reply");
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
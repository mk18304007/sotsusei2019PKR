/*----------ログインするためのコマンド---------*/
package command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import context.ResponseContext;
import context.RequestContext;

import bean.Bean;
import bean.ActionBean;
import bean.UsersBean;
import bean.PostBean;
import bean.LikesBean;
import bean.UsersPostLikesBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class ToProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			//RequestContextのインスタンスを取得する
			RequestContext reqc=getRequestContext();
			
			//ログインしているユーザーの情報をセッションから取得する
			SessionManager session = new SessionManager(reqc);
			String sessionUserId=((UsersBean)session.getAttribute("user")).getManagementId();
			
			//選択されたユーザーの情報をjspから取得する
			String selectedUserId=reqc.getParameter("managementId")[0];
			if(sessionUserId.equals(selectedUserId)){
				//判定用のwhere句とuserIDをMapに格納する
				Map<String,String> palams=new HashMap<String,String>();
				
				//トランザクションを開始する
				OracleConnectionManager.getInstance().beginTransaction();
				
				//インテグレーションレイヤの処理を呼び出す
				palams.put("where"," WHERE managementId=? ORDER BY postId DESC");
				palams.put("value",sessionUserId);
				
				AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
				AbstractDao dao=factory.getAbstractDao();
				ArrayList postList = (ArrayList)dao.readAll(palams);
				
				palams.put("where"," WHERE managementId=?");
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				factory=AbstractDaoFactory.getFactory("likes");
				dao=factory.getAbstractDao();
				ArrayList likesList = (ArrayList)dao.readAll(palams);
				
				//トランザクションを終了する
				OracleConnectionManager.getInstance().commit();
				
				List<Object> first=new ArrayList<>();
				first.add("post");
				first.add(postList);
				
				List<Object> second=new ArrayList<>();
				second.add("user");
				if(ub!=null||ub.getProfile()!=null){
					//プロフィールの内容を取得して、記号や改行を置換する
					String text = ub.getProfile();
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
					ub.setProfile(text);
				}
				second.add(ub);
				
				List<Object> third=new ArrayList<>();
				third.add("like");
				third.add(likesList);
				
				List<List> result=new ArrayList<>();
				result.add(first);
				result.add(second);
				result.add(third);
				
				resc.setResult(result);
				
				//転送先情報をセットする
				resc.setTarget("myProfile");
			}else{
				//判定用のwhere句とuserIDをMapに格納する
				Map<String,String> palams=new HashMap<String,String>();
				
				//トランザクションを開始する
				OracleConnectionManager.getInstance().beginTransaction();
				
				//インテグレーションレイヤの処理を呼び出す
				palams.put("where"," WHERE managementId=?");
				palams.put("value",selectedUserId);
				
				AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
				AbstractDao dao=factory.getAbstractDao();
				ArrayList postList = (ArrayList)dao.readAll(palams);
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				factory=AbstractDaoFactory.getFactory("likes");
				dao=factory.getAbstractDao();
				ArrayList likesList = (ArrayList)dao.readAll(palams);
				
				List<UsersPostLikesBean> list=new ArrayList<>();
				for(int i=0;i<postList.size();i++){
					PostBean pb=(PostBean)postList.get(i);
					if(selectedUserId.equals(pb.getManagementId())){
						UsersPostLikesBean uplb=new UsersPostLikesBean();
						uplb.setPostBean(pb);
						list.add(uplb);
					}
				}
				for(int i=0;i<likesList.size();i++){
					LikesBean lb=(LikesBean)likesList.get(i);
					if(selectedUserId.equals(lb.getManagementId())){
						UsersPostLikesBean uplb=new UsersPostLikesBean();
						uplb.setLikesBean(lb);
						list.add(uplb);
					}
				}
				UsersPostLikesBean uplb=new UsersPostLikesBean();
				uplb.setUsersBean(ub);
				list.add(uplb);
				
				List<Object> first=new ArrayList<>();
				first.add("post");
				first.add(postList);
				
				List<Object> second=new ArrayList<>();
				second.add("user");
				second.add(ub);
				
				List<Object> third=new ArrayList<>();
				third.add("like");
				third.add(likesList);
				
				List<List> result=new ArrayList<>();
				result.add(first);
				result.add(second);
				result.add(third);
				
				resc.setResult(result);
				
				palams.clear();
				palams.put("where","WHERE state=1 AND ((activeManagementId="+sessionUserId+" AND passiveManagementId="+selectedUserId+") OR (activeManagementId="+selectedUserId+" AND passiveManagementId="+sessionUserId+"))");
				//ブロックされているかどうかを確認
				factory=AbstractDaoFactory.getFactory("action");
				dao=factory.getAbstractDao();
				ActionBean ab=(ActionBean)dao.read(palams);
				
				//トランザクションを終了する
				OracleConnectionManager.getInstance().commit();
				
				if(ab==null||ab.getActionId()==null){
					//ブロックしている・もしくはされている場合
					resc.setTarget("profile");
				}else{
					//転送先情報をセットする
					resc.setTarget("blockProfile");
				}
			}
			
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
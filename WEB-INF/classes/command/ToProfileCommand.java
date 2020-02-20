/*----------ログインするためのコマンド---------*/
package command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import context.ResponseContext;
import context.RequestContext;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.LikesBean;
import bean.UsersPostLikesBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class ToProfileCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
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
			palams.put("where","WHERE managementId=?");
			palams.put("value",sessionUserId);
			
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
			AbstractDao dao=factory.getAbstractDao();
			ArrayList postList = (ArrayList)dao.readAll(palams);
			
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
			palams.put("where","WHERE managementId=?");
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
				LikesBean lb=(LikesBean)postList.get(i);
				if(selectedUserId.equals(lb.getManagementId())){
					UsersPostLikesBean uplb=new UsersPostLikesBean();
					uplb.setLikesBean(lb);
					list.add(uplb);
				}
			}
			UsersPostLikesBean uplb=new UsersPostLikesBean();
			uplb.setUsersBean(ub);
			list.add(uplb);
			
			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
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
			
			//転送先情報をセットする
			resc.setTarget("profile");
		}
		
		return resc;
	}
}
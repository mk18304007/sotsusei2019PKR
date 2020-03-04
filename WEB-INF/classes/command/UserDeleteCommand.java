/*----------Home画面へ移動するためのコマンド----------*/
package command;

import context.RequestContext;
import context.ResponseContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.ActionBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class UserDeleteCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();
			
			//セッションからユーザー情報を取得
			SessionManager session = new SessionManager(reqc);
			String managementId = ((UsersBean)session.getAttribute("user")).getManagementId();
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//自分をフォローしている全てのユーザーを取得
			Map<String,Object> palams=new HashMap<>();
			palams.put("where"," WHERE passiveManagementId="+managementId);
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
			AbstractDao dao=factory.getAbstractDao();
			List followList=dao.readAll(palams);
			
			//ユーザーを一件ずつ取得
			for(int i=0;i<followList.size();i++){
				ActionBean ab=(ActionBean)followList.get(i);
				
				palams.clear();
				palams.put("where"," WHERE managementId="+ab.getActiveManagementId());
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				//取得したユーザーの更新前データを取得
				palams.clear();
				palams.put("where"," WHERE managementId="+ub.getManagementId());
				UsersBean oldData=(UsersBean)dao.read(palams);
				
				//フォロー数を減らす
				palams.put("Bean",oldData);
				palams.put("follows",String.valueOf((Integer.parseInt(oldData.getFollows()))-1));
				dao.update(palams);
				
			}
			
			//自分がフォローしている全てのユーザーを取得
			palams.clear();
			palams.put("where"," WHERE activeManagementId="+managementId);
			factory=AbstractDaoFactory.getFactory("action");
			dao=factory.getAbstractDao();
			List followerList=dao.readAll(palams);
			
			//ユーザーを一件ずつ取得
			for(int i=0;i<followerList.size();i++){
				ActionBean ab=(ActionBean)followerList.get(i);
				
				palams.clear();
				palams.put("where"," WHERE managementId="+ab.getPassiveManagementId());
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				//取得したユーザーの更新前データを取得
				palams.clear();
				palams.put("where"," WHERE managementId="+ub.getManagementId());
				UsersBean oldData=(UsersBean)dao.read(palams);
				
				//フォロワー数を減らす
				palams.put("Bean",oldData);
				palams.put("followers",String.valueOf((Integer.parseInt(oldData.getFollowers()))-1));
				dao.update(palams);
				
			}
			//action表から削除ユーザーが含まれる列を削除
			palams.clear();
			palams.put("where"," WHERE activeManagementId="+managementId+" OR passiveManagementId="+managementId);
			factory=AbstractDaoFactory.getFactory("action");
			dao=factory.getAbstractDao();
			dao.delete(palams);
			
			//reply表から自分が投稿したコメントを削除
			palams.clear();
			palams.put("where"," WHERE managementId="+managementId);
			factory=AbstractDaoFactory.getFactory("reply");
			dao=factory.getAbstractDao();
			dao.delete(palams);
			
			//自分が投稿したすべての投稿を取得する
			factory=AbstractDaoFactory.getFactory("post");
			dao=factory.getAbstractDao();
			List postList=dao.readAll(palams);
			
			//一件ずつ投稿を取得
			for(int i=0;i<postList.size();i++){
				PostBean pb=(PostBean)postList.get(i);
				//投稿についているコメントを削除
				palams.clear();
				palams.put("where"," WHERE postId="+pb.getPostId());
				factory=AbstractDaoFactory.getFactory("reply");
				dao=factory.getAbstractDao();
				dao.delete(palams);
				
				//投稿を削除
				factory=AbstractDaoFactory.getFactory("post");
				dao=factory.getAbstractDao();
				dao.delete(palams);
			}
			//すべての削除が終わったのでユーザーを削除する
			palams.clear();
			palams.put("where"," WHERE managementId="+managementId);
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			dao.delete(palams);
			
			//ユーザーを削除したらセッションを切り、ログアウトする
			session.invalidate();
			resc.setTarget("index");
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
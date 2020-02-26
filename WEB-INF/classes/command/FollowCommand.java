/*----------フォロー/フォロー解除用のコマンド----------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;
import bean.ActionBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;

import dao.AbstractDao;

public class FollowCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//選択されたユーザーの情報をjspから取得する
		String selectedUserId=reqc.getParameter("managementId")[0];
		
		//ログインしているユーザーの情報をセッションから取得する
		SessionManager session = new SessionManager(reqc);
		String sessionUserId=((UsersBean)session.getAttribute("user")).getManagementId();
		
		//フォローされているかどうかを判定するのに使用する値をMapに格納する
		Map<String,Object> palams=new HashMap<>();
		palams.put("where","WHERE state=0 AND activeManagementID="+sessionUserId+" AND passiveManagementID="+selectedUserId);
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//Action表からデータを取得
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
		AbstractDao dao=factory.getAbstractDao();
		ActionBean ab=(ActionBean)dao.read(palams);
		//フォローしている場合
		if(ab.getActionId()!=null && ab.getActionId().equals("")!=true){
			dao.delete(palams);
			
			//セッションユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId="+sessionUserId);
			
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			UsersBean ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロー数を減らす
			palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))-1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			//選択されたユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId="+selectedUserId);
			ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロワー数を減らす
			palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))-1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			System.out.println("フォローを解除しました！");
		//フォローしていない場合
		}else{
			//新しくAction表に登録する
			palams.remove("where");
			dao.insert(palams);
			
			//セッションユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId="+sessionUserId);
			
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			UsersBean ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロー数を増やす
			palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))+1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			//選択されたユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId="+selectedUserId);
			ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロワー数を増やす
			palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))+1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			System.out.println("フォローしました！");
		}
		return resc;
	}
}
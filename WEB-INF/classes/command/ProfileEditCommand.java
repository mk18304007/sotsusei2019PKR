/*----------プロフィール情報を編集するためのコマンド----------*/
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
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//一意な値を更新する場合でも変更できるように
		//セッションから主キーを取得する
		SessionManager session=new SessionManager(reqc);
		String managementId=((UserBean)session.getAttribute("user")).getManagementId();
		
		//RequestContextから入力パラメータを受け取る
		String userName=reqc.getParameter("userName")[0];
		String userId=reqc.getParameter("userId")[0];
		String profile=reqc.getParameter("profile")[0];
		System.out.println(userName);
		//String profilePicture=reqc.getParameter("profilePicture")[0];
		
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confirm=reqc.getParameter("password")[1];
		String release=reqc.getParameter("release")[0];
		
		//パスワードと確認用パスワードが一致しないとき
		/*if(password.equals(confilm)==false){
			//本来は例外を送出する
			System.out.println("パスワードが一致しません");
		}*/
		//更新する値をマップに格納
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("userName",userName);
		palams.put("userId",userId);
		palams.put("profile",profile);
		palams.put("mailAddress",mailAddress);
		palams.put("password",password);
		palams.put("release",release);
		
		//更新判定用のwhere句をMapに格納
		palams.put("where","where managementId="+managementId);
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		//更新処理
		dao.update(palams);
		
		//更新後のデータを取得する
		//where句は同じものを使える
		UserBean ub=(UserBean)dao.read(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//セッションのもつ情報を最新のものにする
		session=new SessionManager(reqc);
		session.setAttribute("user",ub);
		
		//更新後はプロフィールページへ飛ばす
		Map<String,Bean> result=new HashMap<String,Bean>();
		result.put("user",ub);
		resc.setResult(result);
		resc.setTarget("profile");
		
		return resc;
	}
}
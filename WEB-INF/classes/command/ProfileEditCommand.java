/*----------プロフィール情報を編集するためのコマンド----------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;
import util.PostManager;

import dao.AbstractDao;

public class ProfileEditCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//一意な値を更新する場合でも変更できるように
		//セッションから主キーを取得する
		SessionManager session=new SessionManager(reqc);
		String managementId=((UsersBean)session.getAttribute("user")).getManagementId();
		
		//PostManagerを利用し、画像を保存、保存先のパスを取得する
		PostManager pm= new PostManager(reqc);
		String profilePicture = pm.getContentsPath();
		//RequestContextから入力パラメータを受け取る
		String userName=reqc.getParameter("userName")[0];
		String userId=reqc.getParameter("userId")[0];
		String profile=reqc.getParameter("profile")[0];
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confirm=reqc.getParameter("password")[1];
		String state=reqc.getParameter("state")[0];
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		
		//更新前の値を取得し、Mapに格納する
		//更新がない列はdaoからこのBeanの値を参照する
		//where句以降は同じものを使える
		Map<String,Object> palams=new HashMap<String,Object>();
		palams.put("where","WHERE managementID=?");
		palams.put("value",managementId);
		UsersBean ub=(UsersBean)dao.read(palams);
		palams.put("Bean",ub);
		
		//更新する値をマップに格納
		palams.put("userName",userName);
		palams.put("userId",userId);
		palams.put("profile",profile);
		palams.put("mailAddress",mailAddress);
		palams.put("password",password);
		palams.put("state",state);
		if(profilePicture.equals("") || profilePicture==null || profilePicture.equals("/images/")){
			palams.put("profilePicture",ub.getProfilePicture());
		}else{
			palams.put("profilePicture",profilePicture);
		}
		
		//更新処理
		dao.update(palams);
		
		//更新後のデータを取得する
		//where句は同じものを使える
		ub=(UsersBean)dao.read(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//セッションのもつ情報を最新のものにする
		session=new SessionManager(reqc);
		session.setAttribute("user",ub);
		
		//更新後はプロフィールページへ飛ばす
		ToProfileCommand tpc=new ToProfileCommand();
		tpc.init(reqc);
		resc=tpc.execute(resc);
		
		return resc;
	}
}
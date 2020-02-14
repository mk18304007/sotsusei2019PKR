/*----------ユーザー登録するためのコマンド---------*/
package command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class SignUpCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		
		//RequestContextから入力
		String userName=reqc.getParameter("userName")[0];
		String userId=reqc.getParameter("userId")[0];
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confilm=reqc.getParameter("password")[1];
		
		//パスワードと確認用パスワードが一致しないとき
		/*if(password.equals(confilm)==false){
			//本来は例外を送出する
			System.out.println("パスワードが一致しません");
		}*/
		//登録する値をマップに格納
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("userName",userName);
		palams.put("userId",userId);
		palams.put("mailAddress",mailAddress);
		palams.put("password",password);
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		dao.insert(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//登録が完了したら自動でログインしたいので、LoginCommandを呼ぶ
		LoginCommand lc=new LoginCommand();
		lc.init(reqc);
		resc=lc.execute(resc);
		
		return resc;
	}
}
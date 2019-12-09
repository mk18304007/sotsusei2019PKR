/*----------ユーザー登録するためのコマンド---------*/
package command;

import java.util.Map;
import java.util.HashMap;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class SignUpCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		
		//RequestContextから入力
		String name=reqc.getParameter("name")[0];
		String userId=reqc.getParameter("userId")[0];
		String mailAddress=reqc.getParameter("mailAddress")[0];
		String password=reqc.getParameter("password")[0];
		String confilm=reqc.getParameter("password")[1];
		
		//パスワードと確認用パスワードが一致するとき
		/*if(password.equals(confilm)){
			//本来は例外を送出する
			System.out.println("パスワードが一致しません");
		}*/
		//登録する値をマップに格納
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("name",name);
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
		//OracleConnectionManager.getInstance().commit();
		
		//コネクションを切断する
		//OracleConnectionManager.getInstance().closeConnection();
		
		//登録が完了したら自動でログインしたいので、LoginCommandを呼ぶ
		LoginCommand lc=new LoginCommand();
		lc.init(reqc);
		resc=lc.execute(resc);
		
		Map result=new HashMap();
		result.put("user",resc.getResult());
		resc.setResult(result);
		return resc;
	}
}
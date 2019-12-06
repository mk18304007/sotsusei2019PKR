/*----------ログインするためのコマンド---------*/
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

public class LoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//RequestContextから入力パラメータを取得する
		String[] userIds=reqc.getParameter("userId");
		String userId=userIds[0];
		
		String[] passwords=reqc.getParameter("password");
		String password=passwords[0];
		
		//新しいUserProductクラスをインスタンス化する
		Map<String,String> user = new HashMap<String,String>();
		user.put("userId",userId);
		user.put("password",password);
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		UserBean ub=(UserBean)dao.read(user);
		
		//入力パラメータとDBから取得した値が正しいか判定
		if(userId.equals(ub.getUserId()) && password.equals(ub.getPassword())){
			//一致する場合Homeへ転送
			Map<String,Bean> result=new HashMap<String,Bean>();
			result.put("user",ub);
			resc.setResult(result);
			resc.setTarget("home");
			
		}else{
			/*一致しない場合、ログインページへ(仮)
								本来は例外を送出する*/
			resc.setTarget("login");
			System.out.println("IDかパスワードが違います");
		}
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//コネクションを切断する
		OracleConnectionManager.getInstance().closeConnection();
		
		return resc;
	}
}
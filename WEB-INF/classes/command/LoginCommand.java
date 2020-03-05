/*----------ログインするためのコマンド---------*/
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
import util.SessionManager;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class LoginCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			//RequestContextのインスタンスを取得する
			RequestContext reqc=getRequestContext();
			
			//RequestContextから入力パラメータを取得する
			String userId=reqc.getParameter("userId")[0];
			String password=reqc.getParameter("password")[0];
			
			//判定用のwhere句とuserIDをMapに格納する
			Map<String,String> palams=new HashMap<String,String>();
			palams.put("value",userId);
			palams.put("where","where userId=?");
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//インテグレーションレイヤの処理を呼び出す
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
			AbstractDao dao=factory.getAbstractDao();
			UsersBean ub=(UsersBean)dao.read(palams);
			
			//入力パラメータとDBから取得したパスワードが正しいか判定
			if(password.equals(ub.getPassword())){
				//一致する場合Homeへ転送
				//ホーム画面に移動する際、投稿を表示したいので、ホーム画面移動用のコマンドに遷移を任せる
				ToHomeCommand thc=new ToHomeCommand();
				thc.init(reqc);
				resc=thc.execute(resc);
				
			}else{
				//一致しない場合、ログインページへ(仮)本来は例外を送出する
				List<String> first=new ArrayList<String>();
				String state="1";
				first.add("notfound");
				first.add(state);
				List<List> result=new ArrayList<>();
				result.add(first);
				resc.setResult(result);
				resc.setTarget("login");
				System.out.println("LoginCommand.execute.else.IDかパスワードが違います");
			}
			
			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
			//セッションにユーザー情報を持たせる
			SessionManager session=new SessionManager(reqc);
			session.setAttribute("user",ub);
			
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
/*----------Home画面へ移動するためのコマンド----------*/
package command;

import context.RequestContext;
import context.ResponseContext;

import java.util.Map;
import java.util.HashMap;

import bean.Bean;
import bean.ReplyBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class PostDeleteCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();
			//postIdを取得
			String postId=reqc.getParameter("postId")[0];
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//リプライ表を参照して投稿にコメントがついているかを調べる
			//インテグレーションレイヤの処理を呼び出す
			Map<String,String> palams=new HashMap<>();
			palams.put("where","WHERE postId="+postId);
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("reply");
			AbstractDao dao = factory.getAbstractDao();
			ReplyBean rb=(ReplyBean)dao.read(palams);
			
			//投稿にコメントがある場合コメントを先に削除する
			if(rb!=null){
				dao.delete(palams);
			}
			
			//投稿にコメントがついてない状態になったら
			//投稿を削除する
			factory = AbstractDaoFactory.getFactory("post");
			dao = factory.getAbstractDao();
			dao.delete(palams);
			
			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
			//表示していた投稿がなくなるので、削除後はホームへ戻る
			//ホームへ移動する処理はToHomeCommandへ一任する
			ToHomeCommand thc=new ToHomeCommand();
			thc.init(reqc);
			resc=thc.execute(resc);
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
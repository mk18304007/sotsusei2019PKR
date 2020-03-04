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

public class CommentDeleteCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();
			//postIdを取得
			String commentId=reqc.getParameter("commentId")[0];
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//リプライ表から該当する列を削除する
			Map<String,String> palams=new HashMap<>();
			palams.put("where"," WHERE replyId="+commentId);
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("reply");
			AbstractDao dao = factory.getAbstractDao();
			dao.delete(palams);

			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
			//削除後はホームへ戻る
			//ホームへ移動する処理はToHomeCommandへ一任する
			ToCommentCommand tcc=new ToCommentCommand();
			tcc.init(reqc);
			resc=tcc.execute(resc);
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
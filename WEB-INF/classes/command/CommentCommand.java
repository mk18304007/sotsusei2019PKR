package command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.text.SimpleDateFormat;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.ReplyBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.PostManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class CommentCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc=getRequestContext();
			//投稿のIDを取得
			String postId=reqc.getParameter("postId")[0];
			
			//ログインユーザーの情報をセッションから取得
			SessionManager session=new SessionManager(reqc);
			String managementId=((UsersBean)session.getAttribute("user")).getManagementId();
			
			//リプライの内容を取得
			String replyText = reqc.getParameter("comment")[0];
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//リプライ表に書き込み
			Map<String,String> palams=new HashMap<>();
			palams.put("managementId",managementId);
			palams.put("postId",postId);
			palams.put("reply",replyText);
			palams.put("commentID","");
			palams.put("state","0");
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("reply");
			AbstractDao dao=factory.getAbstractDao();
			dao.insert(palams);
			
			//元の投稿ページへ戻る
			//DBからの取得等は、ToReplyCommandへ一任する
			ToCommentCommand tcc=new ToCommentCommand();
			tcc.init(reqc);
			resc=tcc.execute(resc);
			
			resc.setTarget("reply");
			
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
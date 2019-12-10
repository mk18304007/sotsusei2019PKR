package command;

import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;

import java.text.SimpleDateFormat;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UserBean;
import bean.PostBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class PostCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		//RequestContextのインスタンスを取得する
		RequestContext reqc=getRequestContext();
		
		//SessionManagerのインスタンスを生成し、セッションの持つユーザー情報を取得する
		SessionManager session=new SessionManager(reqc);
		String managementId=((UserBean)session.getAttribute("user")).getManagementId();
		
		//RequestContextからパラメータを取得する
		String contents=reqc.getParameter("contents")[0];
		String text=reqc.getParameter("text")[0];
		
		//DBに格納する内容をMapに格納
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("managementID",managementId);
		palams.put("contents",contents);
		palams.put("text",text);
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
		AbstractDao dao=factory.getAbstractDao();
		dao.insert(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		resc.setTarget("home");
		return resc;
	}
}
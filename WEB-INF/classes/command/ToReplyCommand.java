/*----------返信画面に移動するためのコマンド----------*/
package command;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class ToReplyCommand extends AbstractCommand
{
	public ResponseContext execute(ResponseContext resc)
	{
		//RequestContextのインスタンスを取得する
		RequestContext reqc = getRequestContext();
		System.out.println("36行目: ToReplyCommandのreqcは" + reqc);
		
		//DBに格納する内容をMapに格納
		Map<String,String> palams = new HashMap<String,String>();
		
//		palams.put("1st", "nothing");
		
		System.out.println("42行目: ToReplyCommandのpalamsは" + palams);
		String post_id= reqc.getParameter("post_id")[0];
		System.out.println("post_idは " + post_id);
		palams.put("value",post_id);
		
//		if(palams.get("1st") != "nothing")
//		{
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();

			//インテグレーションレイヤの処理を呼び出す
			AbstractDaoFactory factory = AbstractDaoFactory.getFactory("post");
			AbstractDaoFactory Rfactory = AbstractDaoFactory.getFactory("reply");
			AbstractDao dao = factory.getAbstractDao();
			AbstractDao Rdao = Rfactory.getAbstractDao();
		
			//ArrayList list = Arrays.(dao.read(palams));
			//List list = dao.readAll(palams);
			Bean replybean = dao.read(palams);
			Map rmap = new HashMap();
			rmap.put("post_id", post_id);
			List replyList = Rdao.readAll(rmap);
		
			System.out.println("ToReplyCommand側のreplybeanは " + replybean);
			/*
			 *WebRequestContextのresultに入る
			 *WebApplicationControllerのhandleResponse()の
			 *req.setAttribute("data",resc.getResult())で登録したKeyを
			 *jspの c:forEachタグの items="${data}" に合わせる
			 */
			//resc.setResult(replybean);

			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
//		}
		resc.add(replybean);
		resc.add(replyList);
		resc.setTarget("reply");
		
		return resc;
	}
}
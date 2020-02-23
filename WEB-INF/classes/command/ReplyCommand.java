/**
 * Command pattern
 * 
 * @see <a href="https://www.oracle.com/webfolder/technetwork/jp/javamagazine/Java_MJ18_CommandDesignPattern.pdf">RFC Oracle, Inc</a>
 * @version  4.0
 * @author   Hideki Iizuka
 * @since    JDK5.0,
 */

package command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.SimpleDateFormat;

import context.RequestContext;
import context.ResponseContext;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.ReplyBean;
import bean.ActionBean;

import util.OracleConnectionManager;
import util.SessionManager;
import util.PostManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class ReplyCommand extends AbstractCommand
{
	public ResponseContext execute(ResponseContext resc)
	{
		//RequestContextのインスタンスを取得する
		RequestContext reqc = getRequestContext();
		
		//SessionManagerのインスタンスを生成し、セッションの持つユーザー情報を取得する
		SessionManager session = new SessionManager(reqc);
		System.out.println("ReplyCommandのsessionは " + session);
		String managementId = ((UsersBean)session.getAttribute("user")).getManagementId();
		
		
		
		//付け足し部分
/*		
		ReplyBean rb = new ReplyBean();
		ArrayList reply = rb.getReply();
		Iterator it = reply.iterator();
		
		while(it.hasNext())
		{
			String res = (String)it.next();
			System.out.println("ResCommandのresは " + res);
		}
*/
		//ここまで付け足し
		
		//RequestContextからパラメータを取得する
		String reply = reqc.getParameter("reply")[0];
		System.out.println("68行目: ReplyCommandのreplyは " + reply);
		
		String post_id = reqc.getParameter("post_id")[0];
		System.out.println("70行目: post_idは " + post_id);
		
		//DBに格納する内容をMapに格納
		Map<String,String> palams = new HashMap<String,String>();
		//palams.put("where","where managementId=?");
		palams.put("managementId", managementId);
		palams.put("postId", post_id);
		palams.put("commentID", "100");
		palams.put("state", "0");
		palams.put("reply", reply);

		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();

		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory("reply");
		AbstractDao dao = factory.getAbstractDao();
		
		dao.insert(palams);
		
		palams.remove("where");
		
		ArrayList list = (ArrayList)dao.readAll(palams);
		
		System.out.println("ReplyCommand側のlistは " + list);
		
		resc.setResult(list);

		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		System.out.println("post_idは " + post_id);
		
		resc.setCommandTarget("reply?post_id=" + post_id);
		
		return resc;
	}
}
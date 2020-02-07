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

import command.ToHomeCommand;

import util.OracleConnectionManager;
import util.SessionManager;
import util.PostManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class PostCommand extends AbstractCommand
{
	public ResponseContext execute(ResponseContext resc)
	{
		//RequestContextのインスタンスを取得する
		RequestContext reqc = getRequestContext();
		
		//SessionManagerのインスタンスを生成し、セッションの持つユーザー情報を取得する
		SessionManager session = new SessionManager(reqc);
		String managementId = ((UsersBean)session.getAttribute("user")).getManagementId();
		
		PostManager postmanager = new PostManager(reqc);
		String contents = postmanager.getContentsPath();

		//RequestContextからパラメータを取得する
		//String contents=reqc.getParameter("contents")[0];
		String text = reqc.getParameter("text")[0];

		//DBに格納する内容をMapに格納
		Map<String,String> palams = new HashMap<String,String>();
		palams.put("where","where managementId=?");
		palams.put("value", managementId);
		palams.put("contents", contents);
		palams.put("text", text);
		palams.put("managementId",managementId);

		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();

		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory = AbstractDaoFactory.getFactory("post");
		AbstractDao dao = factory.getAbstractDao();
		dao.insert(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//Homeへ移動する際の処理をToHomeCommandに一任する
		ToHomeCommand thc=new ToHomeCommand();
		thc.init(reqc);
		resc=thc.execute(resc);
		
		return resc;
	}
}
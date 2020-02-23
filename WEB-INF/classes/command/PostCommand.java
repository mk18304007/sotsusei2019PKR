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

public class PostCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc = getRequestContext();
		
		//SessionManagerのインスタンスを生成し、セッションの持つユーザー情報を取得する
		SessionManager session = new SessionManager(reqc);
		String managementId = ((UsersBean)session.getAttribute("user")).getManagementId();
		
		//PostManagerを利用し、画像を保存、保存先のパスを取得する
		PostManager postmanager = new PostManager(reqc);
		String[] string = new String[10];
		ArrayList contents = postmanager.getContentsPath();
		Iterator it = contents.iterator();
		
		while(it.hasNext()){
			String path = (String)it.next();
		}
		
		//RequestContextからパラメータを取得する
		String text = reqc.getParameter("text")[0];
		
		//DBに格納する内容をMapに格納
		Map<String, Object> palams = new HashMap<String, Object>();
		palams.put("where","where managementId=?");
		palams.put("managementId", managementId);
		palams.put("contents1", contents.get(0));
		palams.put("contents2", contents.get(1));
		palams.put("contents3", contents.get(2));
		palams.put("contents4", contents.get(3));
		palams.put("contents5", contents.get(4));
		palams.put("contents6", contents.get(5));
		palams.put("contents7", contents.get(6));
		palams.put("contents8", contents.get(7));
		palams.put("contents9", contents.get(8));
		palams.put("contents10", contents.get(9));
		palams.put("text", text);
		
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
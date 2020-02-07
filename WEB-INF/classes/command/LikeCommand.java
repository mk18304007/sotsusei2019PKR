/*----------いいねのコマンド----------*/
package command;

import bean.Bean;
import bean.LikesBean;

import context.RequestContext;
import context.ResponseContext;

import dao.AbstractDao;

import java.util.Map;
import java.util.HashMap;

import util.LikeCheck;
import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

public class LikeCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		
		String postId=reqc.getParameter("postId")[0];
		String managementId=reqc.getParameter("managementId")[0];
		String replyId="";
		String state="0";
		System.out.println("LikeCommand.execute.postId:"+postId+",managementId:"+managementId);
		
		//いいね判定用のMapを作り、取得した値を格納する
		Map<String,String> palams=new HashMap<String,String>();
		palams.put("postId",postId);
		palams.put("managementId",managementId);
		palams.put("where","WHERE managementId=? AND postId=?");
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("likes");
		AbstractDao dao=factory.getAbstractDao();
		LikesBean lb=(LikesBean)dao.read(palams);
		
		if(lb.getLikeId()!=null && lb.getLikeId().equals("")!=true){
			String likeId=((LikesBean)dao.read(palams)).getLikeId();
			System.out.println("LikeCommand.doPost.if.ture.likeId:"+likeId);
			
			dao.delete(palams);
			System.out.println("いいねを取り消しました！");
		}else{
			palams.put("replyId",replyId);
			palams.put("state",state);
			dao.insert(palams);
			System.out.println("いいねしました！");
		}
		return resc;
	}
}
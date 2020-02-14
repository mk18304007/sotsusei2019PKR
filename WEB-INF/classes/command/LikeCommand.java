/*----------いいねのコマンド----------*/
package command;

import bean.Bean;
import bean.LikesBean;
import bean.PostBean;

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
		
		//いいねされているかどうかを判定するのに使用する値をMapに格納する
		Map<String,Object> palams=new HashMap<>();
		palams.put("postId",postId);
		palams.put("managementId",managementId);
		palams.put("where","WHERE managementId=? AND postId=?");
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//likes表からデータを取得
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("likes");
		AbstractDao dao=factory.getAbstractDao();
		LikesBean lb=(LikesBean)dao.read(palams);
		
		//いいねされている場合
		if(lb.getLikeId()!=null && lb.getLikeId().equals("")!=true){
			//likes表から該当する列を削除
			dao.delete(palams);
			
			//Mapを初期化し、Post表のデータを取得
			palams.clear();
			palams.put("where","WHERE postId=?");
			palams.put("value",postId);
			
			factory=AbstractDaoFactory.getFactory("post");
			dao=factory.getAbstractDao();
			PostBean pb=(PostBean)dao.read(palams);
			
			//LikesCountを1減らすupdate文を実行する
			//更新のない列は同じデータで上書きする
			palams.put("Bean",pb);
			palams.put("likesCount",String.valueOf(Integer.parseInt(pb.getLikesCount())-1));
			dao.update(palams);
			System.out.println("いいねを取り消しました！");
		//いいねされていない場合
		}else{
			//新しくLikes表に登録する
			palams.put("replyId",replyId);
			palams.put("state",state);
			dao.insert(palams);
			
			//Mapを初期化し、Post表のデータを取得
			palams.clear();
			palams.put("where","WHERE postId=?");
			palams.put("value",postId);
			
			factory=AbstractDaoFactory.getFactory("post");
			dao=factory.getAbstractDao();
			PostBean pb=(PostBean)dao.read(palams);
			
			//LikesCountを1増やすupdate文を実行する
			//更新のない列は同じデータで上書きする
			palams.put("Bean",pb);
			palams.put("likesCount",String.valueOf(Integer.parseInt(pb.getLikesCount())+1));
			dao.update(palams);
			System.out.println("いいねしました！");
		}
		
		resc.setTarget("home");
		return resc;
	}
}
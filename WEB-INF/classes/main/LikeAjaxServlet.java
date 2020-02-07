package main;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Bean;
import bean.LikesBean;

import dao.AbstractDao;

import util.LikeCheck;
import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

public class LikeAjaxServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		req.setCharacterEncoding("UTF-8");
		
		String postId=req.getParameter("postId");
		String managementId=req.getParameter("managementId");
		String replyId="";
		String state="0";
		System.out.println("LikeAjaxServlet.doPost.postId:"+postId+",managementId:"+managementId);
		
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
			System.out.println("LikeCheckServlet.doPost.if.ture.likeId:"+likeId);
			
			dao.delete(palams);
			System.out.println("いいねを取り消しました！");
		}else{
			palams.put("replyId",replyId);
			palams.put("state",state);
			dao.insert(palams);
			System.out.println("いいねしました！");
		}
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
	}
}
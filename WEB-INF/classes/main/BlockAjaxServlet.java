package main;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.*;

import dao.AbstractDao;

import util.LikeCheck;
import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

public class BlockAjaxServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		req.setCharacterEncoding("UTF-8");
		
		//選択されたユーザーの情報をjspから取得する
		String selectedUserId=req.getParameter("managementId");
		
		//ログインしているユーザーの情報をセッションから取得する
		HttpSession session=req.getSession();
		String sessionUserId=((UsersBean)session.getAttribute("user")).getManagementId();
		
		//ブロックされているかどうかを判定するのに使用する値をMapに格納する
		Map<String,Object> palams=new HashMap<>();
		palams.put("state","1");
		palams.put("activeManagementID",sessionUserId);
		palams.put("passiveManagementID",selectedUserId);
		palams.put("where","WHERE state=? AND activeManagementID=? AND passiveManagementID=?");
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//Action表からデータを取得
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
		AbstractDao dao=factory.getAbstractDao();
		ActionBean ab=(ActionBean)dao.read(palams);
		//ブロックしている場合
		if(ab.getActionId()!=null && ab.getActionId().equals("")!=true){
			dao.delete(palams);
			
			System.out.println("ブロックを解除しました！");
		//ブロックしていない場合
		}else{
			//action表に追加
			dao.insert(palams);
			
			//フォローしていた・されていた状態を格納する変数
			//それぞれdeleteの結果を取得し、0以外ならしていた・されていたと判定
			
			int followCount=0;
			int followerCount=0;
			
			//ブロックするユーザーのフォローを解除する
			palams.put("state","0");
			followCount=dao.delete(palams);
			//ブロックするユーザーからのフォローを解除する
			palams.put("activeManagementID",selectedUserId);
			palams.put("passiveManagementID",sessionUserId);
			followerCount=dao.delete(palams);
			
			//フォローしていたなら
			if(followCount!=0){
				//自分の更新前データを取得する
				palams.clear();
				palams.put("where","WHERE managementId=?");
				palams.put("value",sessionUserId);
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				//自分のフォロー数を減らす
				palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))-1));
				palams.put("Bean",ub);
				dao.update(palams);
				
				//ブロックするユーザーの更新前データを取得する
				palams.clear();
				palams.put("where","WHERE managementId=?");
				palams.put("value",selectedUserId);
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				ub=(UsersBean)dao.read(palams);
				
				//ブロックするユーザーのフォロワー数を減らす
				palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))-1));
				palams.put("Bean",ub);
				dao.update(palams);
			}
			
			//フォローされていたなら
			if(followerCount!=0){
				//自分の更新前データを取得する
				palams.clear();
				palams.put("where","WHERE managementId=?");
				palams.put("value",sessionUserId);
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				UsersBean ub=(UsersBean)dao.read(palams);
				
				//自分のフォロワー数を減らす
				palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))-1));
				palams.put("Bean",ub);
				dao.update(palams);
				
				//ブロックするユーザーの更新前データを取得する
				palams.clear();
				palams.put("where","WHERE managementId=?");
				palams.put("value",selectedUserId);
				
				factory=AbstractDaoFactory.getFactory("users");
				dao=factory.getAbstractDao();
				ub=(UsersBean)dao.read(palams);
				
				//ブロックするユーザーのフォロー数を減らす
				palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))-1));
				palams.put("Bean",ub);
				dao.update(palams);
			}
			System.out.println("ブロックしました！");
		}
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
	}
}
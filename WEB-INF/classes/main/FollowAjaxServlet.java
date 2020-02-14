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

public class FollowAjaxServlet extends HttpServlet{
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
		
		//フォローされているかどうかを判定するのに使用する値をMapに格納する
		Map<String,Object> palams=new HashMap<>();
		palams.put("state","0");
		palams.put("activeManagementID",sessionUserId);
		palams.put("passiveManagementID",selectedUserId);
		palams.put("where","WHERE state=? AND activeManagementID=? AND passiveManagementID=?");
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//Action表からデータを取得
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
		AbstractDao dao=factory.getAbstractDao();
		ActionBean ab=(ActionBean)dao.read(palams);
		//フォローしている場合
		if(ab.getActionId()!=null && ab.getActionId().equals("")!=true){
			dao.delete(palams);
			
			//セッションユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId=?");
			palams.put("value",sessionUserId);
			
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			UsersBean ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロー数を減らす
			palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))-1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			//選択されたユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId=?");
			palams.put("value",selectedUserId);
			ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロワー数を減らす
			palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))-1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			System.out.println("フォローを解除しました！");
		//フォローしていない場合
		}else{
			//新しくAction表に登録する
			palams.remove("where");
			int count=dao.insert(palams);
			System.out.println("aaaaaa"+count);
			//セッションユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId=?");
			palams.put("value",sessionUserId);
			
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			UsersBean ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロー数を増やす
			palams.put("follows",String.valueOf((Integer.parseInt(ub.getFollows()))+1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			//選択されたユーザーの更新前情報を取得する
			palams.clear();
			palams.put("where","WHERE managementId=?");
			palams.put("value",selectedUserId);
			ub=(UsersBean)dao.read(palams);
			
			//セッションユーザーのフォロワー数を増やす
			palams.put("followers",String.valueOf((Integer.parseInt(ub.getFollowers()))+1));
			palams.put("Bean",ub);
			dao.update(palams);
			
			System.out.println("フォローしました！");
		}
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
	}
}
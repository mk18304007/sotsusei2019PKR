package command;

import context.RequestContext;
import context.ResponseContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import bean.Bean;
import bean.ActionBean;
import bean.PostBean;
import bean.UsersBean;
import bean.UsersActionBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;
import util.FollowCheck;

import dao.AbstractDao;

public class SarchCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		RequestContext reqc=getRequestContext();
		
		SessionManager session = new SessionManager(reqc);
		String sessionManagementId = ((UsersBean)session.getAttribute("user")).getManagementId();
		
		String keyword=reqc.getParameter("keyword")[0];
		Map<String,String> palams=new HashMap<>();
		palams.put("where","WHERE userName LIKE '%"+keyword+"%'OR userId LIKE '%"+keyword+"%'");
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("users");
		AbstractDao dao=factory.getAbstractDao();
		ArrayList usersList = (ArrayList)dao.readAll(palams);
		
		palams.clear();
		palams.put("where","WHERE text LIKE '%"+keyword+"%'");
		
		factory=AbstractDaoFactory.getFactory("post");
		dao=factory.getAbstractDao();
		ArrayList postList = (ArrayList)dao.readAll(palams);
		
		palams.clear();
		
		List<Bean> newUserList=new ArrayList<>();
		for(int i=0;i<usersList.size();i++){
			UsersBean ub=(UsersBean)usersList.get(i);
			UsersActionBean uab=new UsersActionBean();
			palams.put("state","0");
			palams.put("activeManagementID",sessionManagementId);
			palams.put("passiveManagementID",ub.getManagementId());
			palams.put("where","WHERE state=? AND activeManagementID=? AND passiveManagementID=?");
			FollowCheck check=new FollowCheck();
			uab.setUsersBean(ub);
			if(check.checkFollow(palams)){
				uab.setState("1");
			}else{
				uab.setState("0");
			}
			newUserList.add(uab);
		}
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		List<Object> first=new ArrayList<>();
		first.add("user");
		first.add(newUserList);
		
		List<Object> second=new ArrayList<>();
		second.add("post");
		second.add(postList);
		
		List<List> result=new ArrayList<>();
		result.add(first);
		result.add(second);
		
		resc.setResult(result);
		resc.setTarget("sarch");
		
		return resc;
	}
}
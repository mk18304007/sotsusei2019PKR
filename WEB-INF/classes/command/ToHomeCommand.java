/*----------Home画面へ移動するためのコマンド----------*/
package command;

import context.RequestContext;
import context.ResponseContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import bean.Bean;
import bean.UsersBean;
import bean.PostBean;
import bean.LikesBean;
import bean.UsersPostLikesBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

import dao.AbstractDao;

public class ToHomeCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc){
		
		//判定用のwhere句とuserIDをMapに格納する
		Map<String,String> palams=new HashMap<String,String>();
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
		AbstractDao dao=factory.getAbstractDao();
		ArrayList postList = (ArrayList)dao.readAll(palams);
		
		factory=AbstractDaoFactory.getFactory("users");
		dao=factory.getAbstractDao();
		ArrayList usersList = (ArrayList)dao.readAll(palams);
		
		factory=AbstractDaoFactory.getFactory("likes");
		dao=factory.getAbstractDao();
		ArrayList likesList = (ArrayList)dao.readAll(palams);
		
		List<Bean> list=new ArrayList<>();
		for(int i=0; i<postList.size();i++){
			UsersPostLikesBean uplb=new UsersPostLikesBean();
			PostBean pb=(PostBean)postList.get(i);
			for(int j=0; j<usersList.size();j++){
				UsersBean ub=(UsersBean)usersList.get(j);
				if(pb.getManagementId().equals(ub.getManagementId())){
					uplb.setUsersBean(ub);
				}
			}
			for(int j=0;j<likesList.size();j++){
				LikesBean lb=(LikesBean)likesList.get(j);
				if(pb.getPostId().equals(lb.getPostId())){
					uplb.setLikesBean(lb);
				}
			}
			uplb.setPostBean((PostBean)postList.get(i));
			list.add(uplb);
		}
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		resc.setResult(list);
		
		//転送先情報をセットする
		resc.setTarget("home");
		return resc;
	}
}
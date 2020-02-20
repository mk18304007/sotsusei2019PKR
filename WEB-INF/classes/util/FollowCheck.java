package util;

import java.util.Map;

import dao.AbstractDao;

import bean.Bean;
import bean.ActionBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

public class FollowCheck{
	public boolean checkFollow(Map map){
		//フォローしているかどうかのフラグ
		boolean flag=false;
		
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
		AbstractDao dao=factory.getAbstractDao();
		ActionBean ab=(ActionBean)dao.read(map);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//フォローしているかの判定
		//Likes表の主キーがNULLでない、かつ空文字でない場合TRUEを返す
		if(ab.getActionId()!=null && ab.getActionId().equals("")==false){
			flag=true;
		}else{
			flag=false;
		}
		
		return flag;
	}
}
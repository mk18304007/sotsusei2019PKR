package util;

import java.util.Map;

import dao.AbstractDao;

import bean.Bean;
import bean.LikesBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;

public class LikeCheck{
	public static boolean checkLikeUser(Map palams){
		//いいねされているかどうかのフラグ
		boolean flag=false;
		//トランザクションを開始する
		OracleConnectionManager.getInstance().beginTransaction();
		
		//インテグレーションレイヤの処理を呼び出す
		AbstractDaoFactory factory=AbstractDaoFactory.getFactory("likes");
		AbstractDao dao=factory.getAbstractDao();
		LikesBean lb=(LikesBean)dao.read(palams);
		
		//トランザクションを終了する
		OracleConnectionManager.getInstance().commit();
		
		//いいねされているかの判定
		//Likes表の主キーがNULLでない、かつ空文字でない場合TUREを返す
		if(lb.getLikeId()!=null && lb.getLikeId().equals("")!=true){
			flag=true;
		}else{
			flag=false;
		}
		
		return flag;
	}
}
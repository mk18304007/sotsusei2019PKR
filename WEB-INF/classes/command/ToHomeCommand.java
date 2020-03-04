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
import util.SessionManager;
import util.FollowCheck;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;

public class ToHomeCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc = getRequestContext();
			String managementId="";
			String userId="";
			try{
				//SessionManagerのインスタンスを生成し、セッションの持つユーザー情報を取得する
				SessionManager session = new SessionManager(reqc);
				managementId = ((UsersBean)session.getAttribute("user")).getManagementId();
			}catch(NullPointerException e){
				userId=reqc.getParameter("userId")[0];
			}catch(Exception e){
				e.printStackTrace();
			}
			//判定用のwhere句とuserIDをMapに格納する
			Map<String,String> palams=new HashMap<String,String>();
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			palams.put("where"," ORDER BY postID DESC");
			
			//インテグレーションレイヤの処理を呼び出す
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("post");
			AbstractDao dao=factory.getAbstractDao();
			ArrayList postList = (ArrayList)dao.readAll(palams);
			palams.clear();
			
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			ArrayList usersList = (ArrayList)dao.readAll(palams);
			
			factory=AbstractDaoFactory.getFactory("likes");
			dao=factory.getAbstractDao();
			ArrayList likesList = (ArrayList)dao.readAll(palams);
			
			
			//managementId(セッションユーザー)が空なら
			if(managementId==null||managementId.equals("")){
				//ユーザーリストを一件ずつ取得し
				for(int i=0; i<usersList.size();i++){
					UsersBean ub=(UsersBean)usersList.get(i);
					//ログイン時に入力したユーザーIDと一致するユーザーの主キーを取得する
					if(userId.equals(ub.getUserId())){
						managementId=ub.getManagementId();
					}
				}
			}
			
			
			List<Bean> list=new ArrayList<>();
			//投稿を一件ずつ取得
			for(int i=0; i<postList.size();i++){
				PostBean pb=(PostBean)postList.get(i);
				//フォローしてるかどうかのフラグ
				boolean followFlag=false;
				
				FollowCheck check=new FollowCheck();
				
				//フォローしてるかどうかを判定する
				palams.put("state","0");
				palams.put("activeManagementID",managementId);
				palams.put("passiveManagementID",pb.getManagementId());
				palams.put("where","WHERE state=? AND activeManagementID=? AND passiveManagementID=?");
				
				followFlag=check.checkFollow(palams);
				palams.clear();
				System.out.println("postManagementId:"+pb.getManagementId()+" followFlag:"+followFlag);
				//フォローしているとき もしくは 投稿したユーザーが自分のとき
				if(followFlag || pb.getManagementId().equals(managementId)){
					UsersPostLikesBean uplb=new UsersPostLikesBean();
					//ユーザーを一件ずつ取得
					for(int j=0; j<usersList.size();j++){
						UsersBean ub=(UsersBean)usersList.get(j);
						//投稿したユーザーと今取得したユーザーが一致するときのみ
						if(pb.getManagementId().equals(ub.getManagementId())){
							//Beanに追加
							uplb.setUsersBean(ub);
						}
					}
					//いいねを一件ずつ取得
					for(int j=0;j<likesList.size();j++){
						LikesBean lb=(LikesBean)likesList.get(j);
						
						//いいね表の投稿IDと、投稿のIDが一致する かつ セッションユーザーと、いいね表のいいねしたユーザーが一致するときのみ
						if(lb.getPostId().equals(pb.getPostId()) && managementId.equals(lb.getManagementId())){
								//Beanに登録
								uplb.setLikesBean(lb);
						}
					}
					//投稿の内容を取得して、記号や改行を置換する
					String text = pb.getText();
					text = text.replaceAll("&amp;","&amp;amp;");
					text = text.replaceAll("&lt;","&amp;lt;");
					text = text.replaceAll("&gt;","&amp;gt;");
					text = text.replaceAll("&quot;","&amp;quot;");
					text = text.replaceAll("&apos;","&amp;apos;");
					text = text.replaceAll("&nbsp;","&amp;nbsp;");
					text = text.replaceAll("\"","&quot;");
					text = text.replaceAll("'","&apos;");
					text = text.replaceAll("<","&lt;");
					text = text.replaceAll(">","&gt;");
					text = text.replaceAll("\n","<br>");
					pb.setText(text);
					
					//最後に投稿をBeanに登録する
					uplb.setPostBean(pb);
					list.add(uplb);
				}
			}
			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
			List<Object> first=new ArrayList<>();
			first.add("posts");
			first.add(list);
			List<List> result=new ArrayList<>();
			result.add(first);
			resc.setResult(result);
			
			//転送先情報をセットする
			resc.setTarget("home");
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
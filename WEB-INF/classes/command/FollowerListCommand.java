/*----------フォローリストを表示するためのコマンド----------*/
package command;

import context.RequestContext;
import context.ResponseContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import bean.Bean;
import bean.UsersBean;
import bean.ActionBean;

import util.OracleConnectionManager;
import util.factory.AbstractDaoFactory;
import util.SessionManager;
import util.FollowCheck;

import dao.AbstractDao;

import exception.business.BusinessLogicException;
import exception.business.ParameterInvalidException;
import exception.integration.IntegrationException;


public class FollowerListCommand extends AbstractCommand{
	public ResponseContext execute(ResponseContext resc)throws BusinessLogicException{
		try{
			RequestContext reqc=getRequestContext();
			String managementId=reqc.getParameter("managementId")[0];
			
			//判定用のMap
			Map<String,String> palams=new HashMap<>();
			palams.put("where","WHERE state=0");
			
			//トランザクションを開始する
			OracleConnectionManager.getInstance().beginTransaction();
			
			//インテグレーションレイヤの処理を呼び出す
			AbstractDaoFactory factory=AbstractDaoFactory.getFactory("action");
			AbstractDao dao=factory.getAbstractDao();
			ArrayList followList = (ArrayList)dao.readAll(palams);
			
			palams.clear();
			
			//インテグレーションレイヤの処理を呼び出す
			factory=AbstractDaoFactory.getFactory("users");
			dao=factory.getAbstractDao();
			ArrayList usersList = (ArrayList)dao.readAll(palams);
			
			List<UsersBean> list=new ArrayList<>();
			for(int i=0;i<followList.size();i++){
				ActionBean ab=(ActionBean)followList.get(i);
				if(ab.getPassiveManagementId().equals(managementId)){
					for(int j=0;j<usersList.size();j++){
						UsersBean ub=(UsersBean)usersList.get(j);
						if(ab.getActiveManagementId().equals(ub.getManagementId())){
							list.add(ub);
						}
					}
				}
			}
			//トランザクションを終了する
			OracleConnectionManager.getInstance().commit();
			
			List<Object> first=new ArrayList<>();
			first.add("follow");
			first.add(list);
			List<List> result=new ArrayList<>();
			result.add(first);
			
			//転送先情報をセットする
			resc.setTarget("followerList");
			resc.setResult(result);
			return resc;
		}catch(NullPointerException e){
			throw new ParameterInvalidException("入力内容が足りません",e);
		}catch(IntegrationException e){
			throw new BusinessLogicException(e.getMessage(),e);
		}
	}
}
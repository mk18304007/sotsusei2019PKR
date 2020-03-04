package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.ActionBean;

import util.OracleConnectionManager;

import exception.integration.IntegrationException;

public class ActionDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map)throws IntegrationException{
		return 0;
	}
	public int insert(Map map)throws IntegrationException{
		ActionBean ab =new ActionBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO Action(actionID,activeManagementID,passiveManagementID,state) VALUES((SELECT COALESCE(MAX(actionID),0)+1 FROM Action),?,?,?)");
			System.out.println("ActionDao.insert.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("activeManagementID"));
			ps.setString(2,(String)map.get("passiveManagementID"));
			ps.setString(3,(String)map.get("state"));
			
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("ActionDao.insert.finally.catch:失敗");
			OracleConnectionManager.getInstance().rollback();
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ActionDao.insert.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return count;
    }
	public Bean read(Map map)throws IntegrationException{
		ActionBean ab =new ActionBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Action ");
			sql.append((String)map.get("where"));
			System.out.println("ActionDao.read.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			
			if(map.containsKey("state")){
				ps.setString(1,(String)map.get("state"));
			}
			if(map.containsKey("activeManagementID")){
				ps.setString(2,(String)map.get("activeManagementID"));
			}
			if(map.containsKey("passiveManagementID")){
				ps.setString(3,(String)map.get("passiveManagementID"));
			}

			rs=ps.executeQuery();
			if(rs.next()){
				ab.setActionId(rs.getString(1));
				ab.setActiveManagementId(rs.getString(2));
				ab.setPassiveManagementId(rs.getString(3));
				ab.setState(rs.getString(4));
				ab.setTimeStamp(rs.getString(5));
			}else{
				System.out.println("ActionDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("ActionDao.read.catch:失敗");
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ActionDao.read.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return ab;
	}
	public List readAll(Map map)throws IntegrationException{
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Action ");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("ActionDao.readAll.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value")&&map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				ActionBean ab =new ActionBean();
				ab.setActionId(rs.getString(1));
				ab.setActiveManagementId(rs.getString(2));
				ab.setPassiveManagementId(rs.getString(3));
				ab.setState(rs.getString(4));
				ab.setTimeStamp(rs.getString(5));
				list.add(ab);
			}
		}catch(Exception e){
			System.out.println("ActionDao.readAll.catch:失敗");
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ActionDao.readAll.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return list;
	}
	public int delete(Map map)throws IntegrationException{
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("DELETE FROM Action ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("ActionDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("state")){
				ps.setString(1,(String)map.get("state"));
			}
			if(map.containsKey("activeManagementID")){
				ps.setString(2,(String)map.get("activeManagementID"));
			}
			if(map.containsKey("passiveManagementID")){
				ps.setString(3,(String)map.get("passiveManagementID"));
			}
			count=ps.executeUpdate();
			System.out.println("Action表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("ActionDao.delete.catch:失敗");
			OracleConnectionManager.getInstance().rollback();
			throw new IntegrationException(e.getMessage(),e);
		}
		return count;
	}
}
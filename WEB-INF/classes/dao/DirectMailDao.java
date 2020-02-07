package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.DirectMailBean;

import util.OracleConnectionManager;

public class DirectMailDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		return 0;
	}
	public int insert(Map map){
		DirectMailBean db=new DirectMailBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO DrictMail(sendManagementID,sentManagementID,talk,contents) VALUES(?,?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("sendManagementId"));
			ps.setString(2,(String)map.get("sentManagementId"));
			if(map.containsKey("text")){
				ps.setString(3,(String)map.get("text"));
			}else{
				ps.setString(3,"");
			}
			if(map.containsKey("contents")){
				ps.setString(4,(String)map.get("contents"));
			}else{
				ps.setString(4,"");
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("DirectMailDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("DirectMailDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		DirectMailBean db=new DirectMailBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Post ");
			// WHERE句が存在したらSELECT文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println("DirectMailDao.read.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			
			rs=ps.executeQuery();
			if(rs.next()){
				db.setSendManagementId(rs.getString(1));
				db.setSentManagementId(rs.getString(2));
				db.setTalk(rs.getString(3));
				db.setContents(rs.getString(4));
			}else{
				System.out.println("DirectMailDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("DirectMailDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("DirectMailDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return db;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Post ");
			// WHERE句が存在したらSELECT文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value")&&map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				DirectMailBean db=new DirectMailBean();
				db.setSendManagementId(rs.getString(1));
				db.setSentManagementId(rs.getString(2));
				db.setTalk(rs.getString(3));
				db.setContents(rs.getString(4));
				list.add(db);
			}
		}catch(SQLException e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				throw new RuntimeException(e.getMessage(),e);
			}
		}
		return list;
	}
	public int delete(Map map){
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("DELETE FROM DirectMail ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("DirectMailDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("value")){
				ps.setString(1,(String)map.get("value"));
			}
			
			count=ps.executeUpdate();
			System.out.println("DirectMail表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("DirectMailDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
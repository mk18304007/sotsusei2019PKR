package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.UsersBean;

import util.OracleConnectionManager;

public class UsersDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		UsersBean ub=new UsersBean();
		int count=0;
		if(map.containsKey("Bean")){
			ub=(UsersBean)map.get("Bean");
		}
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Users SET ");
			sql.append("userID=?,userName=?,mailAddress=?,password=?,profile=?,profilePicture=?,state=?,postCount=?,likesCount=?,follows=?,followers=?,report=?");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			System.out.println("UsersDao.update.sql:"+sql);
			
			if(map.containsKey("userID")){
				ps.setString(1,(String)map.get("userId"));
			}else{
				ps.setString(1,ub.getUserId());
			}
			if(map.containsKey("userName")){
				ps.setString(2,(String)map.get("userName"));
			}else{
				ps.setString(2,ub.getUserName());
			}
			if(map.containsKey("mailAddress")){
				ps.setString(3,(String)map.get("mailAddress"));
			}else{
				ps.setString(3,ub.getMailAddress());
			}
			if(map.containsKey("password")){
				ps.setString(4,(String)map.get("password"));
			}else{
				ps.setString(4,ub.getPassword());
			}
			if(map.containsKey("profile")){
				ps.setString(5,(String)map.get("profile"));
			}else{
				ps.setString(5,ub.getProfile());
			}
			if(map.containsKey("profilePicture")){
				ps.setString(6,(String)map.get("profilePicture"));
			}else{
				ps.setString(6,ub.getProfilePicture());
			}
			if(map.containsKey("state")){
				ps.setString(7,(String)map.get("state"));
			}else{
				ps.setString(7,ub.getState());
			}
			if(map.containsKey("postCount")){
				ps.setString(8,(String)map.get("postCount"));
			}else{
				ps.setString(8,ub.getPostCount());
			}
			if(map.containsKey("likesCount")){
				ps.setString(9,(String)map.get("likesCount"));
			}else{
				ps.setString(9,ub.getLikesCount());
			}
			if(map.containsKey("follows")){
				ps.setString(10,(String)map.get("follows"));
			}else{
				ps.setString(10,ub.getFollows());
			}
			if(map.containsKey("followers")){
				ps.setString(11,(String)map.get("followers"));
			}else{
				ps.setString(11,ub.getFollowers());
			}
			if(map.containsKey("report")){
				ps.setString(12,(String)map.get("report"));
			}else{
				ps.setString(12,ub.getReport());
			}
			if(map.containsKey("value")){
				ps.setString(13,(String)map.get("value"));
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("UsersDao.update.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("UsersDao.update.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}

	public int insert(Map map){
		UsersBean ub=new UsersBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();

			sql.append("INSERT INTO Users(managementID,userID,userName,mailAddress,password,profile,profilePicture,state,postCount,likesCount,follows,followers,report,registeredDate) VALUES((SELECT COALESCE(MAX(managementID),0)+1 FROM Users),?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)");
			ps=cn.prepareStatement(new String(sql));
			System.out.println("UsersDao.insert.sql:"+sql);
			
			ps.setString(1,(String)map.get("userId"));
			ps.setString(2,(String)map.get("userName"));
			ps.setString(3,(String)map.get("mailAddress"));
			ps.setString(4,(String)map.get("password"));
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("UsersDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("UsersDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		UsersBean ub=new UsersBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Users ");
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println("UsersDao.read.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			rs=ps.executeQuery();
			if(rs.next()){
				ub.setManagementId(rs.getString(1));
				ub.setUserId(rs.getString(2));
				ub.setUserName(rs.getString(3));
				ub.setMailAddress(rs.getString(4));
				ub.setPassword(rs.getString(5));
				ub.setProfile(rs.getString(6));
				ub.setProfilePicture(rs.getString(7));
				ub.setState(rs.getString(8));
				ub.setPostCount(rs.getString(9));
				ub.setLikesCount(rs.getString(10));
				ub.setFollows(rs.getString(11));
				ub.setFollowers(rs.getString(12));
				ub.setReport(rs.getString(13));
				ub.setRegistredDate(rs.getString(14));
			}else{
				System.out.println("UsersDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("UsersDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("UsersDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return ub;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Users ");
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println("UsersDao.readAll.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value")&&map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
				if(map.containsKey("value2")){
				ps.setString(2,(String)map.get("value2"));
				}
			}
			rs=ps.executeQuery();
			while(rs.next()){
				UsersBean ub=new UsersBean();
				ub.setManagementId(rs.getString(1));
				ub.setUserId(rs.getString(2));
				ub.setUserName(rs.getString(3));
				ub.setMailAddress(rs.getString(4));
				ub.setPassword(rs.getString(5));
				ub.setProfile(rs.getString(6));
				ub.setProfilePicture(rs.getString(7));
				ub.setState(rs.getString(8));
				ub.setPostCount(rs.getString(9));
				ub.setLikesCount(rs.getString(10));
				ub.setFollows(rs.getString(11));
				ub.setFollowers(rs.getString(12));
				ub.setReport(rs.getString(13));
				ub.setRegistredDate(rs.getString(14));
				list.add(ub);
			}
		}catch(SQLException e){
			System.out.println("UsersDao.readAll.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("UsersDao.readAll.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return list;
	}
	public int delete(Map map){
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("DELETE FROM Users ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("UsersDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("value")){
				ps.setString(1,(String)map.get("value"));
			}
			
			count=ps.executeUpdate();
			System.out.println("Users表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("UsersDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
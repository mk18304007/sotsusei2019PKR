package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.UserBean;

import util.OracleConnectionManager;

public class UsersDao implements AbstractDao{

	PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
	UserBean ub=new UserBean();
	
	public int update(Map map){
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Users SET");
			sql.append("userID=?,name=?,name,mailaddress,pasword,profilepicture,release");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			count=ps.executeUpdate();
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
		return count;
	}

	public int insert(Map map){
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			//COALESCE は 最初のNULLと置き換える
			sql.append("INSERT INTO Users(managementID,userID,name,mailAddress,password) VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),?,?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("userId"));
			ps.setString(2,(String)map.get("name"));
			ps.setString(3,(String)map.get("mailAddress"));
			ps.setString(4,(String)map.get("password"));
			count=ps.executeUpdate();
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
		return count;
	}
	public List readAll(Map map){
		return null;
	}
	public Bean read(Map map){
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Users ");
			sql.append((String)map.get("where"));
			System.out.println(sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			
			rs=ps.executeQuery();
			if(rs.next()){
				ub.setManagementId(rs.getString(1));
				ub.setUserId(rs.getString(2));
				ub.setName(rs.getString(3));
				ub.setMailAddress(rs.getString(4));
				ub.setPassword(rs.getString(5));
				ub.setProfile(rs.getString(6));
				ub.setProfilePicture(rs.getString(7));
				ub.setRelease(rs.getString(8));
				ub.setPostCount(rs.getString(9));
				ub.setFollows(rs.getString(10));
				ub.setFollowers(rs.getString(11));
				ub.setLikesCount(rs.getString(12));
				ub.setRegistredDate(rs.getString(13));
			}else{
				System.out.println("ユーザーが見つかりません");
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
		return ub;
	}
}
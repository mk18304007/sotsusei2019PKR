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
	public int update(Map map){
		return 0;
	}
	public int insert(Map map){
		return 0;
	}
	public List readAll(Map map){
		return null;
	}
	public Bean read(Map map){
		UserBean ub=new UserBean();
		PreparedStatement ps=null;
		try{
			Connection cn=null;
			cn=OracleConnectionManager.getInstance().getConnection();
			
			String sql="SELECT * FROM Users WHERE userId = ? AND password = ?";
			
			ps=cn.prepareStatement(sql);
			ps.setString(1,(String)map.get("userId"));
			ps.setString(2,(String)map.get("password"));
			
			ResultSet rs=ps.executeQuery();
			
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
				System.out.println("ÉÜÅ[ÉUÅ[Ç™å©Ç¬Ç©ÇËÇ‹ÇπÇÒ");
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
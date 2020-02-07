package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.LikesBean;

import util.OracleConnectionManager;

public class LikesDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		return 0;
	}
	public int insert(Map map){
		LikesBean lb =new LikesBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("INSERT INTO Likes(LikeID,ManagementID,postID,replyID,state) VALUES((SELECT COALESCE(MAX(LikeID),0)+1 FROM Likes),?,?,?,?)");
			System.out.println("LikesDao.insert.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("managementId"));
			ps.setString(2,(String)map.get("postId"));
			ps.setString(3,(String)map.get("replyId"));
			ps.setString(4,(String)map.get("state"));
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("LikesDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("LikesDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		LikesBean lb =new LikesBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Likes ");
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("LikesDao.read.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("managementId")){
				ps.setString(1,(String)map.get("managementId"));
			}
			if(map.containsKey("postId")){
				ps.setString(2,(String)map.get("postId"));
			}
			if(map.containsKey("replyId")){
				ps.setString(2,(String)map.get("replyId"));
			}
			rs=ps.executeQuery();
			if(rs.next()){
				lb.setLikeId(rs.getString(1));
				lb.setManagementId(rs.getString(2));
				lb.setPostId(rs.getString(3));
				lb.setReplyId(rs.getString(4));
				lb.setState(rs.getString(5));
			}else{
				System.out.println("LikesDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("LikesDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("LikesDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return lb;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Likes ");
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("LikesDao.readAll.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value")&&map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				LikesBean lb =new LikesBean();
				lb.setLikeId(rs.getString(1));
				lb.setManagementId(rs.getString(2));
				lb.setPostId(rs.getString(3));
				lb.setReplyId(rs.getString(4));
				lb.setState(rs.getString(5));
				list.add(lb);
			}
		}catch(SQLException e){
			System.out.println("LikesDao.readAll.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("LikesDao.readAll.finally.catch:失敗");
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
			sql.append("DELETE FROM Likes ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("LikesDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("managementId")){
				ps.setString(1,(String)map.get("managementId"));
			}
			if(map.containsKey("postId")){
				ps.setString(2,(String)map.get("postId"));
			}
			
			count=ps.executeUpdate();
			System.out.println("Likes表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("LikesDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
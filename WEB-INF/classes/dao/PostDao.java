package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.PostBean;

import util.OracleConnectionManager;

public class PostDao implements AbstractDao{

	PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
	PostBean pb=new PostBean();
	
	public int update(Map map){
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Post SET");
			sql.append("contents,text,report");
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
			sql.append("INSERT INTO Post(postID,managementID,contents,text) VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("managementID"));
			ps.setString(2,(String)map.get("contents"));
			if(map.containsKey("text")){
				ps.setString(3,(String)map.get("text"));
			}else{
				ps.setString(3,"");
			}
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
			sql.append("SELECT * FROM Post ");
			sql.append((String)map.get("where"));
			System.out.println(sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			
			rs=ps.executeQuery();
			if(rs.next()){
				pb.setPostId(rs.getString(1));
				pb.setManagementId(rs.getString(2));
				pb.setContents(rs.getString(3));
				pb.setText(rs.getString(4));
				pb.setReport(rs.getString(5));
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
		return pb;
	}
}
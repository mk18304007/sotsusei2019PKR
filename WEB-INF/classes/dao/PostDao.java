package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.PostBean;

import util.OracleConnectionManager;

public class PostDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		PostBean pb=new PostBean();
		int count=0;
		//更新前のデータをMapから取り出す
		//変更がない(新しいデータがMapに格納されていない列はこのBeanが持つ値を入れる)
		if(map.containsKey("Bean")){
			pb=(PostBean)map.get("Bean");
		}
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Post SET ");
			sql.append("report=?,likesCount=?");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("PostDao.update.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("report")){
				ps.setString(1,(String)map.get("report"));
			}else{
				ps.setString(1,pb.getReport());
			}
			if(map.containsKey("likesCount")){
				ps.setString(2,(String)map.get("likesCount"));
			}else{
				ps.setString(2,pb.getLikesCount());
			}
			
			if(map.containsKey("value")){
				ps.setString(3,(String)map.get("value"));
			}
			count=ps.executeUpdate();
			cn.commit();
		}catch(SQLException e){
			System.out.println("PostDao.update.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.update.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}

	public int insert(Map map){
		PostBean pb=new PostBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Post(postID,managementID,contents,text) VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),?,?,?)");
			System.out.println("PostDao.insert.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("managementId")){
				ps.setString(1,(String)map.get("managementId"));
			}else{
				System.out.println("PostDao.insert:managementIdがないよ");
			}
			if(map.containsKey("contents")){
				ps.setString(2,(String)map.get("contents"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("text")){
				ps.setString(3,(String)map.get("text"));
			}else{
				ps.setString(3,"");
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("PostDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		PostBean pb=new PostBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Post ");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("PostDao.read.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value") && map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			if(rs.next()){
				pb.setPostId(rs.getString(1));
				pb.setManagementId(rs.getString(2));
				pb.setContents(rs.getString(3));
				pb.setText(rs.getString(4));
				pb.setReport(rs.getString(5));
				pb.setLikesCount(rs.getString(6));
			}else{
				System.out.println("PostDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("PostDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return pb;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Post ");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("PostDao.readAll.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value") && map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				PostBean pb=new PostBean();
				pb.setPostId(rs.getString(1));
				pb.setManagementId(rs.getString(2));
				pb.setContents(rs.getString(3));
				pb.setText(rs.getString(4));
				pb.setReport(rs.getString(5));
				pb.setLikesCount(rs.getString(6));
				list.add(pb);
			}
		}catch(SQLException e){
			System.out.println("PostDao.readAll.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.readAll.catch:失敗");
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
			sql.append("DELETE FROM Post ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("PostDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("value")){
				ps.setString(1,(String)map.get("value"));
			}
			
			count=ps.executeUpdate();
			System.out.println("Post表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("PostDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
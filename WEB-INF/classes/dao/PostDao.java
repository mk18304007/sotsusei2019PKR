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

import exception.integration.IntegrationException;

public class PostDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map)throws IntegrationException{
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
			OracleConnectionManager.getInstance().rollback();
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.update.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return count;
	}

	public int insert(Map map)throws IntegrationException{
		PostBean pb=new PostBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Post(postID,managementID,contents1,contents2, contents3, contents4, contents5, contents6, contents7, contents8, contents9, contents10, text) VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("PostDao.insert.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("managementId")){
				ps.setString(1,(String)map.get("managementId"));
			}else{
				System.out.println("PostDao.insert:managementIdがないよ");
			}
			if(map.containsKey("contents1")){
				ps.setString(2,(String)map.get("contents1"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents2")){
				ps.setString(3,(String)map.get("contents2"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents3")){
				ps.setString(4,(String)map.get("contents3"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents4")){
				ps.setString(5,(String)map.get("contents4"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents5")){
				ps.setString(6,(String)map.get("contents5"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents6")){
				ps.setString(7,(String)map.get("contents6"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents7")){
				ps.setString(8,(String)map.get("contents7"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents8")){
				ps.setString(9,(String)map.get("contents8"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents9")){
				ps.setString(10,(String)map.get("contents9"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("contents10")){
				ps.setString(11,(String)map.get("contents10"));
			}else{
				System.out.println("PostDao.insert:contentsがないよ");
			}
			if(map.containsKey("text")){
				ps.setString(12,(String)map.get("text"));
			}else{
				ps.setString(12,"");
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("PostDao.insert.catch:失敗");
			OracleConnectionManager.getInstance().rollback();
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.insert.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return count;
	}
	public Bean read(Map map)throws IntegrationException{
		PostBean pb=new PostBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append( "SELECT  postId,managementId,NVL(CONTENTS1,''),NVL(CONTENTS2,''),NVL(CONTENTS3,''),NVL(CONTENTS4,''),NVL(CONTENTS5,''),NVL(CONTENTS6,''),NVL(CONTENTS7,''),NVL(CONTENTS8,''),NVL(CONTENTS9,''),NVL(CONTENTS10,''),TEXT,REPORT,LIKESCOUNT,timeStamp FROM Post");
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
				pb.setContents1(rs.getString(3));
				pb.setContents2(rs.getString(4));
				pb.setContents3(rs.getString(5));
				pb.setContents4(rs.getString(6));
				pb.setContents5(rs.getString(7));
				pb.setContents6(rs.getString(8));
				pb.setContents7(rs.getString(9));
				pb.setContents8(rs.getString(10));
				pb.setContents9(rs.getString(11));
				pb.setContents10(rs.getString(12));
				pb.setText(rs.getString(13));
				pb.setReport(rs.getString(14));
				pb.setLikesCount(rs.getString(15));
				pb.setTimeStamp(rs.getString(16));
			}else{
				System.out.println("PostDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("PostDao.read.catch:失敗");
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.read.finally.catch:失敗");
				throw new IntegrationException(e.getMessage(),e);
			}
		}
		return pb;
	}
	public List readAll(Map map)throws IntegrationException{
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT  postId,managementId,NVL(CONTENTS1,''),NVL(CONTENTS2,''),NVL(CONTENTS3,''),NVL(CONTENTS4,''),NVL(CONTENTS5,''),NVL(CONTENTS6,''),NVL(CONTENTS7,''),NVL(CONTENTS8,''),NVL(CONTENTS9,''),NVL(CONTENTS10,''),TEXT,REPORT,LIKESCOUNT,timeStamp FROM Post");
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
				pb.setContents1(rs.getString(3));
				pb.setContents2(rs.getString(4));
				pb.setContents3(rs.getString(5));
				pb.setContents4(rs.getString(6));
				pb.setContents5(rs.getString(7));
				pb.setContents6(rs.getString(8));
				pb.setContents7(rs.getString(9));
				pb.setContents8(rs.getString(10));
				pb.setContents9(rs.getString(11));
				pb.setContents10(rs.getString(12));
				pb.setText(rs.getString(13));
				pb.setReport(rs.getString(14));
				pb.setLikesCount(rs.getString(15));
				pb.setTimeStamp(rs.getString(16));
				list.add(pb);
			}
		}catch(SQLException e){
			System.out.println("PostDao.readAll.catch:失敗");
			throw new IntegrationException(e.getMessage(),e);
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("PostDao.readAll.catch:失敗");
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
			OracleConnectionManager.getInstance().rollback();
			throw new IntegrationException(e.getMessage(),e);
		}
		return count;
	}
}
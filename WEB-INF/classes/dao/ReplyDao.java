package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.ReplyBean;
import dao.AbstractDao;
import util.OracleConnectionManager;

public class ReplyDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		ReplyBean rb =new ReplyBean();
		int count = 0;
		if(map.containsKey("Bean")){
			rb=(ReplyBean)map.get("Bean");
		}
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Reply SET ");
			sql.append("likesCount=?");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			System.out.println("ReplyDao.udpate.sql:"+sql);
			
			if(map.containsKey("likesCount")){
				ps.setString(1,(String)map.get("likesCount"));
			}else{
				ps.setString(1,rb.getLikesCount());
			}
			if(map.containsKey("value")){
				ps.setString(2,(String)map.get("value"));
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("ReplyDao.update.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ReplyDao.update.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public int insert(Map map){
		ReplyBean rb =new ReplyBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Reply(managementID,postID,replyID,reply,commenID,state) VALUES(?,?,(SELECT COALESCE(MAX(replyID),0)+1 FROM Reply),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			System.out.println("ReplyDao.insert.sql:"+sql);
			
			ps.setString(1,(String)map.get("managementId"));
			ps.setString(2,(String)map.get("postId"));
			ps.setString(3,(String)map.get("reply"));
			ps.setString(4,(String)map.get("commentID"));
			ps.setString(5,(String)map.get("state"));
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("ReplyDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ReplyDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		ReplyBean rb =new ReplyBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Reply ");
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			System.out.println("ReplyDao.read.sql:"+sql);
			
			ps.setString(1,(String)map.get("value"));
			rs=ps.executeQuery();
			if(rs.next()){
				rb.setManagementId(rs.getString(1));
				rb.setPostId(rs.getString(2));
				rb.setReplyId(rs.getString(3));
				rb.setReply(rs.getString(4));
				rb.setCommentId(rs.getString(5));
				rb.setLikesCount(rs.getString(6));
				rb.setState(rs.getString(7));
			}else{
				System.out.println("ReplyDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("ReplyDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ReplyDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return rb;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Reply ");
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			System.out.println("ReplyDao.readAll.sql:"+sql);
			
			if(map.containsKey("value") && map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				ReplyBean rb =new ReplyBean();
				rb.setManagementId(rs.getString(1));
				rb.setPostId(rs.getString(2));
				rb.setReplyId(rs.getString(3));
				rb.setReply(rs.getString(4));
				rb.setCommentId(rs.getString(5));
				rb.setLikesCount(rs.getString(6));
				rb.setState(rs.getString(7));
				list.add(rb);
			}
		}catch(SQLException e){
			System.out.println("ReplyDao.readAll.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("ReplyDao.readAll.catch:失敗");
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
			sql.append("DELETE FROM Reply ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("ReplyDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("value")){
				ps.setString(1,(String)map.get("value"));
			}
			
			count=ps.executeUpdate();
			System.out.println("Reply表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("ReplyDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
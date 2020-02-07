package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import bean.Bean;
import bean.FollowBean;

import util.OracleConnectionManager;

public class FollowDao implements AbstractDao{
	private PreparedStatement ps=null;
	private Connection cn=null;
	private ResultSet rs=null;
	
	public int update(Map map){
		FollowBean fb =new FollowBean();
		int count = 0;
		//更新前のデータをMapから取り出す
		//変更がない(新しいデータがMapに格納されていない列はこのBeanが持つ値を入れる)
		if(map.containsKey("Bean")){
			fb=(FollowBean)map.get("Bean");
		}
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			// フォローを解除したとき0になる
			sql.append("UPDATE Follow SET state=0");
			System.out.println("FollowDao.update.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("FollowDao.update.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("FollowDao.update.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public int insert(Map map){
		FollowBean fb =new FollowBean();
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO Follow(followID,followerManagementId,followersManagementId) VALUES((SELECT COALESCE(MAX(replyID),0)+1 FROM Reply),?,?,?)");
			System.out.println("FollowDao.insert.sql:"+sql);
			
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("followID"));
			ps.setString(2,(String)map.get("followerManagementId"));
			ps.setString(3,(String)map.get("followersManagementId"));
			count=ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("FollowDao.insert.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("FollowDao.insert.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return count;
	}
	public Bean read(Map map){
		FollowBean fb =new FollowBean();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Follow ");
			sql.append((String)map.get("where"));
			System.out.println(sql);
			
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			rs=ps.executeQuery();
			if(rs.next()){
				fb.setFollowId(rs.getString(1));
				fb.setFollowerManagementId(rs.getString(2));
				fb.setFollowersManagementId(rs.getString(3));   
			}else{
				System.out.println("FollowDao.read.else:失敗");
			}
		}catch(SQLException e){
			System.out.println("FollowDao.read.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("FollowDao.read.finally.catch:失敗");
				e.printStackTrace();
			}
		}
		return fb;
	}
	public List readAll(Map map){
		List<Bean> list=new ArrayList<Bean>();
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Follow ");
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println(sql);
			
			ps=cn.prepareStatement(new String(sql));
			if(map.containsKey("value")&&map.containsKey("where")){
				ps.setString(1,(String)map.get("value"));
			}
			rs=ps.executeQuery();
			while(rs.next()){
				FollowBean fb =new FollowBean();
				fb.setFollowId(rs.getString(1));
				fb.setFollowerManagementId(rs.getString(2));
				fb.setFollowersManagementId(rs.getString(3)); 
				list.add(fb);
			}
		}catch(SQLException e){
			System.out.println("FollowDao.readAll.catch:失敗");
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(SQLException e){
				System.out.println("FollowDao.readAll.finally.catch:失敗");
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
			sql.append("DELETE FROM Follow ");
			
			//WHERE句をMapから取得
			if(map.containsKey("where")){
				sql.append((String)map.get("where"));
			}
			System.out.println("FollowDao.delete.sql:"+sql);
			ps=cn.prepareStatement(new String(sql));
			
			if(map.containsKey("value")){
				ps.setString(1,(String)map.get("value"));
			}
			
			count=ps.executeUpdate();
			System.out.println("Follow表から"+count+"行削除しました");
			
			cn.commit();
		}catch(Exception e){
			System.out.println("FollowDao.delete.catch:失敗");
			e.printStackTrace();
		}
		return count;
	}
}
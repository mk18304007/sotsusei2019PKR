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
			ps=cn.prepareStatement(new String(sql));
			// 通報数の更新
			if(map.containsKey("report")){
                ps.setString(1,(String)map.get("report"));
            }else{
                ps.setString(1,pb.getReport());
			}
			// いいねされた数の更新
			if(map.containsKey("likesCount")){
                ps.setString(2,(String)map.get("likesCount"));
            }else{
                ps.setString(2,pb.getLikesCount());
			}
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			// 処理列数を返す
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
			sql.append("INSERT INTO Post(postID,managementID,contents,text) VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("managementId"));
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
	public Bean read(Map map){
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT * FROM Post ");
			// WHERE句が存在したらSELECT文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			
			rs=ps.executeQuery();
			if(rs.next()){
				pb.setPostId(rs.getString(1));
				pb.setManagementId(rs.getString(2));
				pb.setContents(rs.getString(3));
				pb.setText(rs.getString(4));
				pb.setReport(rs.getString(5));
				pb.setLikesCount(rs.getString(6));
			}else{
				System.out.println("なし");
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
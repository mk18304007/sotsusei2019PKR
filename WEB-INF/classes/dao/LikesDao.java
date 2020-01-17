package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.LikesBean;
import dao.AbstractDao;
import util.OracleConnectionManager;

public class LikesDao implements AbstractDao{

    PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
    LikesBean lb =new LikesBean();
    
    public int update(Map map){
		return 0;
    }
	public int insert(Map map){
        int count = 0;
        try{
            cn=OracleConnectionManager.getInstance().getConnection();

            StringBuffer sql = new StringBuffer();
            
            sql.append("INSERT INTO Likes(LikeID,ManagementID,postID,replyID,state) VALUES((SELECT COALESCE(MAX(LikeID),0)+1 FROM Likes),?,?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("ManagementID"));
			ps.setString(2,(String)map.get("postID"));
            ps.setString(3,(String)map.get("replyID"));
            ps.setString(4,(String)map.get("state"));
            
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
			sql.append("SELECT * FROM Likes ");
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println(sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));

            rs=ps.executeQuery();
			if(rs.next()){
				lb.setLikesId(rs.getString(1));
				lb.setManagementId(rs.getString(2));
				lb.setPostId(rs.getString(3));
                lb.setReplyId(rs.getString(4));
                lb.setState(rs.getString(4));
			}else{
				System.out.println("damedesu");
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
		return lb;
	}
    public List readAll(Map map){
		return null;
	}
    
}
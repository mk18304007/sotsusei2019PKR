package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.ReplyBean;
import dao.AbstractDao;
import util.OracleConnectionManager;

public class ReplyDao implements AbstractDao{

    PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
    ReplyBean rb =new ReplyBean();
    
    public int update(Map map){
        int count = 0;
        try{
            cn=OracleConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
            sql.append("UPDATE Reply SET ");
            sql.append("likesCount=?");
            ps=cn.prepareStatement(new String(sql));

            // リプライの状態
            if(map.containsKey("likesCount")){
                pst.setString(1,(String)map.get("likesCount"));
            }else{
                pst.setString(1,rb.getLikesCount());
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
            
            sql.append("INSERT INTO Reply(managementID,postID,replyID,reply,commenID,state) VALUES(?,?,(SELECT COALESCE(MAX(replyID),0)+1 FROM Reply),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("managementId"));
			ps.setString(2,(String)map.get("postId"));
			ps.setString(3,(String)map.get("reply"));
			ps.setString(4,(String)map.get("commentID"));
			ps.setString(5,(String)map.get("state"));
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
			sql.append("SELECT * FROM Reply ");
			// WHERE句が存在したらUPDATE文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
            }
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));

            rs=ps.executeQuery();
			if(rs.next()){
				rb.setManagementId(rs.getString(1));
				rb.setPostId(rs.getString(2));
				rb.setReplyId(rs.getString(3));
				rb.setReply(rs.getString(4));
				rb.setCommentID(rs.getString(5));
				rb.setLikesCount(rs.getString(6));
				rb.setState(rs.getString(7));
			}else{
				System.out.println("コメントないよ");
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
		return rb;
	}
    public List readAll(Map map){
		return null;
	}
    
}
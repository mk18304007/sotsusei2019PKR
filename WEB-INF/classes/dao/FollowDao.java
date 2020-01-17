package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.FollowBean;
import dao.AbstractDao;
import util.OracleConnectionManager;

public class FollowDao implements AbstractDao{

    PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
    FollowBean fb =new FollowBean();
    
    public int update(Map map){
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
            ps=cn.prepareStatement(new String(sql));
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
            
            sql.append("INSERT INTO Follow(followID,followerManagementId,followersManagementId) VALUES((SELECT COALESCE(MAX(replyID),0)+1 FROM Reply),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("followID"));
			ps.setString(2,(String)map.get("followerManagementId"));
			ps.setString(3,(String)map.get("followersManagementId"));
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
				System.out.println("FollowDao:read:error");
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
		return fb;
	}
    public List readAll(Map map){
		return null;
	}
    
}
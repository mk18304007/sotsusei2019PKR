package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.DirectMailBean;
import util.OracleConnectionManager;

public class DirectMailDao implements AbstractDao{

	PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
	DirectMailBean db=new DirectMailBean();
	
	public int update(Map map){
        return 0;
	}

	public int insert(Map map){
		int count = 0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO DrictMail(sendManagementID,sentManagementID,talk,contents) VALUES(?,?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("sendManagementId"));
			ps.setString(2,(String)map.get("sentManagementId"));
			if(map.containsKey("text")){
				ps.setString(3,(String)map.get("text"));
			}else{
				ps.setString(3,"");
			}
			if(map.containsKey("contents")){
				ps.setString(4,(String)map.get("contents"));
			}else{
				ps.setString(4,"");
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
				db.setSendManagementId(rs.getString(1));
				db.setSentManagementId(rs.getString(2));
				db.setTalk(rs.getString(3));
				db.setContents(rs.getString(4));
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
		return db;
	}
}
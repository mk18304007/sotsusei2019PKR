package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.ActionBean;
import dao.AbstractDao;
import util.OracleConnectionManager;

public class ActionDao implements AbstractDao{

    PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
    ActionBean ab =new ActionBean();
    
    public int update(Map map){
		return 0;
    }
	public int insert(Map map){
        int count = 0;
        try{
            cn=OracleConnectionManager.getInstance().getConnection();

            StringBuffer sql = new StringBuffer();
            
            sql.append("INSERT INTO Action(actionID,activeManagementID,passiveManagementID,state) VALUES((SELECT COALESCE(MAX(actionID),0)+1 FROM Action),?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("actionID"));
			ps.setString(2,(String)map.get("activeManagementID"));
			ps.setString(3,(String)map.get("passiveManagementID"));
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
			sql.append("SELECT * FROM Action ");
			sql.append((String)map.get("where"));
			System.out.println(sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));

            rs=ps.executeQuery();
			if(rs.next()){
				ad.setActionId(rs.getString(1));
				ad.setActiveManagementtId(rs.getString(2));
				ad.setPassiveManagementId(rs.getString(3));
                ad.setState(rs.getString(4));
                ad.setTimeStamp(rs.getString(5));
			}else{
				System.out.println("ないよ～");
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
		return ad;
	}
    
}
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import bean.Bean;
import bean.UsersBean;

import util.OracleConnectionManager;

public class UsersDao implements AbstractDao{

	PreparedStatement ps=null;
	Connection cn=null;
	ResultSet rs=null;
	UsersBean ub=new UsersBean();
	
	public int update(Map map){
		int count=0;
		try{
			cn=OracleConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE Users SET ");
			sql.append("userID=?,userName=?,mailAddress=?,password=?,profile=?,profilePicture=?,state=?,postCount=?,likesCount=?,follows=?,followers=?,report=?");
			ps=cn.prepareStatement(new String(sql));
			// ユーザーIDの変更
			if(map.containsKey("userID")){
                ps.setString(1,(String)map.get("userId"));
            }else{
                ps.setString(1,ub.getUserId());
			}
			// ユーザーネームの変更
			if(map.containsKey("userName")){
                ps.setString(2,(String)map.get("userName"));
            }else{
                ps.setString(2,ub.getUserName());
			}
			// メールアドレスの変更
			if(map.containsKey("mailAddress")){
                ps.setString(3,(String)map.get("mailAddress"));
            }else{
                ps.setString(3,ub.getMailAddress());
			}
			// パスワードの変更
			if(map.containsKey("password")){
                ps.setString(4,(String)map.get("password"));
            }else{
                ps.setString(4,ub.getPassword());
			}
			// プロフィール文の変更
			if(map.containsKey("profile")){
                ps.setString(5,(String)map.get("profile"));
            }else{
                ps.setString(5,ub.getProfile());
			}
			// プロフィールの写真変更
			if(map.containsKey("profilePicture")){
                ps.setString(6,(String)map.get("profilePicture"));
            }else{
                ps.setString(6,ub.getProfilePicture());
			}
			// 公開非公開の変更
			if(map.containsKey("state")){
                ps.setString(7,(String)map.get("state"));
            }else{
                ps.setString(7,ub.getState());
			}
			// 自分の投稿数の更新
			if(map.containsKey("postCount")){
                ps.setString(8,(String)map.get("postCount"));
            }else{
                ps.setString(8,ub.getPostCount());
			}
			// いいねの更新
			if(map.containsKey("likesCount")){
                ps.setString(9,(String)map.get("likesCount"));
            }else{
                ps.setString(9,ub.getLikesCount());
			}
			// フォロー数の更新
			if(map.containsKey("follows")){
                ps.setString(10,(String)map.get("follows"));
            }else{
                ps.setString(10,ub.getFollows());
			}
			// フォローされた数の更新
			if(map.containsKey("followers")){
                ps.setString(11,(String)map.get("followers"));
            }else{
                ps.setString(11,ub.getFollowers());
			}
			// 通報数の更新
			if(map.containsKey("report")){
                ps.setString(12,(String)map.get("report"));
            }else{
                ps.setString(12,ub.getReport());
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

			sql.append("INSERT INTO Users(managementID,userID,userName,mailAddress,password) VALUES((SELECT COALESCE(MAX(managementID),0)+1 FROM Users),?,?,?,?)");
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("userId"));
			ps.setString(2,(String)map.get("userName"));
			ps.setString(3,(String)map.get("mailAddress"));
			ps.setString(4,(String)map.get("password"));
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
			sql.append("SELECT * FROM Users ");
			// WHERE句が存在したらSELECT文を実行する
			if(map.containsKey("where")){
                sql.append((String)map.get("where"));
			}
			System.out.println(sql);
			ps=cn.prepareStatement(new String(sql));
			ps.setString(1,(String)map.get("value"));
			
			rs=ps.executeQuery();
			if(rs.next()){
				ub.setManagementId(rs.getString(1));
				ub.setUserId(rs.getString(2));
				ub.setUserName(rs.getString(3));
				ub.setMailAddress(rs.getString(4));
				ub.setPassword(rs.getString(5));
				ub.setProfile(rs.getString(6));
				ub.setProfilePicture(rs.getString(7));
				ub.setState(rs.getString(8));
				ub.setPostCount(rs.getString(9));
				ub.setLikesCount(rs.getString(10));
				ub.setFollows(rs.getString(11));
				ub.setFollowers(rs.getString(12));
				ub.setReport(rs.getString(13));
				ub.setRegistredDate(rs.getString(14));
			}else{
				System.out.println("ユーザーが見つかりません");
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
		return ub;
	}
}
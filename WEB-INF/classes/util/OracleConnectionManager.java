/*----------DBに関する接続情報をまとめたManagerクラス----------*/
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionManager{
	private static OracleConnectionManager oraconn=null;
	private Connection cn=null;
	
	private OracleConnectionManager(){}
	
	public static OracleConnectionManager getInstance(){
		if(oraconn == null){
			oraconn = new OracleConnectionManager();
		}
		return oraconn;
	}
	
	public Connection getConnection(){
		if(cn==null){
			try{
				//Driverインターフェイスを実装するクラスをロードする
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//Connectionインターフェイスを実装するクラスのインスタンスを返す
				cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return cn;
	}
	
	public void closeConnection(){
		try{
			if(cn!=null){
				cn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void beginTransaction(){
		if(cn==null){
			getConnection();
		}
		try{
			cn.setAutoCommit(false);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void commit(){
		try{
			cn.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void rollback(){
		try{
			cn.rollback();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
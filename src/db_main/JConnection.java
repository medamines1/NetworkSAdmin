package db_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

public class JConnection {
	public static Connection connectdb () {
			Connection conn = null;
			
			
				   try {

			            Class.forName("oracle.jdbc.driver.OracleDriver");

			        } catch (ClassNotFoundException e) {

			            System.out.println("Where is your Oracle JDBC Driver?");
			            e.printStackTrace();
			            return null;

			        }
				try {
				   conn =DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","med");
				} catch (SQLException e) {

		            System.out.println("Connection Failed! Check output console");
		            e.printStackTrace();
		            return null;

		        }
				
			return conn;
	}
	
	
	
	
	
	
	public static OraclePreparedStatement prepexecute(String sql) {
		Connection conn =  db_main.JConnection.connectdb();   
		OraclePreparedStatement pst=null;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pst;
	}
	
	public static OracleResultSet afterprepExecute(OraclePreparedStatement pst) {
		
		OracleResultSet rs;
		try {
			rs =(OracleResultSet) pst.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return rs;
	}
	
	public static OracleResultSet execute(String sql) {
		
		Connection conn =  db_main.JConnection.connectdb();   
		OraclePreparedStatement pst;
		OracleResultSet rs;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			rs =(OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		return rs;		
	}

	
	public static OracleResultSet executeWithNext(String sql) {
		
		Connection conn =  db_main.JConnection.connectdb();   
		OraclePreparedStatement pst;
		OracleResultSet rs;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			rs =(OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		try {
			rs.next();
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		return rs;
		
		
	}
	
	public static int length_table(String s) {
		try {
			  OracleResultSet rs = execute("select count(1) from "+s);
			  rs.next();
			  return rs.getInt("count(1)");
		} catch (Exception e) {
			return -1;
		}
	}
		
	public static int get_last(String s) {
		try {
			  OracleResultSet rs = execute("select max(id) as gid from "+s);
			  rs.next();
			  return rs.getInt("gid");
		} catch (Exception e) {
			return -1;
		}
		
	}
	
	public static JSONObject getGson(String msg) {
	
	 
	try {
		JSONObject data = new JSONObject(msg.trim());
		return data;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
	public static String toGson(Map<String, String> map) {
		JSONObject json = new JSONObject();
		
			try {
				for(Map.Entry<String, String> s : map.entrySet())
					json.put(s.getKey(),s.getValue());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return json.toString();
		
	}
	
	
}

package main;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;

import MyExceptions.UserNotIdentifiedException;
import db_main.JConnection;
import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

public class Compte {
	private static int code;
	private int user_code;
	private double solde;
	private double max_rouge =300; 
	
	public Compte(double solde) {
		this.solde = solde;
		this.setUser_code(Compte.code);
		setCode();
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public double getMax_rouge() {
		return max_rouge;
	}

	public void setMax_rouge(double max_rouge) {
		this.max_rouge = max_rouge;
	}

	public static int getCode() {
		return code;
	}

	public static void setCode() {
		Compte.code++;
	}

	public int getUser_code() {
		return user_code;
	}

	public  void setUser_code(int user_code) {
		this.user_code = user_code;
	}
	
	public static void connected(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, UserNotIdentifiedException {
		
		 HttpSession sess=request.getSession(false);
		 if (sess==null) {	
	            
			 	//request.getRequestDispatcher("NSA/login.html").forward(request, response);
			 	
			 	throw new UserNotIdentifiedException("session wasn't found"); 
	        }
		 
	}
	
	

	
	
	public static StringBuffer crypt_password(byte[] bs ) throws NoSuchAlgorithmException {

		 MessageDigest md = MessageDigest.getInstance("SHA");
		   md.update(bs);
		     byte[] mdbytes = md.digest();
		     StringBuffer hexString = new StringBuffer();
		    	for (int i=0;i<mdbytes.length;i++) {
		    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
		    	}
		 
		
		return hexString;
	}
	
	
	public static void assign_history(int userid, String action,String from, String to ) throws SQLException {
	 		Connection conn =  db_main.JConnection.connectdb();   
			String sql =String.format("insert into tbl_history(id,userid,uaction,from_ip,to_ip) values(%s,'%s','%s','%s','%s')",length_history(),userid,action,from,to);
			OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			pst.executeQuery();
			conn.close();
		
	}
	public static int length_history() throws SQLException {
		Connection conn =  db_main.JConnection.connectdb();   
		String sql = "SELECT COUNT(*) as cou FROM tbl_history";
		OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
		OracleResultSet rs =(OracleResultSet) pst.executeQuery();
		 rs.next();
		return rs.getInt("cou");
	}

	
	public static boolean sign_up(String name,String email, String password,String m_img ) {
		try {
			 OraclePreparedStatement pst = JConnection.prepexecute("insert into tbl_users values(?,?,?,?,?)");
			 pst.setInt(1, JConnection.get_last("tbl_users")+1);
			 pst.setString(2,name);
			 pst.setString(3, email);
			 pst.setString(4, crypt_password(password.getBytes()).toString());
			 pst.setString(5, m_img);
			 if(JConnection.afterprepExecute(pst) != null) {
				 return true;
			 }
			 return false;
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return true;		
	}
	
	

	public static boolean send_msg(int sender,int receiver, String msg ,String subj,String placedin ) {
		try {
			 OraclePreparedStatement pst = JConnection.prepexecute("insert into msg_db values(?,?,?,?,default,?,default,?)");
			 pst.setInt(1,JConnection.get_last("msg_db")+1);
			 pst.setInt(2,sender);
			 pst.setInt(3, receiver);
			 pst.setString(4, msg);
			 pst.setString(5, placedin);
			 pst.setString(6, subj);

			 if(JConnection.afterprepExecute(pst) != null) {
				 return true;
			 }
			 return false;
			 
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static String get_msgs(String userid) {
		try {
			OracleResultSet rs =  JConnection.execute("select * from msg_db where (sender=" + userid + "  and( placedin='Inbox' or placedin='Sent')) or ( receiver=" + userid + " and placedin='Sent' ) or (sender="+userid+" or receiver="+ userid +" )     order by otimestamp DESC ");
			Map<String, String> options = new LinkedHashMap<String, String>();
			  while(rs.next()) {
				  Map<String,String> e = new LinkedHashMap<String, String>();
					e.put("sender",String.valueOf(rs.getInt("sender")));
					e.put("receiver",String.valueOf(rs.getInt("receiver")));
					e.put("msg",rs.getString("msg"));
					e.put("seen", rs.getString("seen"));
					e.put("placedin", rs.getString("placedin"));
					e.put("timestamp", rs.getString("otimestamp"));
					e.put("subj", rs.getString("subj"));
				  options.put(String.valueOf(rs.getInt("id")), new Gson().toJson(e));
		        }
			  options.put("action","plt_msges" );
			 return JConnection.toGson(options);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	public static String get_list_date(String userid) {
		OracleResultSet rs = JConnection.execute(String.format("select * from tbl_calend where creater_id =%s ",userid));
		Map<String, String> options = new LinkedHashMap<String, String>();
		  try {
			while(rs.next()) {
				  Map<String,String> e = new LinkedHashMap<String, String>();
					e.put("ID",rs.getString("ID"));
					e.put("title",rs.getString("title"));
					e.put("start",rs.getString("d_start"));
					e.put("backgroundColor", rs.getString("bg"));
					e.put("end", rs.getString("end"));
				  options.put(rs.getString("id"), new Gson().toJson(e));
				  
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return new JSONObject(options).toString();
	}
	
	
	
	public static boolean insert_date(String userid,String title,String start, String bg,String end) {

		try { 
			JConnection.execute(String.format("insert into tbl_calend values(default,'%s','%s','%s','%s',%s,default) ",title,start,bg,end,userid));
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
				
		return true;
	}

	public static void delete_date(String userid,String id) {
		JConnection.execute(String.format("delete from tbl_calend where id =%s and creater_id=%s",id,userid));
		
	}	

}

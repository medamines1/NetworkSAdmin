package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import MyExceptions.UserNotIdentifiedException;
import main.Compte;
import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

@SuppressWarnings("serial")
public class jsnReplier extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Compte.connected(req, resp);
		} catch (UserNotIdentifiedException e1) {
			e1.printStackTrace();
		}
	 try {
		 	Connection conn =  db_main.JConnection.connectdb();   
			String sql = "Select * from tbl_machine where type='computer' ";
			OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			OracleResultSet rs =  (OracleResultSet) pst.executeQuery();
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			Map<String, String> options = new LinkedHashMap<String, String>();
			  while(rs.next()) {
				  Map<String,String> opt2 = new LinkedHashMap<String, String>();
		          opt2.put("nom",rs.getString("nom"));
		          opt2.put("type",rs.getString("type"));
		          opt2.put("status",rs.getString("status"));
		          opt2.put("host",rs.getString("Host")+":"+String.valueOf(rs.getInt("port")));
				  options.put(String.valueOf(rs.getInt("id")), new Gson().toJson(opt2));
		        }
		
			 String json = new Gson().toJson(options);
			 out.print(json);
	       
	} catch (SQLException e) {
		System.out.println(e);
	}catch(IllegalStateException e) {
		System.out.println(e);
	}
		
	}

	/**
	 * 
	 */
	

}

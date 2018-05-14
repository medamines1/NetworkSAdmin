package main;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import javax.servlet.*;  
import javax.servlet.http.*;

import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

      
public class Validation extends HttpServlet 
{    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5523264011288510651L;
	@Override
	public void init() throws ServletException {
		super.init();

		
	}
	
	private String id;
	private boolean connected;
	public void doPost(HttpServletRequest request, 
     HttpServletResponse response) 
       throws ServletException, IOException 
   {        
       response.setContentType("text/html");      
       PrintWriter pwriter = response.getWriter();                
       String name=request.getParameter("uname");      
       StringBuffer pass = null;
	try {
		pass = Compte.crypt_password(request.getParameter("upass").getBytes());
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		 pwriter.print("error while parsing the password");   
       return ;
	
		
	} 
     
   
	try {
	connected =false;
	Connection conn =  db_main.JConnection.connectdb();   
	String sql = "select * From tbl_users where email='"+name+"' and password='"+pass+"'";
	OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
	OracleResultSet rs =(OracleResultSet) pst.executeQuery();
	
	if (rs.next()) {
		connected = true;
		 id =  rs.getString("id");
		
	}
	
	}catch(Exception e) {
		
		System.err.println(e);
		pwriter.printf("Errer by db.");   	
	}
    if(connected)
       {          
		  response.addCookie(new Cookie("user_id",String.valueOf(id))); 
		  HttpSession session=request.getSession();  
	      session.setAttribute("user_id",String.valueOf(id));
          pwriter.print("success");
	      return;         
       }     
       else
       {
    	  
          pwriter.print("User name or password is incorrect!");      
          return;
          //RequestDispatcher dis=request.getRequestDispatcher("NSA/login.html");           
          //dis.include(request, response);                                
       }      
    
   }

	
	
}  
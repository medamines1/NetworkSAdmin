package api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import MyExceptions.UserNotIdentifiedException;
import error_pack.error_handl;
import main.Compte;
import oracle.jdbc.driver.OracleResultSet;


public class file_handler extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2517185673759905720L;


	   private int maxFileSize = 5000 * 1024;
	   private int maxMemSize = 40 * 1024;
	   private String user_id ;
	   private file_main f;
	@Override
	public void init() {
		f = new file_main();
	      
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Compte.connected(req, resp);
		} catch (UserNotIdentifiedException e1) {
			// TODO Auto-generated catch block
			return;
		}
		
		String action = req.getParameter("action"); 
		if (action != null && action.equals("get")) {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		OracleResultSet rs = f.file_list(); 
		Map<String, String> options = new LinkedHashMap<String, String>();
		  try {
			while(rs.next()) {
				  Map<String,String> opt2 = new LinkedHashMap<String, String>();
			      opt2.put("fname",rs.getString("fname"));
			      opt2.put("path",rs.getString("path"));
			      opt2.put("c_id",String.valueOf(rs.getInt("CREATER_ID")));
				  options.put(String.valueOf(rs.getInt("fid")), new Gson().toJson(opt2));
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		 String json = new Gson().toJson(options);
		 out.print(json);
		}else if(action != null && action.equals("delete") && action != null && req.getParameter("fid")!=null ) {
			String fileid = req.getParameter("fid");
			//remove the file 
		try {
			f.delete(f.getFilePath(Integer.valueOf(fileid)));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		}
		else {
			req.getRequestDispatcher("NSA/test.html").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   resp.setContentType("text/html");
		  PrintWriter out = resp.getWriter( );  		
	  
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);

	      factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      HttpSession session=req.getSession(); 
	      try {   
	    	   user_id =  session.getAttribute("user_id").toString();
	    	   
	      }catch(NullPointerException e) {
	    	  System.out.println("you're not connected");
	    	  resp.sendRedirect("/");
	    	  return ;
	      }
	    
	      try {
				if (f.upload_file(req,resp,user_id,upload)) {
					 out.println("success");
				}
				else
					out.print("failed");
		} catch (Exception e) {
			e.printStackTrace();
			 out.println(error_handl.true_err(e.toString()));
		}
	    
			
	}

	
}

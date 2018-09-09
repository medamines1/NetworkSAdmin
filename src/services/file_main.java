package services;


import java.io.File;
import java.io.IOException;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

public class file_main {
	private int fid;
	private String fname;
	private int creater_id;
	private String otimestamp;
	
	

	public file_main() {
		
	}
	public file_main(int fid, String fname, int creater_id, String otimestamp) {
		this.fid = fid;
		this.fname = fname;
		this.creater_id = creater_id;
		this.otimestamp = otimestamp;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public int getCreater_id() {
		return creater_id;
	}
	public void setCreater_id(int creater_id) {
		this.creater_id = creater_id;
	}
	public String getOtimestamp() {
		return otimestamp;
	}
	public void setOtimestamp(String otimestamp) {
		this.otimestamp = otimestamp;
	}
	
	   private String uploadPath = "F:\\file_store\\" ;
	   private File filepath ;
	   private int pathi ;
	   private File file;
	@SuppressWarnings({ "static-access" })
	public boolean upload_file(HttpServletRequest req,HttpServletResponse resp,String user,ServletFileUpload upload) throws IOException, ServletException {
		
	     File uploadDir = new File(uploadPath + File.separator + user );
         if (!uploadDir.exists()) {
             uploadDir.mkdir();
         }

	      try { 
		         
	       	  	  // Parse the request to get file items.
		    	  List<FileItem> items = upload.parseRequest(req);   	 
	
		         // Process the uploaded file items
		         Iterator<FileItem> i = items.iterator();  		    		        
		         
		         while ( i.hasNext () ) {
		        	 
		        	  FileItem item = (FileItem) i.next();
	                  this.fname = item.getName();
	                  if (!item.isFormField()) {
	                	  
	                	  filepath = new File(uploadDir +  File.separator + fname);
	                	  if (!filepath.exists())
	                		  item.write(filepath);
	                	  else {
	                	 pathi=0;
	                	  while (filepath.exists()) {
	                		  filepath = new File(uploadDir +  File.separator + pathi);                		  
	                		  pathi++;
	                	  }
	                	  filepath.mkdir();
	                	  filepath =new File(filepath + file.separator + fname);
	                	  item.write(filepath);
	  
	                	  
	                	  HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("myfile", filepath, ContentType.create("application/octet-stream"), fname).build();
	                	  HttpPost request = new HttpPost("http://"+items.get(items.size()-1).getString()+ "/" + "new_file");
	                	  request.setEntity(entity);
	                	
	                	  HttpClient client = HttpClientBuilder.create().build();
	                	try {
	                		client.execute(request);
						} catch (Exception e) {
							e.printStackTrace();
						}
	                	  
	                	  
	                	  
	                	  				}
	                	  	              
                  	          	                  
	                  }		   				         
		         }
		       
	      } catch(Exception e) {
	    	  		e.printStackTrace();
		            System.out.println(e);
		            return false;
		         }
 
		return true;
	
	}

/*	public boolean create_file_db() {
	 	Connection conn =  db_main.JConnection.connectdb();   
		String sql = "insert into fileh values(?,?,?,default,?)";
		
		try {
			OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1, fid);
			
			pst.setString(2, fname);
			pst.setInt(3, creater_id);
			pst.setString(4, filepath.toString());
			@SuppressWarnings("unused")
			OracleResultSet rs =  (OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			return false;
		}		
		return true;
		
	}
		
	public static int getCount() throws SQLException {
		Connection conn =  db_main.JConnection.connectdb();   
		String sql = "SELECT COUNT(1) FROM fileh";
		OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
		OracleResultSet rs =(OracleResultSet) pst.executeQuery();
		 rs.next();
		return rs.getInt("COUNT(1)");
	}
	public OracleResultSet  file_list(){
	 	Connection conn =  db_main.JConnection.connectdb();   
		String sql = "Select * from fileh";
		OraclePreparedStatement pst;
		OracleResultSet rs=null;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			rs =  (OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error1" + e);
		}
		return rs;
	}
	public String getFilePath(int fid) throws SQLException {
	 	Connection conn =  db_main.JConnection.connectdb();   
		String sql = "Select path from fileh where fid=?";
		OraclePreparedStatement pst;
		OracleResultSet rs=null;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			pst.setInt(1,fid);
			rs =  (OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			rs.next();
			String res = rs.getString("path");
			return res;
		}catch (SQLException e) {
			return null;
		}
			
			

	}
	public boolean delete(String filepath) {
		
		

	      try
	        {
	    	  	@SuppressWarnings("unused")
				File f = new File(filepath);
	            Files.deleteIfExists(Paths.get(filepath));
	        	deleteFileDB(filepath);
	    		return true;
	        }
	        catch(NoSuchFileException e)
	        {
	            System.out.println("No such file/directory exists");
	        }
	        catch(DirectoryNotEmptyException e)
	        {
	            System.out.println("Directory is not empty.");
	        }
	        catch(IOException e)
	        {
	            System.out.println("Invalid permissions.");
	        }catch(NullPointerException e)
	      	{
	        	System.out.println("wan't found...");
	        	return false;
	      	}
		
		return false;

	}
	private void deleteFileDB(String filepath) {
	 	Connection conn =  db_main.JConnection.connectdb();   
		String sql = "delete from fileh where path=?";
		OraclePreparedStatement pst;
		@SuppressWarnings("unused")
		OracleResultSet rs=null;
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement(sql);
			pst.setString(1,filepath);
			rs =  (OracleResultSet) pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}*/
	
}
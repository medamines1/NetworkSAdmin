package api;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import db_main.JConnection;
import oracle.jdbc.OracleResultSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.OutputStream;


public class machine implements UIcomponent{
	
	private String cookies;
	
	
	public machine(String ip,int port,String username,String password) {
	
		try {
			connect(s_host(ip,port), username, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
	
	
	public machine(String host,String username,String password) {

		try {
			connect(host, username, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

	@Override
	public String s_host(String ip, int port) {
		// TODO Auto-generated method stub
		return "http://"+ip+":"+port;
	}

	@Override
	public String send_msg(String host, String msg, String t) throws Exception {
		String urlParameters = "msg="+msg+"&"+"title="+t;
		return send_post(host+"/c_msg", urlParameters, get_cookies(),null);
		
	}

	@Override
	public String get_cookies() {
		// TODO Auto-generated method stub
		return cookies;
	}
	
	@Override
	public void set_cookies(String cookies) {
		// TODO Auto-generated method stub
		 this.cookies=cookies;
	}

	@Override
	public String connect(String host,String username,String password) throws Exception {
		// TODO Auto-generated method stub
		String urlParameters = "username="+username+"&"+"password="+password; 
		String response = send_post(host, urlParameters, null,"save");
		
		
		return response;
	}
	public String  sendGET(String host,String cookies) throws IOException {
		URL obj = new URL(host);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		if (cookies!=null)
			con.setRequestProperty("Cookie", cookies); 
		con.setRequestMethod("GET");
		StringBuffer response;
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		
		} else {
			response = new  StringBuffer("GET request not worked " + responseCode) ;  
		}
		return response.toString();

	}

	public String send_post(String host,String urlParameters,String cookies,String connect) throws Exception 
	{
		try {
		URL obj = new URL(host);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		if (cookies!=null)
		con.setRequestProperty("Cookie", cookies); 
		con.setDoOutput(true);
		OutputStream os = (OutputStream) con.getOutputStream();
		os.write(urlParameters.getBytes());
		os.flush();
		os.close();

		
		int responseCode = con.getResponseCode();
		//System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			
			if(cookies == null) {
			StringBuilder sb = new StringBuilder();

			// find the cookies in the response header from the first request
			List<String> lcookies = con.getHeaderFields().get("Set-Cookie");
			if (lcookies != null) {
			    for (String Scookie : lcookies) {
			        if (sb.length() > 0) {
			            sb.append("; ");
			        }

			        // only want the first part of the cookie header that has the value
			        String value = Scookie.split(";")[0];
			        sb.append(value);
			    }
			}
			
			set_cookies(sb.toString());
			
			}
			return response.toString();
			
		}
		}catch(ConnectException e) {
			e.printStackTrace();
			
		}
				
		return null;
	}          
		
        
	
	public String send_bhv(String host) {
		String response;
		try {
			response = sendGET(host, get_cookies());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = e.toString();
		}
		
		return response;
	}


	public String send_cmd(String host,String cmd) {
		String response;
		try {
			String urlParameters = "action="+cmd; 
			response = send_post(host, urlParameters, get_cookies(),null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = e.toString();
		}
		
		return response;
	}
	
	
	public static String get_list_machine(String type) {
		Map<Integer,String> map = new LinkedHashMap<Integer,String>();
		try {
			OracleResultSet rs = JConnection.execute("select * from tbl_machine where type='"+type+"'");
			int i=0;
			while(rs.next()) {
				Map<String,String> tmp =new HashMap<String,String>();
				tmp.put("id",rs.getString("ID"));
				tmp.put("nom",rs.getString("NOM"));
				tmp.put("port",rs.getString("PORT"));
				tmp.put("host",rs.getString("HOST"));
				tmp.put("type",rs.getString("TYPE"));
				tmp.put("status",rs.getString("STATUS"));
				tmp.put("timestamp",rs.getString("TIMESTAMP"));
				
				map.put(i,new JSONObject(tmp).toString());
				i++;
			}		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	
		return new JSONObject(map).toString();
		
	}
	public  static boolean newMachine(String name,String port, String host, String type) {
		
		try {
			String sql =String.format("insert into tbl_machine values(%s,'%s',%s,'%s','%s','down',default)",JConnection.length_table("tbl_machine"),name,port,host,type);
			JConnection.execute(sql);
			return true;	
		} catch (Exception e) {
			return false;
		}
		
		
	}
  
	
}
	


	
	

	

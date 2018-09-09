package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestActions {
	
	private String cookies;
	
	public RequestActions(String host,String username,String password) {
		
		try {
			connect(host, username, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	public String s_host(String ip, int port) {
		return "http://"+ip+":"+port;
	}



	
	public String  sendGET(String host) throws IOException {
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

	public String send_post(String host,String urlParameters,String connect) throws Exception 
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
			
			
			}
			return response.toString();
			
		}
		}catch(ConnectException e) {
			e.printStackTrace();
			
		}
				
		return null;
	} 
	

	public String connect(String host,String username,String password) throws Exception {
		String urlParameters = "username="+username+"&"+"password="+password; 
		String response = send_post(host, urlParameters,"save");
		
		
		return response;
	}
	
	public void set_cookies(String cookies) {
		 this.cookies=cookies;
	}
	
	public String get_cookies() {
		return cookies;
	}
}

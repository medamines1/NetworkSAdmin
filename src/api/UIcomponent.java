package api;

public interface UIcomponent {

	//public boolean connect(String ip,int port);
	public String get_cookies();
	public void set_cookies(String cookies);
	public String connect(String host,String username,String password) throws Exception;//return cookies
	public String s_host(String ip,int port);
	public String send_msg(String host,String t,String msg) throws Exception;
	
	
}

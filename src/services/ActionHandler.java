package services;

import services.RequestActions;

public class ActionHandler {
	
	private String username= "med";
	private String password= "med";
	private RequestActions ra;
	
	public ActionHandler(String host) {
		ra = new RequestActions(host, username, password);
	}
	
	public String send_msg(String host, String msg, String t) throws Exception {
		String urlParameters = "msg="+msg+"&"+"title="+t;
		return ra.send_post(host+"/c_msg", urlParameters, null);
		
	}
	public String send_bhv(String host) {
		String response;
		try {
			response = ra.sendGET(host);
		} catch (Exception e) {
			e.printStackTrace();
			response = e.toString();
		}
		
		return response;
	}


	public String send_cmd(String host,String cmd) {
		String response;
		try {
			String urlParameters = "action="+cmd; 
			response = ra.send_post(host, urlParameters, null);
		} catch (Exception e) {
			e.printStackTrace();
			response = e.toString();
		}
		
		return response;
	}
	
}

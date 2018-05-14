package wsprotcol;




import java.util.HashMap;


import javax.websocket.CloseReason;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import org.json.JSONObject;

import db_main.JConnection;
import main.Compte;



@ServerEndpoint(value="/ws/chat",configurator=GetHttpSessionConfigurator.class)
public class handle_chat {
	 	@OnOpen
	 	public void onOpen(Session session) {
	        System.out.println("Connected ... " + session.getId());
	    }
	 	
	    @OnMessage
	    public String onMessage(String message, Session session) {  

	        	String id = (String) session.getUserProperties().get("httpsession");
	        	JSONObject data=null;
	        	
	        	String action=null;
				try {
		        	data = JConnection.getGson(message);
		        	System.out.println(data.toString());
		        	action = data.get("action").toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println(e1.toString()); 
				} 
	        	if (action==null) {
					 HashMap<String, String> myMap = new HashMap<String, String>();
	        		 myMap.put("resp","true");
	        		 myMap.put("error","true");
	        		 myMap.put("error_msg","unknow action");
	        		 
	        		return  JConnection.toGson(myMap);
	        	}
				switch (action) {
	        	
				case "send msg":
					try {
						
						boolean rs =Compte.send_msg(Integer.parseInt(id),Integer.parseInt(data.get("receiver").toString()), data.get("msg").toString(),data.get("subj").toString(),data.get("placedin").toString());
						 HashMap<String, String> myMap = new HashMap<String, String>();
						 String stat = "error";
						 if(rs)
							  stat = "success";
		        		 myMap.put("resp","true");
		        		 myMap.put("error",String.valueOf(rs));
		        		 myMap.put("action",action);
		        		 myMap.put("state",stat);
		        		 return  JConnection.toGson(myMap);						
						
					} catch ( Exception s) {
						// TODO Auto-generated catch block
						//s.printStackTrace();
						 HashMap<String, String> myMap = new HashMap<String, String>();
		        		 myMap.put("resp","true");
		        		 myMap.put("error","true");
		        		 myMap.put("error_msg",s.toString());
		        		 myMap.put("state","error");
		        		 return  JConnection.toGson(myMap);
					}
				case "get list of msgs":
					try {
						return Compte.get_msgs(id);
					} catch (Exception e) {
						// TODO/: handle exception
						e.printStackTrace();
						
					}
					
					break;
				case "check new msg":
					try {
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					break;
				default:
					break;
				}
				
	            

	        return message;
	    }
	    
	    
	    @OnClose
	    public void onClose(Session session, CloseReason closeReason) {
	    	System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
	    }
	 
	 

}
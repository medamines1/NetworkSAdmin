package wsprotcol;

import java.util.HashMap;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import db_main.JConnection;
import error_pack.error_handl;
import main.Compte;

@ServerEndpoint(value="/ws/calendar",configurator=GetHttpSessionConfigurator.class)
public class ws_calendar {
 	@OnOpen
 	public void onOpen(Session session) {
       
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
			case "get_my_dates" :
				try {
					String  rs = Compte.get_list_date(id);
					return error_handl.false_err(rs,action,"false","true","true");				
				} catch (Exception e) {
					return error_handl.true_err(e.toString());
				}
			case "insert_my_dates" : 
				try {
					@SuppressWarnings("unused")
					String title = "none",start ="none",bg = "none" ,end = "none";
					title = data.getString("title");
					start = data.getString("start");
					bg  =  data.getString("bg");
					end = data.getString("end");
									
					 Compte.insert_date(id, title, start, bg, start); //for the moment end ==start;
					return error_handl.false_err("",action,"true","false","false");					
				} catch (Exception e) {
					return error_handl.true_err(e.toString());
				}		
			case "delete_event":
				try {
					Compte.delete_date(id,data.getString("id"));
					return error_handl.false_err("",action,"true","true","false");	
				} catch (Exception e) {
					e.printStackTrace();
					return error_handl.true_err(e.toString());
				}
				
			default :break;
			}
			

        return message;
    }
    
    @OnError
    public void onError(Throwable t, Session session) {
    	 

    	}
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	//System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
 

}

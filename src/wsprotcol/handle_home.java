package wsprotcol;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import api.machine;
import db_main.JConnection;
import main.Compte;

@ServerEndpoint(value="/ws/home",configurator=GetHttpSessionConfigurator.class)
public class handle_home {
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
        	
			case "get_all_pack":
				try {
					
					String computer = String.valueOf(JConnection.executeWithNext("select count(1) from tbl_machine where type='computer' ").getInt("count(1)"));
					String phones = String.valueOf(JConnection.executeWithNext("select count(1) from tbl_machine where type='phones' ").getInt("count(1)"));
					String printers = String.valueOf(JConnection.executeWithNext("select count(1) from tbl_machine where type='printer' ").getInt("count(1)"));
					String others = String.valueOf(JConnection.executeWithNext("select count(1) from tbl_machine where type='others' ").getInt("count(1)"));
					String msg = String.valueOf(JConnection.executeWithNext("select count(1) from msg_db where (sender="+id+" or receiver="+id+") and placedin='Sent' ").getInt("count(1)"));
					String donline = String.valueOf(JConnection.executeWithNext("select count(1) from tbl_machine where status='active' ").getInt("count(1)"));
					

					
	
					
					HashMap<String, String> myMap = new HashMap<String, String>();
					 
					 myMap.put("computers",computer);
					 myMap.put("phones",phones);
					 myMap.put("printers",printers);
					 myMap.put("others",others);
					 myMap.put("msgs",msg);
					 myMap.put("donlines",donline);
					 
	        		 
					 
					 
					 myMap.put("resp","true");
	        		 myMap.put("hiden","true");
	        		 myMap.put("error",String.valueOf(false));
	        		 myMap.put("action",action);
	        		 myMap.put("state","none");
	        		 return  JConnection.toGson(myMap);						
					
				} catch ( Exception s) {
					//s.printStackTrace();
					 HashMap<String, String> myMap = new HashMap<String, String>();
	        		 myMap.put("resp","true");
	        		 myMap.put("error","true");
	        		 myMap.put("error_msg",s.toString());
	        		 myMap.put("state","error");
	        		 return  JConnection.toGson(myMap);
				}
			case "get_list_of_msgs":
				try {
					return Compte.get_msgs(id);
				} catch (Exception e) {
					 HashMap<String, String> myMap = new HashMap<String, String>();
	        		 myMap.put("resp","true");
	        		 myMap.put("error","true");
	        		 myMap.put("error_msg",e.toString());
	        		 myMap.put("state","error");
	        		 return  JConnection.toGson(myMap);
					
				}
				
				
			case "get_data":
				try {
				String comp = machine.get_list_machine("computer");
				String pho = machine.get_list_machine("phones");
				String printe = machine.get_list_machine("printers");
				String oth = machine.get_list_machine("others");
				
				Map<String,String> myMap = new HashMap<>();
				 myMap.put("computers",comp);
				 myMap.put("phones",pho);
				 myMap.put("printers",printe);
				 myMap.put("others",oth);
				 
				 myMap.put("resp","true");
        		 myMap.put("hiden","true");
        		 myMap.put("error",String.valueOf(false));
        		 myMap.put("action",action);
        		 myMap.put("state","none");
				 
				 
				 	

				 return new JSONObject(myMap).toString();
				}catch(Exception e) {
					e.printStackTrace();
					 HashMap<String, String> myMap = new HashMap<String, String>();
	        		 myMap.put("resp","true");
	        		 myMap.put("error","true");
	        		 myMap.put("error_msg",e.toString());
	        		 myMap.put("state","error");
					return null;				
				}							
			
			default:
				break;
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

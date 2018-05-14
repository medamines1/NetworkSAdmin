package wsprotcol;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import MyExceptions.UserNotIdentifiedPassive;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator  
{
    @Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
    	
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        //Map<String,List<String>> headers = request.getHeaders();
       try {
    	   String data = (String) httpSession.getAttribute("user_id");
    	   config.getUserProperties().put("httpsession",data);
	} catch (Exception e) {
		throw new UserNotIdentifiedPassive("user is not logged in !");
		
   
	}
        
        
    }
}
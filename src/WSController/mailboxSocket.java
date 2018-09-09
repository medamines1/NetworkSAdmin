package WSController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import Mydaos.UserDetailsDao;
import WSInput.Myaction;
import WSInput.msgIn;
import WSModel.MessageAction;
import WSModel.ResponseAction;
import hibernateClasses.User;
import services.ActionHandler;
import services.servMachines;
import services.servMgs;



@Controller
public class mailboxSocket extends TextWebSocketHandler  {
		
	@Autowired
	private servMgs srm;
	
	@Autowired
	private UserDetailsDao udd;

	
	  
		@MessageMapping("/messages/send")
		@SendTo("/topic/messages/send")
		public ResponseAction sendMsg(msgIn msg,Principal p) throws Exception {
			
			User sender = udd.findUserByUsername(p.getName());
			User receiver = udd.findUserByUsername(msg.getReceiver());
			if (receiver !=null && sender !=null ) {
				 srm.sendMsg(sender, receiver, msg.getMsg(), msg.getPlacedin(), msg.getSubj());
				 return new ResponseAction(false,true,true,"message sent");
			}
			return new ResponseAction(true,true,true,"user doesn't existe");
		}
		
		
		@MessageMapping("/messages/getAll")
		@SendTo("/topic/messages/getAll")
		public ResponseAction getAllmsgs(Principal p) throws Exception {
			

			return new ResponseAction(JSONObject.valueToString(srm.getMymessages(p.getName())),true);
		}
	
	
	
	
}

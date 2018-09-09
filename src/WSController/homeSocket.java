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
public class homeSocket extends TextWebSocketHandler  {
		
	@Autowired
	private servMgs srm;
	
	
	@Autowired
	private servMachines smi;
	
	@MessageMapping("/messages/mymsgs")
	@SendTo("/topic/messages/mymsgs")
	public ResponseAction mymsgs(MessageAction msg,Principal p) throws Exception {
		//change the function to return a specific number of msgs at the time
		return new ResponseAction(JSONObject.valueToString(srm.getMymessages(p.getName())),true) ;		
	}
	

	
	@MessageMapping("/messages/new/{id}")
	@SendTo("/topic/messages/new")
	public ResponseAction getNewmsgs(@DestinationVariable String id,Principal p) throws Exception {
		return new ResponseAction(JSONObject.valueToString(srm.getNewMsgs(p.getName(), Long.valueOf(id.trim()))),true);
	}
	
	
	
	
	
	@MessageMapping("/home/getNumbers")
	@SendTo("/topic/home/getNumers")
	public ResponseAction getAllNumbers(Principal p) throws Exception {
		Map<String,Long> m = new HashMap<>();
		m.put("computers", smi.getLengthofmach("computers"));
		m.put("phones", smi.getLengthofmach("phones"));
		m.put("printers", smi.getLengthofmach("printers"));
		m.put("others", smi.getLengthofmach("others"));

		m.put("donlines", smi.getLengthActive());
		m.put("msgs", srm.getLengthofmsgs(p.getName()));
		
		return new ResponseAction(JSONObject.valueToString(m),true);
	}
	
	
	@MessageMapping("/home/getMachines")
	@SendTo("/topic/home/getMachines")
	public ResponseAction getAllMachines(Principal p) throws Exception {
		String a =JSONObject.valueToString(smi.getAllMachines());
		
		return new ResponseAction(a,true);
	}
	
	
	@MessageMapping("/home/action/sendmsg")
	@SendTo("/topic/home/action/send")
	public ResponseAction ActionsendMsg(Myaction action,Principal p) throws Exception {
		try{
		String host = action.getHost();
		JSONObject jso = new JSONObject(action.getArgs());
		ActionHandler ah = new ActionHandler(host);
		ah.send_msg(host, jso.get("c_msg").toString(), "MSG from admin ");
		
		return new ResponseAction("success",true);
		}catch (Exception e) {
			return new ResponseAction(true,true,e.getMessage());
		}
	}
	
	@MessageMapping("/home/action/sendbhv")
	@SendTo("/topic/home/action/sendbhv")
	public ResponseAction ActionsendBhv(Myaction action,Principal p) throws Exception {
		try{
			String host = action.getHost();
			JSONObject jso = new JSONObject(action.getArgs());
			ActionHandler ah = new ActionHandler(host);
			String order = jso.get("action").toString();
			ah.send_bhv(host+order);
		
			
			return new ResponseAction("success",true);
		}catch (Exception e) {
			return new ResponseAction(true,true,e.getMessage());
		}
	}
	
	
	



	
	
	
	
}

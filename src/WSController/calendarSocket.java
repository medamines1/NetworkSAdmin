package WSController;

import java.security.Principal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import Mydaos.UserDetailsDao;
import WSInput.calendInp;
import WSModel.ResponseAction;
import services.calServ;



@Controller
public class calendarSocket extends TextWebSocketHandler  {
		
	@Autowired
	private calServ cas;

	@Autowired
	private UserDetailsDao udd;
	  
		@MessageMapping("/calendar/getAll")
		@SendTo("/topic/calendar/getAll")
		public ResponseAction getAllDates(Principal p) throws Exception {
			return new ResponseAction(JSONObject.valueToString(cas.getAllDat(p.getName())),true);
		}
		
		
		@MessageMapping("/calendar/new")
		@SendTo("/topic/calendar/new")
		public ResponseAction newdate(calendInp cal,Principal p) throws Exception {
			
		if (cas.create(udd.findUserByUsername(p.getName()), cal.getTitle(), cal.getBg(), cal.getD_Start(), cal.getEnd())) 
			 return new ResponseAction(false,true,true,"date added");
		
		return new ResponseAction(true,true,true,"something went wrong");
	}
	
		
		
		@MessageMapping("/calendar/delete/{did}")
		@SendTo("/topic/calendar/delete")
		public ResponseAction newdate(@DestinationVariable long did,Principal p) throws Exception {
			
		if (cas.delete(p.getName(), did)) 
			 return new ResponseAction(false,true,true,"date deleted");
		
		return new ResponseAction(true,true,true,"something went wrong");
	}
	
	
	
	
	
}

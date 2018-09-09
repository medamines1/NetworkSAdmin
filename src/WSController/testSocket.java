package WSController;

import java.security.Principal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import WSModel.MessageAction;
import WSModel.ResponseAction;


@Controller
public class testSocket extends TextWebSocketHandler  {

	@MessageMapping("/hi")
	@SendTo("/topic/back")
	public ResponseAction receiverHi(MessageAction msg,@Payload String payload,Principal p) throws Exception {
		System.out.println("hello " + p.getName() + " : " + payload);
		
		return new ResponseAction(payload,true);		
	}
	

	
	

}

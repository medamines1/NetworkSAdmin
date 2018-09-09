package controllersPages;

import java.security.Principal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Mydaos.UserDetailsDao;
import WSInput.msgIn;
import WSModel.ResponseAction;
import hibernateClasses.User;
import services.servMgs;

@Controller
public class pmailbox {

	
	@Autowired
	private UserDetailsDao udd;
	
	@Autowired
	private servMgs srm;

	  @GetMapping(value="/mailbox")
	  public ModelAndView mainPage(ModelMap model) {
		  ModelAndView m = new  ModelAndView("mailbox");
		  return m;
	  }
	  
	  @GetMapping(value="/compose")
	  public ModelAndView composePage(ModelMap model) {
		  ModelAndView m = new  ModelAndView("compose");
		  return m;
	  }

}

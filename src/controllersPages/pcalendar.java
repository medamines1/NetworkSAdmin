package controllersPages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class pcalendar {
	
	
	  @ResponseBody
	  @GetMapping(value="/calendar")
	  public ModelAndView mainPage(ModelMap model) {
		  ModelAndView m = new  ModelAndView("calendar");
		  return m;
	  }
}

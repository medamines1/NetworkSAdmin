package controllersPages;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Mydaos.UserDetailsDao;
import hibernateClasses.User;
import modelApi.ResponseModel;
import services.file_main;


@Controller
@MultipartConfig
public class index {
		
	  @Autowired
	  private UserDetailsDao userDts; 
	   

	
	  
	  @GetMapping(value="/login")
	  public ModelAndView mainPage(ModelMap model) {
		  ModelAndView m = new  ModelAndView("login");
		  return m;
	  }
	  
	  
	  @GetMapping(value="/")
	  public String indexPage(ModelMap model) {  
		  
		 return "nsa";
	  }
	  
	  @PostMapping(value="/test")
	  @ResponseBody
	  public String pos(@SessionAttribute("JSESSIONID") String j) {
		 
		  return "success" + j;  
	  }
	  
	  
	  @PostMapping("/deletuser")
	  @ResponseBody
	  public String delteUser(@ModelAttribute  User user) {
		try {
			userDts.delete(user);
			return ResponseModel.response_success();
		} catch (Exception e) {
			return ResponseModel.response_failed(e.getMessage());//create the function that changes the dev error to user error
		}
		  
	  } 
	  
	  
	  
	  @PostMapping("/deletuserid")
	  @ResponseBody
	  public String delteUser(@ModelAttribute  int id) {
		try {
			userDts.delete(id);
			return ResponseModel.response_success();
		} catch (Exception e) {
			return ResponseModel.response_failed(e.getMessage());//create the function that changes the dev error to user error
		}
		  
	  } 
	  
	  @PostMapping("/deletuserusername")
	  @ResponseBody
	  public String delteUser(@ModelAttribute  String username) {
		try {
			userDts.delete(username);
			return ResponseModel.response_success();
		} catch (Exception e) {
			return ResponseModel.response_failed(e.getMessage());//create the function that changes the dev error to user error
		}
		  
	  } 
	  
	  
	  
	  @GetMapping("/getusers")
	  @ResponseBody
	  public String getUsers() {
		try {
			
			return ResponseModel.response_success(JSONObject.valueToString(userDts.list()));
		} catch (Exception e) {
			return ResponseModel.response_failed(e.getMessage());//create the function that changes the dev error to user error
		}
		  
	  }
	  
	  
	  
	  @GetMapping("/getusersbname")
	  @ResponseBody
	  public String getUsersByName() {
		try {
			return ResponseModel.response_success(JSONObject.valueToString(userDts.list()));
		} catch (Exception e) {
			return ResponseModel.response_failed(e.getMessage());//create the function that changes the dev error to user error
		}
		  
	  }
	  @RequestMapping(value = {"/logout2"}, method = RequestMethod.GET)
	  public String logoutDo(HttpServletRequest request,HttpServletResponse response){
	  HttpSession session= request.getSession(false);
	      SecurityContextHolder.clearContext();
	           session= request.getSession(false);
	          if(session != null) {
	              session.invalidate();
	          }
	          for(Cookie cookie : request.getCookies()) {
	              cookie.setMaxAge(0);
	          }

	      return "login";
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  @PostMapping(value = {"/file"})
	  @ResponseBody
	  public String SEndFile(HttpServletRequest req,HttpServletResponse resp,Principal p){
		 
	      DiskFileItemFactory factory = new DiskFileItemFactory();
		   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(40 * 1024);

	      factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // maximum file size to be uploaded.
	      upload.setSizeMax(  5000 * 1024);
	     
		  
		  file_main f = new file_main();
		  
		  
		  try {
			if (f.upload_file(req,resp,p.getName(),upload)) 
				  return "success";
				else
					return "failed";
		  }catch (IOException | ServletException e) {
			return "failed";
		  }
	  }
}
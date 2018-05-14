package main;

import java.io.*;  
  
import javax.servlet.*;  
import javax.servlet.http.*;

import MyExceptions.UserNotIdentifiedException;      
public class manageD extends HttpServlet 
{    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3860167757216561919L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ,IllegalStateException
	{

	      response.setContentType("text/html");      
	      
	      try {
			Compte.connected(request, response);
			 request.getRequestDispatcher("NSA/nsa.html").forward(request, response);  
		} catch (UserNotIdentifiedException e) {
			request.getRequestDispatcher("NSA/login.html").forward(request, response);
			return;
		}
	         	


	}
} 
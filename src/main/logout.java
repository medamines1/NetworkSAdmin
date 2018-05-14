package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logout extends HttpServlet{
	/**
	 * 
	 */

	private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null)
	        for (Cookie cookie : cookies) {
	            cookie.setValue("");
	            cookie.setPath("/");
	            cookie.setMaxAge(0);
	            resp.addCookie(cookie);
	        }
	}
	private static final long serialVersionUID = -6343786169708655582L;
	@Override
	public void init() throws ServletException {
		super.init();

		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         	              	 
	    	   HttpSession session=request.getSession();  
	           session.invalidate();  
	           eraseCookie(request, response);
	           response.setHeader("Cache-Control", "no-cache, no-store");
	           response.setHeader("Pragma", "no-cache");
	       request.getRequestDispatcher("NSA/login.html").forward(request, response);
		
	}

}

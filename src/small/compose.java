package small;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyExceptions.UserNotIdentifiedException;
import main.Compte;

public class compose extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8932780006382106393L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	      try {
				Compte.connected(req, resp);
			} catch (UserNotIdentifiedException e) {
				req.getRequestDispatcher("NSA/login.html").forward(req, resp);
				return;
			}
		
		req.getRequestDispatcher("NSA/compose.html").forward(req, resp);
		
	}
}

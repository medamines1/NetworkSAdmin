package small;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MyExceptions.UserNotIdentifiedException;
import main.Compte;

public class calendar  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3429398733915764558L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      try {
				Compte.connected(req, resp);
			} catch (UserNotIdentifiedException e) {
				req.getRequestDispatcher("NSA/login.html").forward(req, resp);
				return;
			}
		req.getRequestDispatcher("NSA/calendar.html").forward(req, resp);
	}
	

}

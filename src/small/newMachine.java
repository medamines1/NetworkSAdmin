package small;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.machine;

@SuppressWarnings("serial")
public class newMachine extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name  = req.getParameter("name");
			String port  = req.getParameter("port");
			String host  = req.getParameter("host");
			String type  = req.getParameter("type");
			System.err.println(name+ port + host + type);
			machine.newMachine(name, port, host, type);
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
}

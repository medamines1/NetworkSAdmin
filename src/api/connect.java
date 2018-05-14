package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Compte;

public class connect extends HttpServlet {

		/**
	 * 
	 */
	private static final long serialVersionUID = -7605265537803683707L;

		@Override
		public void init() throws ServletException {
			super.init();

		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html"); 
		    PrintWriter pwriter = response.getWriter();
		    String resp = "";
			try {
				String host ="http://"+ request.getParameter("host");
				String action =request.getParameter("action");
				String from = request.getServerName();
				machine m = new machine(host+"/authenticate","med","med");
				HttpSession session=request.getSession();  
				Compte.assign_history(Integer.valueOf(session.getAttribute("user_id").toString()), action, from, host);
				
				if (action.equals("c_msg")) {
					//String title =request.getParameter("title");
					String msg =request.getParameter("msg");
					m.send_msg(host, msg,"MSG FROM admin");
				   
									
				}else if (action.contains("behaviour")) {
					m.send_bhv(host+action);				
										
				}else if(action.contains("execute")) {
					String cmd = request.getParameter("cmd");
					resp = m.send_cmd(host+action,cmd);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		 
			pwriter.println(resp);
			pwriter.println("done");
			 	
		
		}
		
		

}

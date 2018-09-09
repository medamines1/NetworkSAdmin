package configurations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
	 
		//private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,
				HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
	        //set our response to OK status
	        response.setStatus(HttpServletResponse.SC_OK);
	        
	        HttpSession session = request.getSession();
	        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        session.setAttribute("username", authUser.getUsername());
	        // put a way to set the useid on login
	        //session.setAttribute("user_id", );
	        //session.setAttribute("authorities", authentication.getAuthorities());
	        
	        
	      response.getWriter().print("success");
	        
	       // logger.info("AT onAuthenticationSuccess(...) function!");
	      
	        

		}
		
}
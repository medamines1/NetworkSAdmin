package configurations;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import hibernateClasses.machines;

/*

this code get all machines stored in the database and test if they their connected and update their status  in db

*/
@Configuration
@EnableScheduling
@Transactional
public class schedualeTaks {
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	Session session;
	
	boolean rs
	
	;
    @Scheduled(fixedDelay=5000)
    @Transactional
    public void ping_all() {
	    System.out.println("[*] Running ping : ");
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<machines> list = session.createQuery("from machines ").list();
		boolean m;
		for (machines i: list) {
			m = false;
			try {
				rs = ping(i.getHost(), i.getPort());
				if (rs && i.getStatus().equals("down")) {
					i.setStatus("active");
					m = true;
					}
				else if (!rs && i.getStatus().equals("active")) {
					i.setStatus("down");
					m=true;
				}
				System.out.println(rs + "||||" + i.toString());
				if (m) {
					session.update(i);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			}
      
    }
    
    public static boolean ping(String host,String port) throws IOException {
    	Socket s;
    	try {
    		s = new Socket(host, Integer.valueOf(port));
    		s.getOutputStream().write((byte) '\n');
    		s.getInputStream().read();
    		s.close();
    		return true;
    	 } catch (Exception e) {
    		return false;
    	}


    	
    	
    }
}
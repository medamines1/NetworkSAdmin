package task_per;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.internal.OraclePreparedStatement;

public class ping_all  implements ServletContextListener {

public static Connection conn; 
private static String status;
private static String host;
private static int port;
private static int id; 
private static boolean f;
public static OracleResultSet query_all_devices() throws SQLException {
	
	conn =  db_main.JConnection.connectdb();   
	String sql = "Select * from tbl_machine";
	OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
	OracleResultSet rs =  (OracleResultSet) pst.executeQuery();
	//conn.close();
	return rs;
}

public static boolean ping(String host,int port) throws IOException {
	Socket s;
	try {
		s = new Socket(host, port);
		s.getOutputStream().write((byte) '\n');
		int ch = s.getInputStream().read();
		s.close();
		System.out.println(ch);
		return true;
	 } catch (ConnectException e) {
		return false;
	}


	
	
}



@Override
public void contextDestroyed(ServletContextEvent arg0) {
	System.out.println("[*] goodbye ...");
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Override
public void contextInitialized(ServletContextEvent arg0) {
	// TODO Auto-generated method stub
	System.out.println("[*] hello ");
	TimerTask vodTimer = new VodTimerTask();

	Timer timer = new Timer();
	timer.schedule(vodTimer, 1000, (2 * 1000));
}

class VodTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("[*] running a ping");
		OracleResultSet rs;
		try {
			rs = query_all_devices();
			
		while(rs.next()) {
			 
			host = rs.getString("host");
			port = rs.getInt("port");
			 id = rs.getInt("id");
			 status= rs.getString("status");
			 boolean m = false;

			  f =ping(host, port);
	
				
			if ( f && status.equals("down")) {
				status = "active";
				m=true;
			}else if (!f && status.equals("active")) {
				status = "down";
				m=true;
			}
			System.out.println("Id: "+id+"\t"+"Host: "+host+"\t"+"Port: "+port+"\t"+"Status: "+status);
			if(m) {
				conn =  db_main.JConnection.connectdb();   
				String sql = "update tbl_machine  set status=? where id=?";
				OraclePreparedStatement pst = (OraclePreparedStatement) conn.prepareStatement(sql);
				pst.setString(1, status);
				pst.setInt(2, id);
				
				pst.executeQuery();
				
				
			}	
			
			
		}
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
}

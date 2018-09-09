package Mydaos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hibernateClasses.User;
import hibernateClasses.calendar;
import hibernateClasses.calendar_key;

@Repository
@Transactional
public class calenDAO  implements calInt{

	@Autowired
	private SessionFactory sessionFactory;	
	
	private Session session;
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public List<String> getAllDat(String username) {
		try {
			@SuppressWarnings("unchecked")
			Map<String,String> m = new HashMap();
			session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			Query<Object[]> query = session.createQuery("select id,title,d_start,bg from calendar where  user.username = :value  ");
			query.setParameter("value", username);
			
			List<Object[]> list = query.list();
			List<String> fi = new ArrayList<>();
			for (Object[] i : list) {
				m.put("id",String.valueOf(i[0]));
				m.put("title",(String)i[1]);
				m.put("start",(String)i[2]);
				m.put("color",(String)i[3]);
				fi.add(JSONObject.valueToString(m));
			}
			
			return fi;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	@Override
	public boolean create(User  user, String title, String bg, String d_start, String end) {
		try {
			session = sessionFactory.getCurrentSession();
			calendar c = new calendar( title, d_start, bg, end);
			c.setUser(user);
			session.save(c);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	@Override
	public boolean delete(String username, long id) {
		
		try {
			session = sessionFactory.getCurrentSession();
			Query c = session.createQuery("delete from calendar where user.username=:usern and id = :did");
			c.setParameter("usern", username);
			c.setParameter("did", id);
			c.executeUpdate();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

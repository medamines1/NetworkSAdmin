package Mydaos;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hibernateClasses.machines;

@Repository
@Transactional
public class machinesDao implements machinesInt {

	
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	private Session session;
	
	@Override
	@Transactional
	public Long getLengthofmach(String type) {
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Long> query = session.createQuery("select count(id) from machines where type = :type ");
		query.setParameter("type", type);
		return  (Long) query.uniqueResult();
	}

	@Override
	public Map<String, String> getAllMachines() {
		try{
			String[] type = {"computers","phones","printers","others"};
			Map<String, String>  m = new HashMap<>();
		for(String i : type) {
			session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			Query<machines> query = session.createQuery("from machines where type = :type ");
			query.setParameter("type", i);
			m.put(i,JSONObject.valueToString(query.getResultList()));
					
		}
		
		return m;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public Long getLengthActive() {
		
			session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			Query<Long> query = session.createQuery("select count(id) from machines where status = 'active' ");
			return  (Long) query.uniqueResult();

	}

}

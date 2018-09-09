package Mydaos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hibernateClasses.User;
import hibernateClasses.messages;


@Repository
@Transactional
public class msgsDao implements msgsInt {
	
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	private Session session;
	
	@Override
	@Transactional
	public messages findById(int id,String username) {
		session = sessionFactory.getCurrentSession();

		
		@SuppressWarnings("unchecked")
		Query<messages> query = session.createQuery("from messages where  = :value and sender = :value2");
		query.setParameter("value", id);
		query.setParameter("value2", username);
		return  (messages) query.uniqueResult();
	}

	@Override
	@Transactional
	public List<messages> findByField(String field, String value) {
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<messages> query = session.createQuery("from messages where id = 0");

		return query.list();
	}

	@Override
	@Transactional
	public void deleteById(int id,String username) {
		sessionFactory.getCurrentSession().delete(this.findById(id,username));;
		
	}
	
	
	@Override
	@Transactional
	public List<String> getMymessages(String username) {
		session = sessionFactory.getCurrentSession();

		
		@SuppressWarnings("unchecked")//avoid getting where the user is the receiver  and placedin is draft
		 Query<Object[]> query = session.createQuery("select sender.username,id,seen,placedin,subj,msg,reveiver.username from messages where  sender.username = :value or reveiver.username = :value  ");
		query.setParameter("value", username);
		List<Object[]> list = query.list();
		List<String> fi = new ArrayList<>();
		Map<String,String> m = new HashMap<>();
		for (Object[] i : list) {
			
			m.put("sender",   String.valueOf(i[0]));
			m.put("id",  String.valueOf(i[1]));
			m.put("seen",   String.valueOf(i[2]));
			m.put("placedin", String.valueOf(i[3]));
			m.put("subj",   String.valueOf(i[4]));
			m.put("msg",   String.valueOf(i[5]));
			m.put("receiver",  String.valueOf(i[6]));
			try {
				fi.add(JSONObject.valueToString(m));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return  fi;
		
	}
	
	@Override
	@Transactional
	public Long getLengthofmsgs(String username) {
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Long> query = session.createQuery("select count(id) from messages where  sender.username = :value or (reveiver = :value and placedin !='draft'  ) ");
		query.setParameter("value", username);
		return   query.uniqueResult();
	}

	@Override
	public boolean sendMsg(User sender,User receiver,String msg,String placedin,String subj) {
		try {
			
			messages m = new messages(sender,receiver,msg,false,placedin,subj);
			session = sessionFactory.getCurrentSession();
			session.save(m);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	
	@Override
	public List<messages> getNewMsgs(String username,Long lastmsg_id) {
		try {
			session = sessionFactory.getCurrentSession();
			Query<messages> query = session.createQuery("select sender.username,id,seen,placedin,subj,msg,reveiver.username from messages where  (sender.username = :value or reveiver.username = :value) and id > :lst ");
			query.setParameter("value", username);
			query.setParameter("lst", lastmsg_id);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	

}

package failExp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("oneForAll")
@Transactional
public class oneForAllImp implements oneForAll{
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	private Session session;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T>  T findById(T type, int id) {
		session = sessionFactory.getCurrentSession();
		return  (T) session.byNaturalId(type.getClass()).using("id", id).load();
	
	}

	@Override
	public <T> List<T> findByField(T type, String field, String value) {
		session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<T> query = session.createQuery("from :table where field = :value");
		query.setParameter("table", ((MagicClass)type).getValue());
		query.setParameter("field", field);
		query.setParameter("value", value);
		return query.list();

		
	}

	@Override
	public <T> void deleteById(T type, int id) {
		sessionFactory.getCurrentSession().delete(this.findById(type, id));;
		
	}

	@Override
	public <T> void deleteByField(T type, String field, String value) {
		sessionFactory.getCurrentSession().delete(this.findByField(type, field, value));;
		
	}

	@Override
	public <T> T findByObj(T type, T obj) {
		return  this.findById(type, ((MagicClass)obj).getId());
		
	}

	@Override
	public <T> void updateByField(T type, String field, String value,String fieldv, String valuev,T obj) {
	       Session session = this.sessionFactory.getCurrentSession();
	         @SuppressWarnings("rawtypes")
			 Query query = session.createQuery("update :table set :fieldv =:valuev where :field = :value "); 
	         
	         query.setParameter("fieldv", fieldv);  
	         
	         query.setParameter("field", field);
	         
	         query.setParameter("valuev", valuev); 
	         
	         query.setParameter("value", value); 
	         
	         query.setParameter("table",((MagicClass)obj).getValue() );  
	         
	         query.executeUpdate();

	}
}

package Mydaos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import hibernateClasses.User;

@Repository
@Transactional
public class UserDetailsDaoImp implements UserDetailsDao {

  @Autowired
  private SessionFactory sessionFactory;
  
  
  Session session;

  @Override
  public User findUserByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }
	
	@Override
	public boolean delete(int id) {
		
		try {
		
		User u = session.byId(User.class).load(id);
		session.delete(u);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	@Override
	public boolean delete(String username) {
		try {
		session = sessionFactory.getCurrentSession();
		User u = session.byNaturalId(User.class).using("username", username).load();
		session.delete(u);
		return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> list() {
		try {
		      session = sessionFactory.getCurrentSession();
		      CriteriaBuilder cb = session.getCriteriaBuilder();
		      CriteriaQuery<User> cq = cb.createQuery(User.class);
		      Root<User> root = cq.from(User.class);
		      cq.select(root);
		      Query<User> query = session.createQuery(cq);
		      return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Transactional
	@Override
	public void delete(User u) {		
			session = sessionFactory.getCurrentSession();
			session.delete(u);
		
	}
	

}

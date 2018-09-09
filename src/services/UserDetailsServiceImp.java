package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Mydaos.UserDetailsDao;
import hibernateClasses.User;

@Service
@Transactional()
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserDetailsDao userDetailsDao;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userDetailsDao.findUserByUsername(username);
    UserBuilder builder = null;
    if (user != null) {
      
      builder = org.springframework.security.core.userdetails.User.withUsername(username);
      builder.disabled(!user.isEnabled());
      builder.password(user.getPassword());
      String[] authorities = user.getAuthorities()
          .stream().map(a -> a.getAuthority()).toArray(String[]::new);

      builder.authorities(authorities);
    } else {
      throw new UsernameNotFoundException("User not found.");
    }
    return builder.build();
  }
  public boolean delete(int id) {
	  return userDetailsDao.delete(id);
  }
  public boolean delete(String username) {
	  return userDetailsDao.delete(username);
  }
  public User findUserById(int id) {
	  return userDetailsDao.findUserById(id);
  }
  public List<User> list(){
	  return userDetailsDao.list();
  }
  @Transactional
  public void update(User u) {
	  userDetailsDao.update(u);
  }
  @Transactional
  public void delete(User u) {
	  userDetailsDao.delete(u);
  }
}

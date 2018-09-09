package Mydaos;

import java.util.List;

import hibernateClasses.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
  boolean delete(int id);
  boolean delete(String username);
  User findUserById(int id);
  List<User> list();
  void update(User u);
  void delete(User u);
}

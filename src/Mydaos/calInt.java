package Mydaos;

import java.util.List;
import java.util.Map;

import hibernateClasses.User;

public interface calInt {
	public List<String> getAllDat(String username);
	public boolean create(User  user,String title,String bg,String d_start,String end);
	public boolean delete(String username,long id);

}

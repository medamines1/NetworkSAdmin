package services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mydaos.calInt;
import Mydaos.calenDAO;
import hibernateClasses.User;
import hibernateClasses.calendar;

@Service
public class calServ implements calInt {
	
	@Autowired
	private calInt cld;
	
	@Override
	public List<String> getAllDat(String username){
		return cld.getAllDat(username);
	}

	@Override
	public boolean create(User  user, String title, String bg, String d_start, String end) {
		return cld.create(user, title, bg, d_start, end);
	}

	@Override
	public boolean delete(String username, long id) {
		return cld.delete(username, id);
	}

}

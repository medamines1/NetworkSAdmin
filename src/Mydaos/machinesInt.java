package Mydaos;

import java.util.List;
import java.util.Map;

import hibernateClasses.machines;

public interface machinesInt {
	//public   messages findById(int id,String username);
	//public   List<messages> findByField(String field, String value);
	//public   void deleteById(int id,String username);
	//public   void updateB 
//	public   List<messages> getMymessages(String username);
	public Long getLengthofmach(String type);
//	boolean sendMsg(User sender, User receiver, String msg, String placedin, String subj);
//	List<messages> getNewMsgs(String username, Long lastmsg_id);
	public Map<String,String>  getAllMachines();
	public Long getLengthActive();


}

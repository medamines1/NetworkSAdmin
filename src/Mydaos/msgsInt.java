package Mydaos;

import java.util.List;

import hibernateClasses.User;
import hibernateClasses.messages;

public interface msgsInt {
	public   messages findById(int id,String username);
	public   List<messages> findByField(String field, String value);
	public   void deleteById(int id,String username);
	//public   void updateB 
	public   List<String> getMymessages(String username);
	public Long getLengthofmsgs(String username);
	boolean sendMsg(User sender, User receiver, String msg, String placedin, String subj);
	List<messages> getNewMsgs(String username, Long lastmsg_id);
	


}

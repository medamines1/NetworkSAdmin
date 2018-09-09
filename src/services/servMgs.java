package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mydaos.msgsInt;
import hibernateClasses.User;
import hibernateClasses.messages;


@Service
public class servMgs implements msgsInt {
	
	@Autowired
	private msgsInt msgd;
	
	@Override
	public messages findById(int id,String username) {
		return msgd.findById(id,username);
		
	}

	@Override
	public List<messages> findByField(String field, String value) {
		return msgd.findByField(field, value);
	}

	@Override
	public void deleteById(int id,String username) {
		msgd.deleteById(id,username);
		
	}
	
	public List<String> getMymessages(String username) {
		return msgd.getMymessages(username);
	}

	@Override
	public Long getLengthofmsgs(String username) {
		return msgd.getLengthofmsgs(username);
	}
	
	@Override
	public boolean sendMsg(User sender, User receiver, String msg, String placedin, String subj){
		return msgd.sendMsg(sender, receiver, msg, placedin, subj);
	}

	@Override
	public List<messages> getNewMsgs(String username, Long lastmsg_id) {
		return msgd.getNewMsgs(username, lastmsg_id);
	}

}

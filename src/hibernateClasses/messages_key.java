package hibernateClasses;

import java.io.Serializable;

@SuppressWarnings("serial")
public class messages_key  implements Serializable{
	

	private long id;
    
    private User sender;

	public messages_key() {}
	public messages_key(User sender) {
		this.sender = sender;
	}

	public messages_key(long id, User sender) {
		this.id = id;
		this.sender = sender;
	}
	@Override
	public String toString() {
		return "messages_key [id=" + id + ", sender=" + sender + "]";
	}
	
	
}

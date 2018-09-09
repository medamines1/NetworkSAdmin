package WSInput;

public class msgIn {
	
	
	public String receiver;
	public String msg;
	public String placedin;
	public String subj;
	
	
	
	public msgIn() {
		
	}
	
	public msgIn(String receiver, String msg, String placedin, String subj) {
		this.receiver = receiver;
		this.msg = msg;
		this.placedin = placedin;
		this.subj = subj;
	}

	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPlacedin() {
		return placedin;
	}
	public void setPlacedin(String placedin) {
		this.placedin = placedin;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	
	

}

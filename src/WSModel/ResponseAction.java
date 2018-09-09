package WSModel;

public class ResponseAction {
	
	private String result;
	
	private boolean Response;;
	
	private boolean err;
	
	private boolean plt;
	
	private String msgplt;

	
	public ResponseAction(String result, boolean response) {
		this.result = result;
		Response = response;
		this.err = false;
		this.plt = false;
		this.msgplt = "";
	}
		


	public ResponseAction(boolean err, boolean plt, String msgplt) {
		this.result = "";
		this.Response = false;
		this.err = err;
		this.plt = plt;
		this.msgplt = msgplt;
	}
	
	public ResponseAction(boolean err, boolean response,boolean plt, String msgplt) {
		this.result = "";
		this.Response = response;
		this.err = err;
		this.plt = plt;
		this.msgplt = msgplt;
	}
	
	public ResponseAction(String result, boolean response, boolean err, boolean plt, String msgplt) {
		this.result = result;
		Response = response;
		this.err = err;
		this.plt = plt;
		this.msgplt = msgplt;
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean getResponse() {
		return Response;
	}

	public void setResponse(boolean response) {
		Response = response;
	}

	public boolean getErr() {
		return err;
	}

	public void setErr(boolean err) {
		this.err = err;
	}

	public boolean getPlt() {
		return plt;
	}

	public void setPlt(boolean plt) {
		this.plt = plt;
	}

	public String getMsgplt() {
		return msgplt;
	}

	public void setMsgplt(String msgplt) {
		this.msgplt = msgplt;
	}


	@Override
	public String toString() {
		return "ResponseAction [result=" + result + ", Response=" + Response + ", err=" + err + ", plt=" + plt
				+ ", msgplt=" + msgplt + "]";
	}
	
	
	
	

}

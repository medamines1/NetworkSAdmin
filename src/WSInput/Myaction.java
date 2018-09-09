package WSInput;

public class Myaction {
	
	private String host;
	private String args;
	
	public Myaction() {}
	public Myaction(String host,String args) {
		this.host = host;
		this.args = args;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	} 
	
	
}

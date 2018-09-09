package WSModel;

public class MessageAction {

	private String name;
	private String Action;
	private String[] args;
	
	public MessageAction() {}
	public MessageAction(String name, String action, String[] args) {
		this.name = name;
		this.Action = action;
		this.args = args;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	
}

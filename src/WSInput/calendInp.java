package WSInput;

public class calendInp {
	private String d_Start;
	private String end;
	private String title;
	private String bg;
	
	
	public calendInp() {}
	public calendInp(String d_Start, String end, String title, String bg) {
		this.d_Start = d_Start;
		this.end = end;
		this.title = title;
		this.bg = bg;
	}

	public String getD_Start() {
		return d_Start;
	}

	public void setD_Start(String d_Start) {
		this.d_Start = d_Start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}
	
	

}

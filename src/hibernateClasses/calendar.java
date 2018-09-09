package hibernateClasses;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name="calendar")
public class calendar implements Serializable {
    

	@Id
	@GeneratedValue
	@Column(name="calendar_id")
	private long id;
    
	@Id
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "username"))
    private User user;
	
 	@Column
	private String title;
	
 	@Column
	private String d_start;
	
 	@Column
	private String bg ;//default '#3c8dbc',
 	
 	@Column(name="end")
	private String end;
    

    public calendar() {
    	
    }
    
    
    



	@Override
	public String toString() {
		return "calendar [id=" + id + ", user=" + user + ", title=" + title + ", d_start=" + d_start + ", bg=" + bg
				+ ", end=" + end + "]";
	}






	public calendar(String title, String d_start, String bg, String end) {
		super();
		this.title = title;
		this.d_start = d_start;
		this.bg = bg;
		this.end = end;
	}

	
	public calendar(long id,String title, String d_start, String bg) {
		super();
		this.id = id;
		this.title = title;
		this.d_start = d_start;
		this.bg = bg;
	}






	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getD_start() {
		return d_start;
	}

	public void setD_start(String d_start) {
		this.d_start = d_start;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}


	
    
    

}

package hibernateClasses;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name="mymsgs")
@IdClass(messages_key.class)
public class messages {
    

	@Id
	@GeneratedValue
	@Column(name="message_id")
	private long id;
    
	@Id
	@OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "username"))
    private User sender;
	
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "username"))
    private User reveiver;
	
    @Column(nullable=false)
    private String msg;
    
    @Column(name="seen")
    private boolean seen;
    
    @Column(name="placedin")
    @Check(constraints="placedin = 'Inbox' or placedin = 'Sent' or placedin = 'Drafts' or placedin = 'Junk' or placedin = 'Trash'")
    private String placedin; 
    
    
    
    @Column(name="subj")
    private String subj;
    
    @Column
    private Date created;
    
    
    @PrePersist
    protected void onCreate() {
      created = new Date();
    }

    public messages() {
    	
    }
    
	public messages(User sender, User reveiver, String msg, boolean seen, String placedin, String subj) {
		this.sender  = sender;
		this.reveiver = reveiver;
		this.msg = msg;
		this.seen = seen;
		this.placedin = placedin;
		this.subj = subj;
	}







	public User getReveiver() {
		return reveiver;
	}

	public void setReveiver(User reveiver) {
		this.reveiver = reveiver;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "messages [id=" + id + ", sender=" + sender + ", reveiver=" + reveiver + ", msg=" + msg + ", seen="
				+ seen + ", placedin=" + placedin + ", subj=" + subj + ", created=" + created + "]";
	}

	
    
    

}

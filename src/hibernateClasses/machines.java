package hibernateClasses;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Table(name="machines")
@Entity
public class machines {
	
	@Id
	@Column(name="machine_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="port")
	private String port;
	
	@Column(name="host")
	private String host;//15
	
	@Column(name="type")
	private String type;
	
	@Column(name="status")
	private String status;
	
	@Column(name="timestamp")
	private Date created;
	
	@PrePersist
	protected void onCreate() {
	  created = new Date();
	}
	
	public machines() {}
	
	public machines(String nom, String port, String host, String type) {
	
		this.nom = nom;
		this.port = port;
		this.host = host;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "machines [id=" + id + ", nom=" + nom + ", port=" + port + ", host=" + host + ", type=" + type
				+ ", status=" + status + ", created=" + created + "]";
	}
	
	
}

package hibernateClasses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	
	
	@GeneratedValue
	@Column(name="userid")
	private long id;
		
	  @Id
	  @Column(name = "username",unique=true)
	  private String username;
	
	  @Column(name = "PASSWORD", nullable = false)
	  private String password;
	
	  @Column(name = "ENABLED", nullable = false)
	  private boolean enabled;
	
	
	  @Column(name = "ROLE", nullable = false)
	  private String role;
	  
	  
	  
  public User(String username, String password, boolean enabled, String role) {
	
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

@OneToMany(cascade = CascadeType.ALL)
  private Set<Authorities> authorities = new HashSet<>();

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public Set<Authorities> getAuthorities() {
	return authorities;
}

public void setAuthorities(Set<Authorities> authorities) {
	this.authorities = authorities;
}
	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
  
}
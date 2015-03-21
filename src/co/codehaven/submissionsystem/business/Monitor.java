package co.codehaven.submissionsystem.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Monitor")
public class Monitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 496508191020484264L;
	@Id
	private String email;
	private String login;
	private int level;
	
	public Monitor(String login, int level) {
		super();
		this.login = login;
		this.email = login+"@cin.ufpe.br";
		this.level  = level;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}

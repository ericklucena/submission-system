package co.codehaven.submissionsystem.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Aluno")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6353954679220110790L;
	@Id
	private String email;
	private String login;
	
	public Student(String login) {
		super();
		this.login = login;
		this.email = login+"@cin.ufpe.br";
	} 

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}

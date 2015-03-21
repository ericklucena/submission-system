package co.codehaven.submissionsystem.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import co.codehaven.submissionsystem.datastore.DatastoreQuestion;


@SuppressWarnings("serial")
@Entity(name="Lista")
public class Question implements Serializable{
	
	
	@Id
	private String id;
	private String login;
	private String list;
	private String file;
	private double grade;
	private String justification;
	
	public Question(String login, String list, String file)
	{
		this.id = login+list;
		this.login = login;
		this.list = list;
		this.file = file; 
	}
	
	public String getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	
}

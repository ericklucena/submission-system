package co.codehaven.submissionsystem.facade;

import java.util.ArrayList;

import co.codehaven.submissionsystem.control.MonitorControl;
import co.codehaven.submissionsystem.control.QuestionControl;
import co.codehaven.submissionsystem.control.StatusControl;
import co.codehaven.submissionsystem.control.StudentControl;
import co.codehaven.submissionsystem.exceptions.MonitorAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.MonitorNotFoundException;
import co.codehaven.submissionsystem.exceptions.QuestionAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.QuestionNotFoundException;
import co.codehaven.submissionsystem.exceptions.StudentAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.StudentNotFoundException;
import co.codehaven.submissionsystem.business.Monitor;
import co.codehaven.submissionsystem.business.Question;
import co.codehaven.submissionsystem.business.Status;
import co.codehaven.submissionsystem.business.Student;

public class SubmissionSystem {
	
	private static SubmissionSystem instance;
	
	public static SubmissionSystem getInstance(){
		if(instance == null){
			instance = new SubmissionSystem();
		}
		
		return instance;
	}
	
	public void insertMonitor(Monitor monitor) throws MonitorAlreadyInsertedException{
		MonitorControl.getInstance().insert(monitor);
	}
	
	public Monitor searchMonitor(String email) throws MonitorNotFoundException{
		return MonitorControl.getInstance().search(email);
	}
	
	public void removeMonitor(String email) throws MonitorNotFoundException{
		MonitorControl.getInstance().remove(email);
	}
	
	public ArrayList<Monitor> getMonitors(){
		return MonitorControl.getInstance().getMonitors();
	}
	
	public boolean hasMonitor(String email){
		return MonitorControl.getInstance().has(email);
	}
	
	public void insertStudent(Student student) throws StudentAlreadyInsertedException{
		StudentControl.getInstance().insert(student);
	}
	
	public ArrayList<Student> getStudents(){
		return StudentControl.getInstance().getStudents();
	}
	
	public Student searchStudent(String email) throws StudentNotFoundException{
		return StudentControl.getInstance().search(email);
	}
	
	public void removeStudent(String email) throws StudentNotFoundException{
		StudentControl.getInstance().remove(email);
	}
	
	public boolean hasStudent(String email){
		return StudentControl.getInstance().has(email);
	}

	public void insertQuestion(Question question) throws QuestionAlreadyInsertedException{
		QuestionControl.getInstance().insert(question);
	}
	
	public void removeQuestion(String id) throws QuestionNotFoundException{
		QuestionControl.getInstance().remove(id);
	}
	
	public boolean hasQuestion(String id){
		return QuestionControl.getInstance().has(id);
	}

	public Question searchQuestion(String id) throws QuestionNotFoundException{
		return QuestionControl.getInstance().search(id);
	}
	
	public ArrayList<Question>  getLists(String login, int list) throws QuestionNotFoundException{
		return QuestionControl.getInstance().getLists(login, list);
	}
	
	public ArrayList<Question>  getAps(String login, int list) throws QuestionNotFoundException{
		return QuestionControl.getInstance().getLists(login, list);
	}
	
	public ArrayList<Question>  getLists(String login) throws QuestionNotFoundException{
		return QuestionControl.getInstance().getLists(login);
	}
		
	public void insertServerStatus(Status status){
		StatusControl.getInstance().insert(status);
	}
	
	public void removeServerStatus(String server){
		StatusControl.getInstance().remove(server);
	}
	
	public Status searchServerStatus(String server){
		return StatusControl.getInstance().search(server);
	}
	
	public boolean isRunning(String server){
		return StatusControl.getInstance().isRunning(server);
	}
	
	public String getTimes(String server){
		return StatusControl.getInstance().getTimes(server);
	}
	
}

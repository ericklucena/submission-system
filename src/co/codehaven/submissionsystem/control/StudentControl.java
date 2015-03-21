package co.codehaven.submissionsystem.control;

import java.util.ArrayList;

import co.codehaven.submissionsystem.datastore.DatastoreStudent;
import co.codehaven.submissionsystem.exceptions.StudentAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.StudentNotFoundException;
import co.codehaven.submissionsystem.business.Student;

public class StudentControl {

	private static StudentControl instance;
	
	public static StudentControl getInstance(){
		
		if(instance==null){
			instance = new StudentControl();
		}
		
		return instance;
		
	}	
	
	public void insert(Student student) throws StudentAlreadyInsertedException{	
		if(DatastoreStudent.getInstance().has(student.getEmail())){
			throw new StudentAlreadyInsertedException();
		}else{
			DatastoreStudent.getInstance().insert(student);
		}
	}
	
	public void remove(String email) throws StudentNotFoundException{
		if(DatastoreStudent.getInstance().has(email)){
			throw new StudentNotFoundException();
		}else{
			DatastoreStudent.getInstance().remove(email);
		}
	}
	
	public ArrayList<Student> getStudents(){
		return DatastoreStudent.getInstance().getStudents();
	}
	
	public Student search(String email) throws StudentNotFoundException{
		return DatastoreStudent.getInstance().search(email);
	}
	
	public boolean has(String login){
		return DatastoreStudent.getInstance().has(login);		
	}		
	
}

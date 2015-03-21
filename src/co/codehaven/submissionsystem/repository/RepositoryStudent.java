package co.codehaven.submissionsystem.repository;

import java.util.ArrayList;
import co.codehaven.submissionsystem.exceptions.StudentNotFoundException;
import co.codehaven.submissionsystem.business.Student;

public interface RepositoryStudent {
	

	public void insert (Student student);
	
	public void remove (String email);
	
	public Student search(String email) throws StudentNotFoundException;
	
	public boolean has(String email);
	
	public ArrayList<Student> getStudents();
	
}

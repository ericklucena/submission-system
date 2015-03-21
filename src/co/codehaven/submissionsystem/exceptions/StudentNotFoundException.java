package co.codehaven.submissionsystem.exceptions;

public class StudentNotFoundException extends Exception {

	public StudentNotFoundException() {
		super(ExceptionsMessages.STUDENT_NOT_FOUND);
	}
	
}

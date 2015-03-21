package co.codehaven.submissionsystem.exceptions;

public class StudentAlreadyInsertedException extends Exception {

	public StudentAlreadyInsertedException() {
		super(ExceptionsMessages.STUDENT_ALREADY_INSERTED);
	}
	
}

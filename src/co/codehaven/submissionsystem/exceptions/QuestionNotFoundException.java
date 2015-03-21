package co.codehaven.submissionsystem.exceptions;

public class QuestionNotFoundException extends Exception {
	
	public QuestionNotFoundException(){
		super(ExceptionsMessages.QUESTION_NOT_FOUND);
	}

}

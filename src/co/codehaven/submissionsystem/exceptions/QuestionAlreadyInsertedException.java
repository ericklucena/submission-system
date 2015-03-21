package co.codehaven.submissionsystem.exceptions;

public class QuestionAlreadyInsertedException extends Exception{
	
	public QuestionAlreadyInsertedException(){
		super(ExceptionsMessages.QUESTION_ALREADY_INSERTED);
	}

}

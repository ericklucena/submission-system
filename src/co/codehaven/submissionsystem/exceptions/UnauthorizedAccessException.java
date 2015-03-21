package co.codehaven.submissionsystem.exceptions;

public class UnauthorizedAccessException extends Exception {
	
	private String email;
	
	public UnauthorizedAccessException(String email) {
		super(ExceptionsMessages.UNAUTHORIZED_ACCESS);
		this.email = email;
	}
	
	public String toLog(){
		return this.email;
	}
	
}

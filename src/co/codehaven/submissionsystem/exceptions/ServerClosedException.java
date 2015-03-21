package co.codehaven.submissionsystem.exceptions;

public class ServerClosedException extends Exception {
	
	public ServerClosedException(){
		super(ExceptionsMessages.SERVER_CLOSED);
	}

}

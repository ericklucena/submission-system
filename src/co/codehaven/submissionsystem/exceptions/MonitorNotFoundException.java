package co.codehaven.submissionsystem.exceptions;

public class MonitorNotFoundException extends Exception{

	public MonitorNotFoundException(){
		
		super(ExceptionsMessages.MONITOR_NOT_FOUND);
		
	}
	
}

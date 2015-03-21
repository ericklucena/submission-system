package co.codehaven.submissionsystem.exceptions;

public class MonitorAlreadyInsertedException extends Exception{
	public MonitorAlreadyInsertedException(){
		super(ExceptionsMessages.MONITOR_ALREADY_INSERTED);
	}
}

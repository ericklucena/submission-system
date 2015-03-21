package co.codehaven.submissionsystem.control;

import co.codehaven.submissionsystem.datastore.DatastoreStatus;
import co.codehaven.submissionsystem.business.Status;

public class StatusControl {

	private static StatusControl instance;
	
	public static StatusControl getInstance(){
		
		if(instance==null){
			instance = new StatusControl();
		}
		
		return instance;
		
	}	
	
	public void insert(Status status){	
		
		DatastoreStatus.getInstance().insert(status);
		
	}
	
	public void remove(String server){
		DatastoreStatus.getInstance().remove(server);
		
	}
	
	public Status search(String server){
		return DatastoreStatus.getInstance().search(server);
	}
	
	public boolean isRunning(String server){
		return DatastoreStatus.getInstance().isRunning(server);		
	}
	
	public String getTimes(String server){
		return DatastoreStatus.getInstance().getTimes(server);
	}
	
}

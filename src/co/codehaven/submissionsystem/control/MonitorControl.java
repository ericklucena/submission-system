package co.codehaven.submissionsystem.control;

import java.util.ArrayList;

import co.codehaven.submissionsystem.datastore.DatastoreMonitor;
import co.codehaven.submissionsystem.exceptions.MonitorAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.MonitorNotFoundException;
import co.codehaven.submissionsystem.business.Monitor;

public class MonitorControl {

	private static MonitorControl instance;
	
	public static MonitorControl getInstance(){
		
		if(instance==null){
			instance = new MonitorControl();
		}
		
		return instance;
		
	}
	
	
	public void insert(Monitor monitor) throws MonitorAlreadyInsertedException{	
		if(DatastoreMonitor.getInstance().has(monitor.getLogin())){
			throw new MonitorAlreadyInsertedException();
		}else{
			DatastoreMonitor.getInstance().insert(monitor);
		}
	}
	
	public void remove(String email) throws MonitorNotFoundException{
		if(DatastoreMonitor.getInstance().has(email)){
			throw new MonitorNotFoundException();
		}else{
			DatastoreMonitor.getInstance().remove(email);
		}
	}
	
	public ArrayList<Monitor> getMonitors(){
		return DatastoreMonitor.getInstance().getMonitors();
	}
	
	public Monitor search(String email) throws MonitorNotFoundException{
		return DatastoreMonitor.getInstance().search(email);
	}
		
	public boolean has(String email){
		return DatastoreMonitor.getInstance().has(email);		
	}
	
	
	
}

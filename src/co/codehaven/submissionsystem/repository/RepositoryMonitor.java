package co.codehaven.submissionsystem.repository;

import java.util.ArrayList;

import co.codehaven.submissionsystem.exceptions.MonitorNotFoundException;
import co.codehaven.submissionsystem.business.Monitor;

public interface RepositoryMonitor {
	

	public void insert (Monitor monitor);
	
	public void remove (String login);
	
	public Monitor search(String login) throws MonitorNotFoundException;
	
	public boolean has(String login);
	
	public ArrayList<Monitor> getMonitors();
	
}

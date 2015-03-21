package co.codehaven.submissionsystem.datastore;

import java.util.ArrayList;

import co.codehaven.submissionsystem.repository.RepositoryMonitor;
import co.codehaven.submissionsystem.business.Monitor;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

import co.codehaven.submissionsystem.exceptions.MonitorNotFoundException;

public class DatastoreMonitor implements RepositoryMonitor{
	
	private static DatastoreMonitor instance;

	public synchronized static DatastoreMonitor getInstance() {
		if (instance == null) {
			instance = new DatastoreMonitor();
		}
		return instance;
	}	
	
	public void insert(Monitor aluno){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity toSave = new Entity("Monitor");
		
		toSave.setProperty("email", aluno.getEmail().toLowerCase());
		toSave.setProperty("login", aluno.getLogin().toLowerCase());
		toSave.setProperty("level", aluno.getLevel());
		remove(aluno.getLogin());
        datastore.put(toSave);
	}
	
	public void remove(String email){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Monitor").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			datastore.delete(entity.getKey());
		}
		
	}
	
	public Monitor search(String email) throws MonitorNotFoundException{
		Monitor result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Monitor").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = createMonitor(entity);
		}
		
		if(result == null){
			throw new MonitorNotFoundException();
		}else{
			return result;
		}
	}
	
	public ArrayList<Monitor> getMonitors(){
		ArrayList<Monitor> list = new ArrayList<Monitor>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.NOT_EQUAL, "");
		Query q = new Query("Monitor").setFilter(id);
		q.addSort("email");
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			list.add(createMonitor(entity));
		}
		
		return list;
	}
	
	
	
	public boolean has(String email){
		Monitor result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Monitor").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = createMonitor(entity);
		}
		
		return result!= null;
	}
	
	private Monitor createMonitor(Entity entity){
		return new Monitor((String)entity.getProperty("login"), Integer.parseInt(entity.getProperty("level").toString()));
	}
	
	
	
	
	
}

package co.codehaven.submissionsystem.datastore;

import java.util.Date;

import co.codehaven.submissionsystem.business.Status;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

public class DatastoreStatus {
	
	private static DatastoreStatus instance;
	
	public static DatastoreStatus getInstance(){
		
		if(instance==null){
			instance = new DatastoreStatus();
		}
		
		return instance;
		
	}	
	
	public void insert(Status status){
		remove(status.getServer());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity toSave = new Entity("Status");
		
		toSave.setProperty("server", status.getServer());
		toSave.setProperty("lista", status.getLista());
		toSave.setProperty("number", status.getNumber());
		toSave.setProperty("day", status.getDay());
		toSave.setProperty("month", status.getMonth());
		toSave.setProperty("year", status.getYear());
		toSave.setProperty("hour", status.getHour());
		toSave.setProperty("minute", status.getMinute());
		toSave.setProperty("endDay", status.getEndDay());
		toSave.setProperty("endMonth", status.getEndMonth());
		toSave.setProperty("endYear", status.getEndYear());
		toSave.setProperty("endHour", status.getEndHour());
		toSave.setProperty("endMinute", status.getEndMinute());
		

		
        datastore.put(toSave);
	}
	
	public void remove(String server){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("server", FilterOperator.EQUAL, server);
		Query q = new Query("Status").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			datastore.delete(entity.getKey());
		}
		
	}
	
	public Status search(String server){
		Status result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("server", FilterOperator.EQUAL, server);
		Query q = new Query("Status").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);

		for(Entity entity: pq.asIterable()){
			result = new Status(
						(String)entity.getProperty("server"),
						(String)entity.getProperty("lista"),
						Integer.parseInt(entity.getProperty("number").toString()),
						Integer.parseInt(entity.getProperty("day").toString()),
						Integer.parseInt(entity.getProperty("month").toString()),
						Integer.parseInt(entity.getProperty("year").toString()),
						Integer.parseInt(entity.getProperty("hour").toString()),
						Integer.parseInt(entity.getProperty("minute").toString()),
						Integer.parseInt(entity.getProperty("endDay").toString()),
						Integer.parseInt(entity.getProperty("endMonth").toString()),
						Integer.parseInt(entity.getProperty("endYear").toString()),
						Integer.parseInt(entity.getProperty("endHour").toString()),
						Integer.parseInt(entity.getProperty("endMinute").toString())					
					);
		}
		
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isRunning(String server){
		Status result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("server", FilterOperator.EQUAL, server);
		Query q = new Query("Status").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);

		for(Entity entity: pq.asIterable()){
			result = new Status(
						(String)entity.getProperty("server"),
						(String)entity.getProperty("lista"),
						Integer.parseInt(entity.getProperty("number").toString()),
						Integer.parseInt(entity.getProperty("day").toString()),
						Integer.parseInt(entity.getProperty("month").toString()),
						Integer.parseInt(entity.getProperty("year").toString()),
						Integer.parseInt(entity.getProperty("hour").toString()),
						Integer.parseInt(entity.getProperty("minute").toString()),
						Integer.parseInt(entity.getProperty("endDay").toString()),
						Integer.parseInt(entity.getProperty("endMonth").toString()),
						Integer.parseInt(entity.getProperty("endYear").toString()),
						Integer.parseInt(entity.getProperty("endHour").toString()),
						Integer.parseInt(entity.getProperty("endMinute").toString())					
					);
		}
		
		Date before = new Date();
		Date after = new Date();
		Date now = new Date(System.currentTimeMillis()-10800000);
		
		before.setYear(result.getYear()-1900);
		before.setMonth(result.getMonth()-1);
		before.setDate(result.getDay());
		before.setHours(result.getHour());
		before.setMinutes(result.getMinute());
		
		after.setYear(result.getEndYear()-1900);
		after.setMonth(result.getEndMonth()-1);
		after.setDate(result.getEndDay());
		after.setHours(result.getEndHour());
		after.setMinutes(result.getEndMinute());
		
		return before.before(now) && now.before(after);

	}
	
	@SuppressWarnings("deprecation")
	public String getTimes(String server){
		Status result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("server", FilterOperator.EQUAL, server);
		Query q = new Query("Status").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);

		for(Entity entity: pq.asIterable()){
			result = new Status(
						(String)entity.getProperty("server"),
						(String)entity.getProperty("lista"),
						Integer.parseInt(entity.getProperty("number").toString()),
						Integer.parseInt(entity.getProperty("day").toString()),
						Integer.parseInt(entity.getProperty("month").toString()),
						Integer.parseInt(entity.getProperty("year").toString()),
						Integer.parseInt(entity.getProperty("hour").toString()),
						Integer.parseInt(entity.getProperty("minute").toString()),
						Integer.parseInt(entity.getProperty("endDay").toString()),
						Integer.parseInt(entity.getProperty("endMonth").toString()),
						Integer.parseInt(entity.getProperty("endYear").toString()),
						Integer.parseInt(entity.getProperty("endHour").toString()),
						Integer.parseInt(entity.getProperty("endMinute").toString())					
					);
		}
		
		Date before = new Date();
		Date after = new Date();
		Date now = new Date(System.currentTimeMillis()-10800000);
		
		before.setYear(result.getYear()-1900);
		before.setMonth(result.getMonth()-1);
		before.setDate(result.getDay());
		before.setHours(result.getHour());
		before.setMinutes(result.getMinute());
		
		after.setYear(result.getEndYear()-1900);
		after.setMonth(result.getEndMonth()-1);
		after.setDate(result.getEndDay());
		after.setHours(result.getEndHour());
		after.setMinutes(result.getEndMinute());
		
		return before.toString()+" "+now.toString()+" "+after.toString();

	}
}

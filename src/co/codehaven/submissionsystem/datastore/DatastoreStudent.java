package co.codehaven.submissionsystem.datastore;

import java.util.ArrayList;

import co.codehaven.submissionsystem.repository.RepositoryStudent;
import co.codehaven.submissionsystem.business.Question;
import co.codehaven.submissionsystem.business.Student;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

import co.codehaven.submissionsystem.exceptions.StudentNotFoundException;

public class DatastoreStudent implements RepositoryStudent {
	
	private static DatastoreStudent instance;

	public synchronized static DatastoreStudent getInstance() {
		if (instance == null) {
			instance = new DatastoreStudent();
		}
		return instance;
	}	
	
	public void insert(Student student){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity toSave = new Entity("Student");
		
		toSave.setProperty("email", student.getEmail().toLowerCase());
		toSave.setProperty("login", student.getLogin().toLowerCase());
		remove(student.getLogin());
        datastore.put(toSave);
	}
	
	public void remove(String email){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Student").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			datastore.delete(entity.getKey());
		}
		
	}
	
	public Student search(String email) throws StudentNotFoundException{
		Student result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Student").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = createStudent(entity);
		}
		if(result==null){
			throw new StudentNotFoundException();
		}else{
			return result;
		}
	}
	
	
	public ArrayList<Student>  getStudents(){
		ArrayList<Student> list = new ArrayList<Student>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.NOT_EQUAL, "");
		Query q = new Query("Student").setFilter(id);
		q.addSort("email");
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			list.add(createStudent(entity));
		}
		
		return list;
	}
	
	
	
	public boolean has(String email){
		Student result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("email", FilterOperator.EQUAL, email.toLowerCase());
		Query q = new Query("Student").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = createStudent(entity);
		}
		
		return result!= null;
	}
	
	
	private Student createStudent(Entity entity){
		return new Student((String)entity.getProperty("login"));
	}
	
	
	
	
	
}

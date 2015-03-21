package co.codehaven.submissionsystem.datastore;

import java.util.ArrayList;

import co.codehaven.submissionsystem.repository.RepositoryQuestion;
import co.codehaven.submissionsystem.business.Question;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

import co.codehaven.submissionsystem.exceptions.QuestionNotFoundException;

public class DatastoreQuestion implements RepositoryQuestion{
	
	private static DatastoreQuestion instance;

	public synchronized static DatastoreQuestion getInstance() {
		if (instance == null) {
			instance = new DatastoreQuestion();
		}
		return instance;
	}	
	
	public void insert(Question question){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity toSave = new Entity("Question");
		
		toSave.setProperty("login", question.getLogin());
		toSave.setProperty("list", question.getList());
		toSave.setProperty("file", question.getFile());
		toSave.setProperty("id", question.getId());
		
        datastore.put(toSave);
	}
	
	public void remove(String id){

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter fId = new FilterPredicate("id", FilterOperator.EQUAL, id);
		Query q = new Query("Question").setFilter(fId);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			datastore.delete(entity.getKey());
		}
	}
	
	public Question search(String id) throws QuestionNotFoundException{
		Question result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter idFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
		
		Query q = new Query("Question").setFilter(idFilter);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file"));
		}
		
		if(result!= null){
			throw new QuestionNotFoundException();
		}else{
			return result;
		}
		
	}
	
	public Question search(String login, String list) throws QuestionNotFoundException{
		Question result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("id", FilterOperator.EQUAL, login+list);
	
		Query q = new Query("Question").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file"));
		}
		
		if(result == null){
			throw new QuestionNotFoundException();
		}else{
			return result;
		}
		
	}
	
	public boolean has(String login, String list){
		Question result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("id", FilterOperator.EQUAL, login+list);
	
		Query q = new Query("Question").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file"));
		}
		
		return result != null;
	}
	
	public boolean has(String id){
		Question result=null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter fId = new FilterPredicate("id", FilterOperator.EQUAL, id);
		
		Query q = new Query("Question").setFilter(fId);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity entity: pq.asIterable()){
			result = new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file"));
		}
		
		return result != null;
	}
	
	public ArrayList<Question> getLists(String login) throws QuestionNotFoundException{
		ArrayList<Question> result= new ArrayList<Question>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id = new FilterPredicate("login", FilterOperator.EQUAL, login.toLowerCase());

		Query q = new Query("Question").setFilter(id);
		q.addSort("list");
		PreparedQuery pq = datastore.prepare(q);
		
		int n=0;
		
		for(Entity entity: pq.asIterable()){
			result.add(new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file")));
			n++;
		}
		
		if(n==0){
			throw new QuestionNotFoundException();
		}else{
			return result;
		}
	}
	
	public ArrayList<Question>  getLists(String login, int list) throws QuestionNotFoundException{
		ArrayList<Question>  result= new ArrayList<Question>();
		String min = "L"+list+"Q1";
		String max = "L"+list+"Q9";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id1 = new FilterPredicate("list", FilterOperator.GREATER_THAN_OR_EQUAL, min);
		Filter id2 = new FilterPredicate("list", FilterOperator.LESS_THAN_OR_EQUAL, max);
		Filter id3 = new FilterPredicate("login", FilterOperator.EQUAL, login.toLowerCase());
		
		Filter id = CompositeFilterOperator.and(id1, id2);
		id = CompositeFilterOperator.and(id, id3);
	
		Query q = new Query("Question").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		int n=0;
		
		for(Entity entity: pq.asIterable()){
			result.add(new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file")));
			n++;
		}
		
		if(n==0){
			throw new QuestionNotFoundException();
		}else{
			return result;
		}
	}
	
	public ArrayList<Question>  getAps(String login, int ap) throws QuestionNotFoundException{
		ArrayList<Question>  result= new ArrayList<Question>();
		String min = "AP"+ap+"Q1";
		String max = "AP"+ap+"Q5";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id1 = new FilterPredicate("list", FilterOperator.GREATER_THAN_OR_EQUAL, min);
		Filter id2 = new FilterPredicate("list", FilterOperator.LESS_THAN_OR_EQUAL, max);
		Filter id3 = new FilterPredicate("login", FilterOperator.EQUAL, login.toLowerCase());
		
		Filter id = CompositeFilterOperator.and(id1, id2);
		id = CompositeFilterOperator.and(id, id3);
	
		Query q = new Query("Question").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);

		int n=0;
		
		for(Entity entity: pq.asIterable()){
			result.add(new Question((String)entity.getProperty("login"),(String)entity.getProperty("list"),(String)entity.getProperty("file")));
			n++;
		}
		
		if(n==0){
			throw new QuestionNotFoundException();
		}else{
			return result;
		}
	}
	
	public int getApQuantity(String login, int ap){
		String min = "AP"+ap+"Q1";
		String max = "AP"+ap+"Q5";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter id1 = new FilterPredicate("list", FilterOperator.GREATER_THAN_OR_EQUAL, min);
		Filter id2 = new FilterPredicate("list", FilterOperator.LESS_THAN_OR_EQUAL, max);
		Filter id3 = new FilterPredicate("login", FilterOperator.EQUAL, login.toLowerCase());
		
		Filter id = CompositeFilterOperator.and(id1, id2);
		id = CompositeFilterOperator.and(id, id3);
		
		Query q = new Query("Question").setFilter(id);
		PreparedQuery pq = datastore.prepare(q);
		
		int n=0;
		for(Entity entity: pq.asIterable()){
			n++;
		}
		
		return n;
	}
	
	
}

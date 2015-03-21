package co.codehaven.submissionsystem.control;

import java.util.ArrayList;
import co.codehaven.submissionsystem.datastore.DatastoreQuestion;
import co.codehaven.submissionsystem.exceptions.QuestionAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.QuestionNotFoundException;
import co.codehaven.submissionsystem.business.Question;

public class QuestionControl {

	private static QuestionControl instance;
	
	public static QuestionControl getInstance(){
		
		if(instance==null){
			instance = new QuestionControl();
		}
		
		return instance;
		
	}
	
	
	public void insert(Question question) throws QuestionAlreadyInsertedException{	
		DatastoreQuestion.getInstance().insert(question);
	}
	
	public void remove(String id) throws QuestionNotFoundException{
		if(!DatastoreQuestion.getInstance().has(id)){
			throw new QuestionNotFoundException();
		}else{
			DatastoreQuestion.getInstance().remove(id);
		}
	}
	
	public Question search(String login, String lista) throws QuestionNotFoundException{
		return DatastoreQuestion.getInstance().search(login, lista);
	}

	public Question search(String id) throws QuestionNotFoundException{
		return DatastoreQuestion.getInstance().search(id);
	}
		
	public boolean has(String id){
		return DatastoreQuestion.getInstance().has(id);		
	}
	
	public ArrayList<Question>  getLists(String login) throws QuestionNotFoundException{
		return DatastoreQuestion.getInstance().getLists(login);
	}
	
	public ArrayList<Question>  getLists(String login, int list) throws QuestionNotFoundException{
		return DatastoreQuestion.getInstance().getLists(login, list);
	}
	
	public ArrayList<Question>  getAps(String login, int ap) throws QuestionNotFoundException{
		return DatastoreQuestion.getInstance().getAps(login, ap);
	}
	
	
}

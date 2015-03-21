package co.codehaven.submissionsystem.repository;

import java.util.ArrayList;

import co.codehaven.submissionsystem.exceptions.QuestionNotFoundException;
import co.codehaven.submissionsystem.business.Question;

public interface RepositoryQuestion {
	

	public void insert (Question question);
	
	public void remove (String id);
	
	public Question search(String login, String list) throws QuestionNotFoundException;

	public Question search(String id) throws QuestionNotFoundException;
	
	public boolean has(String login, String list);

	public boolean has(String id);
	
	public ArrayList<Question> getLists(String login) throws QuestionNotFoundException;
	
	public ArrayList<Question>  getLists(String login, int list) throws QuestionNotFoundException;
	
	public ArrayList<Question>  getAps(String login, int ap) throws QuestionNotFoundException;
	
}

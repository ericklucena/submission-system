package co.codehaven.submissionsystem.submission;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.codehaven.submissionsystem.util.SystemConstants;
import co.codehaven.submissionsystem.control.StudentControl;
import co.codehaven.submissionsystem.business.Monitor;
import co.codehaven.submissionsystem.business.Question;
import co.codehaven.submissionsystem.business.Student;



import co.codehaven.submissionsystem.datastore.DatastoreQuestion;
import co.codehaven.submissionsystem.datastore.DatastoreStudent;
import co.codehaven.submissionsystem.exceptions.MonitorAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.StudentAlreadyInsertedException;
import co.codehaven.submissionsystem.facade.submissionSystem;

public class SignUp extends HttpServlet {


    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	
    	String login = req.getParameter("login");
    	boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
    	
    	if(admin){
    		try {
				SubmissionSystem.getInstance().insertMonitor(new Monitor(login, 1));
			} catch (MonitorAlreadyInsertedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		try {
				SubmissionSystem.getInstance().insertStudent(new Student(login));
			} catch (StudentAlreadyInsertedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}      
    
    	res.sendRedirect(SystemConstants.SIGNUP_SUCCESS_URL);
    }
}
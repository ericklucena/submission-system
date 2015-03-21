package co.codehaven.submissionsystem.submission;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.ProjectComponent;

import co.codehaven.submissionsystem.util.SystemConstants;
import co.codehaven.submissionsystem.business.Question;
import co.codehaven.submissionsystem.business.Student;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.org.apache.bcel.internal.Constants;

import co.codehaven.submissionsystem.control.QuestionControl;
import co.codehaven.submissionsystem.datastore.DatastoreQuestion;
import co.codehaven.submissionsystem.exceptions.QuestionAlreadyInsertedException;
import co.codehaven.submissionsystem.exceptions.QuestionNotFoundException;
import co.codehaven.submissionsystem.exceptions.ServerClosedException;
import co.codehaven.submissionsystem.facade.submissionSystem;

public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();


    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	
    	if(SubmissionSystem.getInstance().isRunning(SystemConstants.SERVER)){
    		UserService userService = UserServiceFactory.getUserService();
    		User user = userService.getCurrentUser();
    		
    		Student student = (Student) req.getSession().getAttribute("student");

    		Question question = null;
    		String file;
    		
            @SuppressWarnings("deprecation")
    		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
            
            try {
    			question = QuestionControl.getInstance().search(student.getLogin(), req.getParameter("lista"));
    			System.out.println("AWAY"+question);
    		} catch (QuestionNotFoundException e) {
    			//Do nothing
    		}
            
            if(question != null){
            	file = question.getFile();
            	blobstoreService.delete(new BlobKey(file));
            	try {
    				SubmissionSystem.getInstance().removeQuestion(question.getId());
    			} catch (QuestionNotFoundException e) {
    				//Do nothing
    			}
            } 
            
            file  = blobs.get("myFile").getKeyString();
      
            if (file == null) {
                res.sendRedirect(SystemConstants.UPLOAD_FAIL_URL);
            } else {
            	try {
            		question = new Question(student.getLogin(), req.getParameter("lista"), file);
    				SubmissionSystem.getInstance().insertQuestion(question);
    			} catch (QuestionAlreadyInsertedException e) {
    				e.printStackTrace();
    			}
                res.sendRedirect(SystemConstants.UPLOAD_SUCCESS_URL);
            }
    	}else{
    		res.sendRedirect(SystemConstants.SERVER_CLOSED_URL);
    	}
    	
    	
    }
}
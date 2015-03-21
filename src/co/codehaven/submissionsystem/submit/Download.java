package co.codehaven.submissionsystem.submission;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.codehaven.submissionsystem.business.Question;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import co.codehaven.submissionsystem.control.QuestionControl;

import co.codehaven.submissionsystem.datastore.DatastoreQuestion;
import co.codehaven.submissionsystem.exceptions.*;

public class Download extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();


    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	
    	UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String file;
	       
    	try {
			file = QuestionControl.getInstance().search(user.getEmail(), req.getParameter("list")).getFile();
		} catch (QuestionNotFoundException e) {
			throw new ServletException(e);
		}
    	
        res.sendRedirect("/serve?blob-key=" + file);
    }
}
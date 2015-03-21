package co.codehaven.submissionsystem.submission;

import java.io.IOException;

import javax.servlet.http.*;

import co.codehaven.submissionsystem.util.SystemConstants;
import co.codehaven.submissionsystem.business.Status;
import co.codehaven.submissionsystem.datastore.DatastoreStatus;
import co.codehaven.submissionsystem.facade.submissionSystem;

@SuppressWarnings("serial")
public class runApServerServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Status status = new Status(
				SystemConstants.APSERVER, 
				req.getParameter("lista"), 
				Integer.parseInt(req.getParameter("number")), 
				Integer.parseInt(req.getParameter("day")), 
				Integer.parseInt(req.getParameter("month")),
				Integer.parseInt(req.getParameter("year")), 
				Integer.parseInt(req.getParameter("hour")),
				Integer.parseInt(req.getParameter("minute")),
				Integer.parseInt(req.getParameter("endDay")),
				Integer.parseInt(req.getParameter("endMonth")),
				Integer.parseInt(req.getParameter("endYear")),
				Integer.parseInt(req.getParameter("endHour")),
				Integer.parseInt(req.getParameter("endMinute"))
						);

		SubmissionSystem.getInstance().insertServerStatus(status);
		resp.sendRedirect(SystemConstants.RUN_AP_SERVER_URL);
	}
}

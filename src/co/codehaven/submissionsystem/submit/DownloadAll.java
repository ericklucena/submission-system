package co.codehaven.submissionsystem.submission;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.codehaven.submissionsystem.util.SystemConstants;
import co.codehaven.submissionsystem.business.Question;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import co.codehaven.submissionsystem.facade.submissionSystem;

public class DownloadAll extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		int list = Integer.parseInt(request.getParameter("list"));
		BlobInfoFactory blobInfoFactory = new BlobInfoFactory(DatastoreServiceFactory.getDatastoreService());

		try {
			ArrayList <Question> questions = SubmissionSystem.getInstance().getLists(login, list);

			response.setContentType("application/zip");
			response.setHeader("content-disposition", "attachment;filename="+ login+"L"+list+".zip");  

			ServletOutputStream outputStream = response.getOutputStream();  
			ZipOutputStream zip = new ZipOutputStream(outputStream);

			for (Question q : questions) {
				
				BlobKey blobKey = new BlobKey(q.getFile());
				String filename = q.getId()+SystemConstants.DOWNLOAD_EXTENSION;

				zip.putNextEntry(new ZipEntry(filename));

				BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);

				byte[] b = blobstoreService.fetchData(blobKey, 0, blobInfo.getSize()-1);  
				zip.write(b, 0, b.length);
				 
				zip.flush();  
			}

			zip.close();  
			outputStream.flush();  
			
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
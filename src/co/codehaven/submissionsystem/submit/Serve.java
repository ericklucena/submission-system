package co.codehaven.submissionsystem.submission;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.codehaven.submissionsystem.util.SystemConstants;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class Serve extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
		String filename = req.getParameter("file-name")+SystemConstants.DOWNLOAD_EXTENSION;
		
		BlobInfoFactory blobInfoFactory = new BlobInfoFactory(DatastoreServiceFactory.getDatastoreService());
		BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
		res.setContentLength(new Long(blobInfo.getSize()).intValue());
		res.setHeader("content-type", blobInfo.getContentType());
		res.setHeader("content-disposition", "attachment;filename=" + filename);


		blobstoreService.serve(blobKey, res);
	}
}
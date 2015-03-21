package co.codehaven.submissionsystem.util;

public interface SystemConstants {
	
	public String APSERVER = "apServerStatus";
	public String SERVER = "serverStatus";
	public String ALUNO = "Aluno";
	
	public String RUN_SERVER_URL = "/monitorRunServer.jsp";
	public String RUN_AP_SERVER_URL = "/monitorRunApServer.jsp";

	public String SIGNUP_SUCCESS_URL = "/monitorRegisterUser.jsp?cadastrado=true";
	
	public String UPLOAD_SUCCESS_URL = "/studentUploadFile.jsp?uploaded=true";
	public String UPLOAD_FAIL_URL = "/studentUploadFile.jsp?notUploaded=true";

	public String UPLOAD_AP_SUCCESS_URL = "/studentUploadApFile.jsp?uploaded=true";
	public String UPLOAD_AP_FAIL_URL = "/studentUploadApFile.jsp?notUploaded=true";
	
	public String SERVER_CLOSED_URL = "/studentUploads.jsp?serverClosed=true";
	
	public String DOWNLOAD_EXTENSION = ".c";
	
	public String SYSTEM_TITLE = "SubmissionSystem";
	public String PAGE_TITLE = "SubmissionSystem";
	public String PAGE_DESCRIPTION = "";
	public String PAGE_KEYWORDS = "";	
	public String LOGO_IMAGE = "img\\E.png";
	public String LOGO_TEXT = "Erick Lucena";
	
	
}

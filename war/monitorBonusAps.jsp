<%@page import="co.codehaven.submissionsystem.business.Question"%>
<%@page import="co.codehaven.submissionsystem.business.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.codehaven.submissionsystem.util.SystemConstants"%>
<%@page import="co.codehaven.submissionsystem.exceptions.UnauthorizedAccessException"%>
<%@page import="co.codehaven.submissionsystem.business.Monitor"%>
<%@page import="co.codehaven.submissionsystem.facade.submissionSystem"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page errorPage="exception.jsp" %>

<%
SubmissionSystem submissionSystem = SubmissionSystem.getInstance();
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

Monitor monitor = (Monitor) session.getAttribute("monitor");

if(monitor == null){
	throw new UnauthorizedAccessException(user.getEmail());
}



SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
fmt.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
Date d;

%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><%=SystemConstants.PAGE_TITLE%></title>
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<!-- JavaScript plugins (requires jQuery) -->
	<script src="js/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/pure-min.css">
	<link rel="stylesheet" href="css/baby-blue.css">
	<link rel="stylesheet" href="css/dev.css">
	<script src="js/rel.js"></script>

    <script src="http://use.typekit.net/ajf8ggy.js"></script>
    <script>try { Typekit.load(); } catch (e) {}</script>
</head>

<body>
    <div class="pure-g-r" id="layout">
        <a href="#menu" id="menuLink" class="pure-menu-link">
            <img src="img/navicon-png2x.png" width="20" alt="Menu toggle"></a>

        <div class="pure-g-r">
            <div class="pure-u" id="menu">
                <div class="pure-menu pure-menu-open">
                    <a class="pure-menu-heading"  href="http://www.codehaven.co">
                        <img src="img/codeHaven.png"/>
                    </a>

                    <ul>
                        <li class=" pure-menu-selected">
                            <a href="monitorHome.jsp">Home</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorRunServer.jsp">Servidor de listas</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorRunApServer.jsp">Servidor de AP's</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorDownloadLists.jsp">Download Listas</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorRegisterUser.jsp">Cadastrar Usuário</a>
                        </li>
                        <li class=" menu-item-divided">
                            <a href="#"></a>
                        </li>
						<li class=" pure-menu">
                            <a href="<%=userService.createLogoutURL("/index.jsp")%>">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="pure-u-1" id="main">

            <div class="hero">
                <div class="pure-u-2-3">
                    <div class="l-box">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Home</h3>
                            </div>
                            <div class="panel-body">
	                            <%
								ArrayList <Student> students = SubmissionSystem.getInstance().getStudents();
									
								for(Student student : students){
									int k=0;
									for(int i=1;i<=13;i++){
										ArrayList<Question> questions = SubmissionSystem.getInstance().getAps(student.getLogin(), i);
										if (questions.size()>0){
											k++;
										}
									}
									if(k>8){
									%>
										<%=student.getLogin() %><br/>	
									<%
									}
								}
								%>
                            </div>
                            </div>               
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <script type="text/javascript">
		$("#logado").delay(0).fadeIn(2000);
	</script>

    <script src="js/rainbow-min.js"></script>
</body>
</html>
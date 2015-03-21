<%@page import="co.codehaven.submissionsystem.exceptions.UnauthorizedAccessException"%>
<%@page import="co.codehaven.submissionsystem.business.Monitor"%>
<%@page import="co.codehaven.submissionsystem.util.SystemConstants"%>
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
                            <a href="#">Home</a>
                        </li>

                        <li class=" menu-item-divided">
                            <a href="#"></a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>

        <div class="pure-u-1" id="main">

            <div class="hero">
                <div class="pure-u-1-3">
                    <div class="l-box">
                        <%
						if(user==null){
						%>
						<div class="alert alert-info">
						<a href="<%=userService.createLoginURL("/index.jsp")%>">Clique aqui para logar no sistema de submissão.</a>
						</div>
						<%
						}else{
							String email = user.getEmail().toLowerCase();
							if(SubmissionSystem.getInstance().hasMonitor(email)){
								session.setAttribute("monitor", SubmissionSystem.getInstance().searchMonitor(email));
								getServletConfig().getServletContext().getRequestDispatcher("/monitorHome.jsp").forward(request,response);
							}else if(SubmissionSystem.getInstance().hasStudent(email)){
								session.setAttribute("student", SubmissionSystem.getInstance().searchStudent(email));
								getServletConfig().getServletContext().getRequestDispatcher("/studentHome.jsp").forward(request,response);
							}else{
								%>
								<div class="alert alert-danger">Você precisa estar cadastrado para utilizar esse sistema.</div>
								<%
							}
						}
						%>	
                    </div>
                </div>
            </div>
        </div>

    </div>
    <script src="js/rainbow-min.js"></script>
</body>
</html>
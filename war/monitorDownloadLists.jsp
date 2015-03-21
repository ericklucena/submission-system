<%@page import="co.codehaven.submissionsystem.exceptions.QuestionNotFoundException"%>
<%@page import="co.codehaven.submissionsystem.business.Student"%>
<%@page import="co.codehaven.submissionsystem.business.Question"%>
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
    <script type="text/javascript">
	<!--
	function submit(formulario, valor) { 
		if(valor != '0'){
			formulario.submission();
		}   	
	} 
	-->
	</script>
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
                        <li class=" pure-menu">
                            <a href="monitorHome.jsp">Home</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorRunServer.jsp">Servidor de listas</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="monitorRunApServer.jsp">Servidor de AP's</a>
                        </li>
                        <li class=" pure-menu-selected">
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
                <div class="pure-u-2-3">
                    <div class="l-box">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Download de listas</h3>
                            </div>
                            <div class="panel-body">
                            	<div style="text-align: center;">
	                            <form action="monitorDownloadLists.jsp" name="escolha" id="escolha">
									<select class="form-control" name="lista" id="lista" onchange="submit(this.form, this.form.lista.value)">
										<option value="0">Escolha uma lista...</option>
										<%
										for(int i=1;i<=6;i++)
										{
										%>
											<option value="<%=i%>">Lista <%=i%></option>
										<%
										}
										%>
									</select>
								</form>
								</div>
								<%
								String listaEscolhida = request.getParameter("lista");
								int listaInt = 0;
								
								if(listaEscolhida != null){
									listaInt = Integer.parseInt(listaEscolhida);
								}
								if(listaInt>0){
								%>
								<br>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>
												Aluno
											</th>
											<th>
												Quest&otilde;es
											</th>
										</tr>
									</thead>
									<tbody>
									<%
									ArrayList<Student> students = SubmissionSystem.getInstance().getStudents();
										
									for(Student aluno: students){
											
									%>
									<tr>
										<td><%=aluno.getLogin()%><td>
										<%
										try{
											ArrayList<Question> questions = SubmissionSystem.getInstance().getLists(aluno.getLogin(), listaInt);
											for(Question lista: questions){
												%>
													<a href="/serve?blob-key=<%=lista.getFile()%>&file-name=<%=lista.getId()%>"><%=lista.getList()%></a><br/><br/>
												<%
											}
											%>
											<a href="/downloadAll?login=<%=aluno.getLogin()%>&list=<%=listaInt%>">zip</a><br/><br/>
											<%
												
										}catch(QuestionNotFoundException e){
											%>
												<p>Não submeteu.</p>
											<%
										}
										%>
											
										</td>
									</tr>
									<%
										}
									%>
									</tbody>
								</table>
								<%} %>
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
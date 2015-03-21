<%@page import="co.codehaven.submissionsystem.business.Status"%>
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
<%@page errorPage="exception.jsp"%>

<%
SubmissionSystem submissionSystem = SubmissionSystem.getInstance();
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

Monitor monitor = (Monitor) session.getAttribute("monitor");

if(monitor == null){
	throw new UnauthorizedAccessException(user.getEmail());
}


Status status = SubmissionSystem.getInstance().searchServerStatus(SystemConstants.SERVER);

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
                        <li class=" pure-menu">
                            <a href="monitorHome.jsp">Home</a>
                        </li>
                        <li class=" pure-menu-selected">
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
               <div class="pure-u-2-3">
                   <div class="l-box">
                       <div class="panel panel-default">
                           <div class="panel-heading">
                               <h3 class="panel-title">Servidor de listas</h3>
                           </div>
                           <div class="panel-body">
                            <div style="display:none;" class="alert alert-info" id="logado">
                                Configuração atual: <%=status.getConfiguration()%>
                            </div>
                            <form name="runServer" id="runServer" action="/runServer" method="post">
                            	<table class="table">
                            		<thead>
                            			<tr>
                            				<th>
                            					Lista
                            				</th>
                            				<th>
                            					Questões
                            				</th>
                            			</tr>
                            		</thead>
                            		<tbody>
                            			<tr>
                            				<td>
                            					<select name="lista" id="lista" class="form-control">
												<%
												for(int i=1;i<=6;i++)
												{
												%>
													<option value="L<%=i%>">L<%=i%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="number" id="number" class="form-control">
												<%
												for(int i=1;i<=10;i++)
												{
												%>
													<option value="<%=i%>"><%=i%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            			</tr>
                            		</tbody>
                            	</table>
								<table class="table">
                            		<thead>
                            			<tr>
                            				<th>
                            					
                            				</th>
                            				<th>
                            					Dia
                            				</th>
                            				<th>
                            					Mês
                            				</th>
                            				<th>
                            					Ano
                            				</th>
                            				<th>
                            					Hora
                            				</th>
                            				<th>
                            					Minutos
                            				</th>
                            			</tr>
                            		</thead>
                            		<tbody>
                            			<tr>
                            				<td>
                            					Início
                            				</td>
                            				<td>
                            					<select name="day" id="day" class="form-control">
												<%
												for(int i=1;i<=31;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="month" id="month" class="form-control">
												<%
												for(int i=1;i<=12;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="year" id="year" class="form-control">
												<%
												for(int i=2013;i<=2030;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="hour" id="hour" class="form-control">
												<%
												for(int i=0;i<=23;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="minute" id="minute" class="form-control">
												<%
												for(int i=0;i<=59;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            			</tr>
                            			<tr>
                            				<td>
                            					Fim
                            				</td>
                            				<td>
                            					<select name="endDay" id="endDay" class="form-control">
												<%
												for(int i=1;i<=31;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="endMonth" id="endMonth" class="form-control">
												<%
												for(int i=1;i<=12;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="endYear" id="endYear" class="form-control">
												<%
												for(int i=2013;i<=2030;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="endHour" id="endHour" class="form-control">
												<%
												for(int i=0;i<=23;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            				<td>
                            					<select name="endMinute" id="endMinute" class="form-control">
												<%
												for(int i=0;i<=59;i++)
												{
												%>
													<option value="<%=i%>"><%=String.format("%02d", i)%></option>
												<%
												}
												%>
												</select>
                            				</td>
                            			</tr>
                            		</tbody>
                            	</table>

								<div class="form-group">
									<input class="btn btn-primary" type="submit" name="submit" value="Configurar">
								</div>
							</form>
                            
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
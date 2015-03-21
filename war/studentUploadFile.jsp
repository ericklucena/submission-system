<%@page import="co.codehaven.submissionsystem.business.Status"%>
<%@page import="co.codehaven.submissionsystem.business.Student"%>
<%@page import="co.codehaven.submissionsystem.util.SystemConstants"%>
<%@page import="co.codehaven.submissionsystem.exceptions.UnauthorizedAccessException"%>
<%@page import="co.codehaven.submissionsystem.facade.submissionSystem"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@page errorPage="exception.jsp" %>

<%
SubmissionSystem submissionSystem = SubmissionSystem.getInstance();
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

Student student = (Student) session.getAttribute("student");

if(student == null){
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
	function comprova_extensao(formulario, arquivo) { 
		   extensoes_permitidas = new Array('<%=SystemConstants.DOWNLOAD_EXTENSION%>'); 
		   meuerro = ""; 
		   if (!arquivo) { 
		      //Se não tenho arquivo, é porque não se selecionou um arquivo no formulário. 
		      	meuerro = "Não foi selecionado nenhum arquivo"; 
		   }else{ 
		      //recupero a extensão deste nome de arquivo 
		      extensao = (arquivo.substring(arquivo.lastIndexOf("."))).toLowerCase(); 
		      //alert (extensao); 
		      //comprovo se a extensão está entre as permitidas 
		      permitida = false; 
		      for (var i = 0; i < extensoes_permitidas.length; i++) { 
		         if (extensoes_permitidas[i] == extensao) { 
		         permitida = true; 
		         break; 
		         } 
		      } 
		      if (!permitida) { 
		         meuerro = "Cheque a extensão do arquivo! \nSó podem ser submetidos arquivos com a extensão: " + extensoes_permitidas.join(); 
		      	}else{ 
		         formulario.submission(); 
		         return 1; 
		      	} 
		   } 
		   alert (meuerro); 
		   return 0; 
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
                            <a href="studentHome.jsp">Home</a>
                        </li>
                        <li class=" pure-menu-selected">
                            <a href="studentUploadFile.jsp">Submeter Lista</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="studentUploadApFile.jsp">Submeter AP</a>
                        </li>
                        <li class=" pure-menu">
                            <a href="studentUploads.jsp">Visualizar Arquivos</a>
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
			<div class="pure-g-r">
			<div class="pure-u-2-3">
				<div class="l-box">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Submissão de lista</h3>
						</div>
						<div class="panel-body">
							<div class="l-box">
							<%
								if(SubmissionSystem.getInstance().isRunning(SystemConstants.SERVER)){
							%>

								<form class="form-horizontal" action="<%=blobstoreService.createUploadUrl("/upload")%>" method="post" enctype="multipart/form-data">
									<div class="form-group">
										<label for="lista" class="col-sm-2 control-label">Questão</label>
										<div class="col-sm-4">
											<select class="form-control" name="lista" id="lista">
											<%
												Status status = SubmissionSystem.getInstance().searchServerStatus(SystemConstants.SERVER);
												for(int i = 1;i<=status.getNumber();i++){
											%>
												<option value="<%=status.getLista() %>Q<%=i%>">Q<%=i%></option>
											<%
												}
											%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="file" class="col-sm-2 control-label">Arquivo</label>
										<div class="col-sm-8">
											<input class="form-control" type="file" name="myFile">
										</div>
									</div>
									<div class="form-group">
										<label for="submit" class="col-sm-2 control-label"></label>
										<div class="col-sm-8">
											<input class="btn btn-primary" type="button" value="Submeter" onclick="comprova_extensao(this.form, this.form.myFile.value)">
										</div>
									</div>
									
								</form>							
							<%
							}else{
							%>
								<div class="alert alert-info">O sistema de submissão não está ativo no momento.</div>							
							<%
							} 
							%>
							</div>
						</div>
					</div>               
				</div>
			</div>
			<div class="pure-u-1-3" id="information" style="display:none;">
				<div class="l-box">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Informações</h3>
						</div>
						<div class="panel-body">
							<div class="alert alert-danger" id="notUploaded" style="display:none;">Houve um problema com a sua submissão. Por favor, tente novamente.</div>
							<div class="alert alert-success" id="uploaded" style="display:none;">Sua questão foi submetida com sucesso</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>

    <script type="text/javascript">
    <%
	if(request.getParameter("notUploaded")!=null){
	%>
	$("#information").delay(0).fadeIn(1000);
	$("#notUploaded").delay(0).fadeIn(1000);
	$("#notUploaded").delay(10000).fadeOut(1000);
	$("#information").delay(11000).fadeOut(1000);
	<%
	}
	%>
	<%
	if(request.getParameter("uploaded")!=null){
	%>
	$("#information").delay(0).fadeIn(1000);
	$("#uploaded").delay(0).fadeIn(1000);
	$("#uploaded").delay(10000).fadeOut(1000);
	$("#information").delay(11000).fadeOut(1000);
	<%
	}
	%>
	</script>

    <script src="js/rainbow-min.js"></script>
</body>
</html>
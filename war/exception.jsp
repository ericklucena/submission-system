<%@page import="co.codehaven.submissionsystem.exceptions.RedirectException"%>
<%@page import="co.codehaven.submissionsystem.util.SystemConstants"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" import="java.io.*" %>

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
                            <a href="home.jsp">Home</a>
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
                <div class="pure-u-2-3">
                    <div class="l-box">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Aconteceu um erro</h3>
                            </div>
                            <div class="panel-body">
	                            <div class="alert alert-danger">
	                            	<%
	                            	if(exception.getMessage().equals("null")){
	                            	%>
	                            		Koalas treinados estão se direcionando aos nossos servidores para tentar corrigir este erro.
	                            	<%
	                            	}else{
	                            	%>
	                                <%=exception.getMessage()%>
	                                <%
	                            	}
	                                %>
	                            </div>
                            	<%
                            		
                            		if(exception instanceof RedirectException){
                            	%>
	                            <form action="<%=((RedirectException)exception).getReturnUrl()%>">
	                                <button type="submit" class="btn btn-default">Voltar</button>
	                            </form>
	                            <%}else{ %>
	                            <form action="index.jsp">
	                                <button type="submit" class="btn btn-default">Voltar</button>
	                            </form>
	                            <%} %>
                            </div>                
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script src="js/rainbow-min.js"></script>
</body>
</html>
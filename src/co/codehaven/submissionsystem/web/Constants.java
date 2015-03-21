package co.codehaven.submissionsystem.web;

public interface Constants {
	
	String MENU_ADMIN = "<ul>"+
						"<li><a href=\"#\" class=\"current\">Central de Acesso</a></li>"+
						"<%if(user != null){"+
						"if(DatastoreUser.isAdmin(user.getEmail())){%>"+
						"	<li><a href=\"/runServer.jsp\">status</a></li>"+
						"<%}%>"+
						"<li><a href=\"<%=userService.createLogoutURL(\"/index.jsp\")%>\">Logout</a></li>"+
						"<%}else{%>"+
						"<li><a href=\"<%=userService.createLoginURL(\"/index.jsp\")%>\">Login</a></li>"+
						"<%} %>"+
						"</ul>";



}

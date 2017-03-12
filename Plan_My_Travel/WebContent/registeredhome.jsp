<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String username=(String)request.getAttribute("username") ;
session.setAttribute("username", username);
%>

<form  method="post" action="BookServlet">
<input type="hidden" name="session" value="<%=username %>">
<button name = "action" value = "mybookingdetails">Booked flights</button>
<button name = "action" value = "reschedulebooking">Reschedule Booked flights</button>
</form>


<input type="hidden" name="login" value="<%=username %>">
<a href="registerusersearch.jsp"><button>Search</button></a>

</body>
</html>

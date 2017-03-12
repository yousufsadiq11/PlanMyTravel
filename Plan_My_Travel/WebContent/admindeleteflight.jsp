<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="AdminDeleteServlet">
Flight Number:
<input type="text" name="flight_number">

Date:
<input type="text" name="date_of_arrival">

Arrival Time:
<input type="text" name="arrival_time">
<button type="submit" name="action" value="delete">Delete</button>
</form>
</body>
</html>
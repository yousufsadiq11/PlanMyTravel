<%@ page import="java.sql.*" %>
<%@page import="java.util.*"%>
<%@ page import="com.uncc.planmytravel.servlet.flight_details" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
button.accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
}

button.accordion.active, button.accordion:hover {
    background-color: #ddd;
}

button.accordion:after {
    content: '\02795';
    font-size: 13px;
    color: #777;
    float: right;
    margin-left: 5px;
}

button.accordion.active:after {
    content: "\2796";
}

div.panel {
    padding: 0 18px;
    background-color: white;
    max-height: 0;
    overflow: hidden;
    transition: 0.6s ease-in-out;
    opacity: 0;
}

div.panel.show {
    opacity: 1;
    max-height: 500px;
}
</style>
</head>
<body>
<%=
request.getAttribute("session")%>


	<%
		ArrayList<flight_details> boookedflights=(ArrayList<flight_details>)request.getAttribute("boookedflights");
		request.setAttribute("boookedflights", boookedflights);
		
		for(flight_details f1:boookedflights){%>
			<h1><b>Booked Flights: </b></h1>
		
	<%break;} %>
	<% 
		for(flight_details f:boookedflights){
		

		 %>
	
<button class="accordion" name="x" value="<%=f.getFlight_number() %>"><%=f.getFlight_name() %></button>
<div class="panel">
	<p>Source: <%=f.getSource() %>     Destination: <%=f.getDestination() %></p>
 	<p>Flight Number: <%= f.getFlight_number()%>	Arrival: <%= f.getArrival() %>	Departure: <%= f.getDeparture() %> </p>
  <form method="post" action=editflight.jsp>
 <p><input type="radio" name="test" value="<%=f.getFlight_id() %>" >Edit Book details</p>
 <input type="hidden" name="bookingid" value="<%=f.getbooking_id() %>">
 <%String s= (String)request.getAttribute("session");%>
 <%=s %>
 <% session.setAttribute("s",s);%>
 <% request.setAttribute("editflight",f);%>
<input type="submit">
</form>
</div>

<% }%>


<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
        this.classList.toggle("active");
        this.nextElementSibling.classList.toggle("show");
  }
}
</script>


</body>
</html>
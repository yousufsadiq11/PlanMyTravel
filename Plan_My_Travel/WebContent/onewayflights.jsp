
<%@ page import="java.sql.*" %>
<%@page import="java.util.*"%>
<%@ page import="com.uncc.planmytravel.servlet.flight_details" %>

<!DOCTYPE html>
<html>
<head>
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
<body><%=
request.getAttribute("session")%>


	<%
		ArrayList<flight_details> oneway=(ArrayList<flight_details>)request.getAttribute("oneway");
		request.setAttribute("oneway", oneway);
		
		for(flight_details f1:oneway){%>
			<h1><b>Available Flights on: </b></h1>
			<h3><b>Source: <%=f1.getSource() %> </b></h1>
			<h3><b>Destination: <%=f1.getDestination() %> </b></h1>
		
	<%break;} %>
	<% 
		for(flight_details f:oneway){
		

		 %>
	
<button class="accordion" name="x" value="<%=f.getFlight_number() %>"><%=f.getFlight_name() %></button>
<div class="panel">
 	<p>Flight Number: <%= f.getFlight_number()%>	Arrival: <%= f.getArrival() %>   Departure: <%= f.getDeparture() %> </p>
  <p>Economy Seats: <%=f.getEconomy_seat_count() %>	Business Seats: <%=f.getBusiness_seat_count() %></p>
  <form method="post" action=book.jsp>
 <p><input type="radio" name="test" value="<%=f.getFlight_id() %>" >Book</p>
  <p><input type="hidden" name="bookingid" value="<%=request.getAttribute("bookingid") %>" >Book</p>
 <%String s= (String)request.getAttribute("session");%>
 <%=s %>
 <% session.setAttribute("s",s);%>
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


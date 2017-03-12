<%--
  Created by IntelliJ IDEA.
  User: vipul
  Date: 10/22/2016
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.*"%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PlanMyTravel</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script>
        $(function () {
            $("#datepicker").datepicker({minDate: 0, maxDate: "+555000D"});
            $("#datepicker").datepicker("setDate", new Date());
            $("#datepicker").datepicker("option", "dateFormat", "dd/mm/yy");
        });

        $(function () {
            $("#dateofdeparture").datepicker({minDate: 0, maxDate: "+555000D"});
            $("#dateofdeparture").datepicker("setDate", new Date());
            $("#dateofdeparture").datepicker("option", "dateFormat", "dd/mm/yy");
        });
        
        $(function () {
            $("#dateofarrival").datepicker({minDate: 0, maxDate: "+555000D"});
            $("#dateofarrival").datepicker("setDate", new Date());
            $("#dateofarrival").datepicker("option", "dateFormat", "dd/mm/yy");
        });
    </script>

    <style>

        /* Modal Content/Box */
        .mycontent {
            margin: 5% auto 5% 10%; /* 5% from the top, 15% from the bottom and centered */
            width: 70%; /* Could be more or less, depending on screen size */
        }

    </style>
</head>

<h2 align="center">Plan My Travel</h2>

<form method="post" action="AddFlightDetailServlet" class="form-horizontal" >
    <div class="mycontent">

    <div class="form-group">
        <label for="flightname" class="col-sm-4 control-label">Flight Name</label>
        <div class="col-sm-4 center-block">
            <input type="text" class="form-control" id="flightname" name="flightname">
        </div>
    </div>


<% ArrayList<String> city=new ArrayList<String>();
int i=0;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan", "root","1234");
            Statement statement = conn.createStatement();
                        ResultSet resultset = 
                statement.executeQuery("select city_name from cities ") ; 

            while(resultset.next()) {
                city.add(resultset.getString(1));
            } 
        %>

<b>Select Source:</b>
<select class="selectpicker" data-live-search="true" name="source">
    
    <% 

for(i=0;i<city.size()-1;i++){
%>
        <option> <%= city.get(i) %> </option>
        <%}%>
 
  </select>
 
 <b>Select Destination:</b>
<select class="selectpicker"  data-live-search="true" name="destination">
    
            <% 

for(i=0;i<city.size()-1;i++){
%>
        <option> <%= city.get(i) %> </option>
        <%}%>
 
  </select>
  





    <div class="form-group">
        <label for="dateofdeparture" class="col-sm-4 control-label">Date Of Departure</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="dateofdeparture" name="dateofdeparture">
        </div>
    </div>

	<div class="form-group">
        <label for="dateofarrival" class="col-sm-4 control-label">Date Of Arrival</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="dateofarrival" name="dateofarrival">
        </div>
    </div>


        <div class="form-group">
            <label for="departure" class="col-sm-4 control-label">Departure time</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="departure" name="departure">
            </div>
        </div>

        <div class="form-group">
            <label for="arrival" class="col-sm-4 control-label">Arrival time</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="arrival" name="arrival">
            </div>
        </div>


        <div class="form-group">
        <label for="numberofeconomyseats" class="col-sm-4 control-label">Economy Seats</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="numberofeconomyseats" name="numberofeconomyseats">
        </div>
    </div>

    <div class="form-group">
        <label for="numberofbusinessseats" class="col-sm-4 control-label">Business Seats</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="numberofbusinessseats" name="numberofbusinessseats">
        </div>
    </div>
    
    <div class="form-group">
        <label for="flightnumber" class="col-sm-4 control-label">flight number</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="flightnumber" name="flightnumber">
        </div>
    </div>
</div>
   <div align="center"> <button type="submit" class="btn btn-primary">Submit</button> </div>
</form>

</body>
</html>

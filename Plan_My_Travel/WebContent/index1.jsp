<!DOCTYPE html>
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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/js/bootstrap-select.min.js"></script>  
  
  
  <meta charset="utf-8" />
  <title>jQuery UI Datepicker - Restrict date range</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>
    $(function() {
      $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+555000D" });
      $("#datepicker").datepicker("setDate",new Date());
      $( "#datepicker" ).datepicker( "option", "dateFormat", "dd/mm/yy");
    });
    
    $(function() {
        $( "#datepickerreturn" ).datepicker({ minDate: 0, maxDate: "+555000D" });
        $("#datepickerreturn").datepicker("setDate",new Date());
        $( "#datepickerreturn" ).datepicker( "option", "dateFormat", "dd/mm/yy");
      });
</script>
  </head>
  
<style>
/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

/* Extra styles for the cancel button */
.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
    position: relative;
}

img.avatar {
    width: 40%;
    border-radius: 50%;
}

.container {
    padding: 16px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
    position: absolute;
    right: 25px;
    top: 0;
    color: #000;
    font-size: 35px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: red;
    cursor: pointer;
}

/* Add Zoom Animation */
.animate {
    -webkit-animation: animatezoom 0.6s;
    animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
    from {-webkit-transform: scale(0)}
    to {-webkit-transform: scale(1)}
}
    
@keyframes animatezoom {
    from {transform: scale(0)}
    to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
}

</style>
<body>

<h2>Plan My Travel</h2>

<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login</button>

<div id="id01" class="modal">
  
  <form class="modal-content animate" method="post" action="servlet1">
    <div class="imgcontainer">.
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      
    </div>

    <div class="container">
      <label><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" required>

      <label><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" required>
        
      <button type="submit" name ="action" value="login">Login</button>

    </div>

  </form>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>

<br>
<button onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Register</button>

<div id="id02" class="modal">
  
  <form class="modal-content animate" method="post" action="servlet1">
    <div class="imgcontainer">.
      <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
      
    </div>

    <div class="container">
      <label><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="username" required>

      <label><b>Password</b></label>
      <input type="password" placeholder="Enter Password" name="password" required>
        
      <label><b>Re enter Password</b></label>
      <input type="password" placeholder="Re Enter Password" name="repassword" required>
      
      <label><b>First name</b></label>
      <input type="text" placeholder="First name" name="firstname" required>
      
      <label><b>Last name</b></label>
      <input type="text" placeholder="Last name" name="lastname" required>
      
      <label><b>Email</b></label>
      <input type="text" placeholder="Email" name="email" required>
      
      
      <button type="submit" name="action" value="register">Register</button>

    </div>

  </form>
</div>

<script>
// Get the modal
var modal = document.getElementById('id02');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
<form  method="post" action="servlet1">

<div class="container">
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
  
  
 <br>
 

 <p><b>Departure: </b><input type="text" id="datepicker" name="departuredate" value="departuredate" width = 20/></p>
 <p><b>Return:</b> <input type="text" id="datepickerreturn" name="returndate" value="returndate"width = 20 disabled="disabled"/></p>

  <input type="radio" name="triptype" value="onewaytrip" checked="checked" onclick="document.getElementById('datepickerreturn').disabled=true"> One way trip<br>
  <input type="radio" name="triptype" value="roundtrip" onclick="document.getElementById('datepickerreturn').disabled=false"> Round trip<br>

<br>


<br>


  <input type="submit" name="action" value="Search"><br>
</div>
</form>


</body>
</html>


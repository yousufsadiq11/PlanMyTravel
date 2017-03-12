
<%@ page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page import="com.uncc.planmytravel.servlet.flight_details"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<String> meal = new ArrayList<String>();
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/plan", "root", "1234");
		PreparedStatement statement = conn
				.prepareStatement("select meal_name from meal_plan");
		ResultSet resultset = statement.executeQuery();

		while (resultset.next()) {
			meal.add(resultset.getString(1));
		}
	%>



	<form method="post" action="BookServlet">
		Flight Number:<%=request.getParameter("test")%><br> Book <input
			type="hidden" name="flight_id"
			value="<%=request.getParameter("test")%>"><br> seats<input
			type="text" name="seats"> <br> Economy <input
			type="radio" name="seat_type" value="economy" checked><br>
		Business <input type="radio" name="seat_type" value="business"><br>

		<b>Select Meal plan:</b> <select class="selectpicker"
			data-live-search="true" name="meal_plan">

			<%
				for (int i = 0; i < meal.size(); i++) {
			%>
			<option>
				<%=meal.get(i)%>
			</option>
			<%
				}
			%>

		</select><br> Save card details <br> YES <input type="radio"
			name="save" value="yes"> No <input type="radio" name="save"
			value="no" checked><br> <br> Enter Card Details <input
			onclick="document.getElementById('name_on_card').disabled = false;document.getElementById('card_type1').disabled = false;document.getElementById('card_type2').disabled = false; document.getElementById('card_number').disabled = false; document.getElementById('expiry_date').disabled = false; document.getElementById('cvv').disabled = false;"
			type="radio" name="card" id="radio" value="new" checked> <input
			type="text" name="card_number" id="card_number"
			placeholder="enter card number"> <input type="text"
			name="expiry_date" id="expiry_date" placeholder="enter expiry date">
		<input type="text" name="cvv" id="cvv" placeholder="enter cvv">
		<input type="text" name="name_on_card" id="name_on_card"
			placeholder="enter name on the card"> <br> VISA <input
			type="radio" name="card_type" id="card_type1" value="visa">
		MASTER CARD <input type="radio" name="card_type" id="card_type2"
			value="master card"> <br> Debit<input type="radio"
			name="payment_method" value="debit"> Credit<input
			type="radio" name="payment_method" value="credit"> <br>
		<br> <br> <input
			onclick="document.getElementById('name_on_card').disabled = true;document.getElementById('card_type1').disabled = true;document.getElementById('card_type2').disabled = true;document.getElementById('card_number').disabled = true; document.getElementById('expiry_date').disabled = true; document.getElementById('cvv').disabled = true; document.getElementById('charstype').disabled = false;"
			type="radio" name="card" value="use">Use existing card
		deatils
		<%=session.getAttribute("s")%>
		<input type="hidden" name="username"
			value="<%=session.getAttribute("s")%>"> <input type="submit"
			name="action" value="book">
			<input type="hidden" name="bookingid" value=<%=request.getParameter("bookingid") %>>
	</form>
</body>
</html>

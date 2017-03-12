package com.uncc.planmytravel.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import business.User;
import data.UserDB;

/**
 * Servlet implementation class MySQLConnect
 */

public class servlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String s;
	String d;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action == null) {
			System.out.println("admin.LoginServlet.doPost() action is null");
		} else {
			System.out.println("admin.LoginServlet.doPost()" + action + " else if");
		}

		String bookingid = request.getParameter("bookingid");
		System.out.println("booking id "+bookingid);
		if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan", "root",
						"1234");
				PreparedStatement pst = conn.prepareStatement("Select * from registered_user where username=? and password=?");
				pst.setString(1, username);
				pst.setString(2, password);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					System.out.println(username);
					request.setAttribute("username", username);
					request.getRequestDispatcher("registeredhome.jsp").forward(request, response);
					
				} else {
					out.println("Incorrect login credentials");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("Search")) {
			try {
String session=request.getParameter("session");
System.out.println(session);
request.setAttribute("session", session);
				ArrayList<flight_details> oneway = new ArrayList<flight_details>();
				ArrayList<flight_details> roundtrip = new ArrayList<flight_details>();
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan", "root", "1234");
				String source = request.getParameter("source");
				String destination = request.getParameter("destination");
			 s=source;
				 d=destination;
				String departuredate[] = request.getParameterValues("departuredate");
				SimpleDateFormat availDate = new SimpleDateFormat("dd/MM/yyyy");
				Date chosenDate = availDate.parse(departuredate[0]);
				System.out.println(chosenDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String format = formatter.format(chosenDate);
				System.out.println(format);
				String triptype = request.getParameter("triptype");
				if (triptype.equals("roundtrip")) {
					PreparedStatement sourcecity = conn.prepareStatement(
							"select city_id from cities where city_name=? ");
					sourcecity.setString(1,source );
					ResultSet sourceset=sourcecity.executeQuery();
					while(sourceset.next()){
						source=sourceset.getString(1);
					}
					PreparedStatement destinationcity = conn.prepareStatement(
							"select city_id from cities where city_name=? ");
					destinationcity.setString(1,destination );
					ResultSet destinationset=destinationcity.executeQuery();
					while(destinationset.next()){
						destination=destinationset.getString(1);
					}
					String returndate[] = request.getParameterValues("returndate");
					SimpleDateFormat availDate1 = new SimpleDateFormat("dd/MM/yyyy");
					Date chosenDate1 = availDate1.parse(returndate[0]);
					SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
					String format1 = formatter1.format(chosenDate1);
					System.out.println(format1);
					PreparedStatement pst = conn.prepareStatement(
							"select * from flight_details where source_id=? and destination_id=? and date_of_arrival=? ");
					pst.setString(1, source);
					pst.setString(2, destination);
					pst.setString(3, format);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						flight_details f = new flight_details();
						f.setFlight_number(rs.getString(2));
						PreparedStatement airlinesset = conn.prepareStatement(
								"select * from airlines where airlines_id=? ");
						airlinesset.setString(1, rs.getString(9));
						ResultSet airlinesname=airlinesset.executeQuery();
						while(airlinesname.next()){
							f.setFlight_name(airlinesname.getString(2));	
						}
						f.setFlight_id(rs.getInt(1));
						
						
						f.setSource(s);
						f.setDestination(d);
						f.setArrival(rs.getString(3));
						f.setDeparture(rs.getString(4));
						//f.setDate_of_operation(rs.getDate(7));
						f.setEconomy_seat_count(rs.getInt(7));
						f.setBusiness_seat_count(rs.getInt(8));
						oneway.add(f);

						
						
					}

					PreparedStatement pst1 = conn.prepareStatement(
							"select * from flight_details where source_id=? and destination_id=? and date_of_arrival=? ");
					pst1.setString(1, destination);
					pst1.setString(2, source);
					pst1.setString(3, format1);
					ResultSet rs1 = pst1.executeQuery();

					while (rs1.next()) {
						flight_details f2 = new flight_details();
						f2.setFlight_number(rs1.getString(2));
						PreparedStatement airlinesset = conn.prepareStatement(
								"select * from airlines where airlines_id=? ");
						airlinesset.setString(1, rs1.getString(9));
						ResultSet airlinesname=airlinesset.executeQuery();
						while(airlinesname.next()){
							f2.setFlight_name(airlinesname.getString(2));	
						}
						f2.setFlight_id(rs1.getInt(1));
						
						
						
						f2.setSource(d);
						f2.setDestination(s);
						f2.setArrival(rs1.getString(3));
						f2.setDeparture(rs1.getString(4));
						//f.setDate_of_operation(rs.getDate(7));
						f2.setEconomy_seat_count(rs1.getInt(7));
						f2.setBusiness_seat_count(rs1.getInt(8));
						roundtrip.add(f2);
					
					
						
					
					
					
					}
					if(oneway.size()!=0||roundtrip.size()!=0){
					request.setAttribute("oneway", oneway);
					request.setAttribute("roundtrip", roundtrip);
					request.setAttribute("session", session);
					request.getRequestDispatcher("roundtripflights.jsp").forward(request, response);}
					else
						out.println("No flights available");
				}
				if (triptype.equals("onewaytrip")) {
					ArrayList<flight_details> oneway1 = new ArrayList<flight_details>();
				
					PreparedStatement sourcecity = conn.prepareStatement(
							"select city_id from cities where city_name=? ");
					sourcecity.setString(1,source );
					ResultSet sourceset=sourcecity.executeQuery();
					while(sourceset.next()){
						source=sourceset.getString(1);
					}
					PreparedStatement destinationcity = conn.prepareStatement(
							"select city_id from cities where city_name=? ");
					destinationcity.setString(1,destination );
					ResultSet destinationset=destinationcity.executeQuery();
					while(destinationset.next()){
						destination=destinationset.getString(1);
					}
					PreparedStatement pst = conn.prepareStatement(
							"select * from flight_details where source_id=? and destination_id=? and date_of_arrival=? ");
					pst.setString(1, source);
					pst.setString(2, destination);
					pst.setString(3, format);

					ResultSet rs8 = pst.executeQuery();

					int i = 0;
					oneway1.clear();
					while (rs8.next()) {
						flight_details ff = new flight_details();
						ff.setFlight_number(rs8.getString(2));
						PreparedStatement airlinesset = conn.prepareStatement(
								"select * from airlines where airlines_id=? ");
						airlinesset.setString(1, rs8.getString(9));
						ResultSet airlinesname=airlinesset.executeQuery();
						while(airlinesname.next()){
							ff.setFlight_name(airlinesname.getString(2));	
						}
						ff.setFlight_id(rs8.getInt(1));
						ff.setSource(s);
						ff.setDestination(d);
						ff.setArrival(rs8.getString(3));
						ff.setDeparture(rs8.getString(4));
						//ff.setDate_of_arrival(rs8.getDate(5));
						ff.setEconomy_seat_count(rs8.getInt(7));
						ff.setBusiness_seat_count(rs8.getInt(8));
						oneway1.add(i, ff);
					

					}
					System.out.println(oneway1.size());
if(oneway1.size()!=0){
					request.setAttribute("oneway", oneway1);
					request.setAttribute("session", session);
					request.setAttribute("bookingid", bookingid);
					request.getRequestDispatcher("onewayflights.jsp").forward(request, response);}
					else
						out.println("No flights available");
					
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Register
		else if (action.equals("register")) {
			System.out.println("admin.LoginServlet.doPost() register");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String emailid = request.getParameter("email");

			if (password.equals(repassword)) {
				System.out.println("admin.LoginServlet.doPost() register password");
				try {
					Properties properties = new Properties();
					properties.setProperty("user", "root");
					properties.setProperty("password", "1234");
					properties.setProperty("useSSL", "false");
					properties.setProperty("autoReconnect", "true");
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan",
							"root","1234");
					PreparedStatement pst = conn.prepareStatement(
							"insert into registered_user values(?,?,?,?,?)");
					pst.setString(1, username);
					pst.setString(2, password);
					pst.setString(3, firstname);
					pst.setString(4, lastname);
					pst.setString(5, emailid);
					int result = pst.executeUpdate();
					if (result > 0) {
						out.println("Registered succesfully");
					} else {
						out.println("Registration failed");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		/*
		 * 
		 * 
		 * String userName = request.getParameter("username");
		 * 
		 * String passWord = request.getParameter("password"); User user =
		 * UserDB.selectUser(userName);
		 * System.out.println("admin.LoginServlet.doPost()" + "username " +
		 * userName); System.out.println("admin.LoginServlet.doPost()" +
		 * "password " + passWord); if (userName == null) { userName = "dummy";
		 * } if (passWord == null) { passWord = "dummy"; } if (user == null) {
		 * System.out.println("admin.LoginServlet.doPost()" +
		 * "User Does not exist"); } else if
		 * (passWord.equals(user.getPassWord())) { //user is registered //url =
		 * "/home.jsp"; //request.setAttribute("user", user);
		 * //request.getServletContext().getRequestDispatcher(url).forward(
		 * request, response); } else { String message =
		 * "Incorrect username or password"; String url = "/index.html";
		 * request.setAttribute("message", message);
		 * request.getServletContext().getRequestDispatcher(url).forward(
		 * request, response); }
		 */

	}
}

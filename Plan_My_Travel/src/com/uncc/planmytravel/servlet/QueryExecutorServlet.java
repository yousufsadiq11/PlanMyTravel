package com.uncc.planmytravel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.CallableStatement;

import Db.DbConnection;

public class QueryExecutorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String queryType = request.getParameter("queryType");
		String query = request.getParameter("queryTextbox");
		String tableName = request.getParameter("tableName");
		String procedureName = request.getParameter("procedureName");

		out.println("Query Type: " + queryType);
		out.println("<br>Table: " + tableName);
		out.println("<br>Query: " + query);

		if (queryType.equals("insert")) {
			runInsertQuery(query, request, response);
		} else if (queryType.equals("update")) {
			runUpdateQuery(query, request, response);
		} else if (queryType.equals("delete")) {
			runDeleteQuery(query, request, response);
		} else if (queryType.equals("select")) {
			runSelectQuery(tableName, request, response);
		} else if (queryType.equals("procedure")) {
			runProcedure(procedureName, request, response);
		}
	}

	public void runInsertQuery(String query, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection conn = getDatabaseConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("<br> Query successfully executed. Record inserted into the database.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<br> An error occured in executing this query. Record not inserted into the table.");
		}
	}

	public void runUpdateQuery(String query, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection conn = getDatabaseConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("<br> Query successfully executed. Table updated.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<br> An error occured in executing this query. Table cannot be updated.");
		}
	}

	public void runDeleteQuery(String query, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection conn = getDatabaseConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			PrintWriter out = response.getWriter();
			out.println("<br> Query successfully executed. Record deleted from table.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<br> An error occured in executing this query. Record cannot be deleted.");
		}
	}

	public void runProcedure(String procedureName, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection conn = getDatabaseConnection();
		CallableStatement callableStatement = null;
		try {
			if (procedureName.equals("book_flight")){
			callableStatement = conn.prepareCall("{call book_flight(?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setString(2, "sanju");
			callableStatement.setInt(3, 1);
			callableStatement.setInt(4, 1);
			callableStatement.setString(5, "L6539fd560");
			callableStatement.setString(6,"Veg");
			callableStatement.setString(7, "K");
			callableStatement.setString(8, "sanju");
			
			callableStatement.executeUpdate();
			
			PrintWriter out = response.getWriter();
			out.println("<br> Query successfully executed. Procedure ran successfully.");
			}
			
			if (procedureName.equals("insert_admin")){
				callableStatement = conn.prepareCall("{call insert_admin(?,?,?,?,?)}");
				callableStatement.setString(1, "sanju");
				callableStatement.setString(2, "sanju");
				callableStatement.setString(3, "sanju@uncc.edu");
				callableStatement.setString(4, "sanju");
				callableStatement.setString(5, "k");

				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("delete_meal_plan")){
				callableStatement = conn.prepareCall("{call delete_meal_plan(?)}");
				callableStatement.setString(1, "Veg");

				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("delete_flight_details")){
				callableStatement = conn.prepareCall("{call delete_flight_details(?)}");
				callableStatement.setInt(1, 1);

				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("delete_booking_details")){
				callableStatement = conn.prepareCall("{call delete_booking_details(?)}");
				callableStatement.setInt(1, 1);

				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_booking_details")){
				callableStatement = conn.prepareCall("{call insert_booking_details(?,?,?,?)}");
				callableStatement.setString(1, "sanju");
				callableStatement.setInt(2, 1);
				callableStatement.setInt(3,1);
				callableStatement.setInt(4,1);
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_card_details")){
				callableStatement = conn.prepareCall("{call insert_card_details(?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, 1234123412);
				callableStatement.setString(2, "sanju");
				callableStatement.setInt(3,123);
				callableStatement.setString(4,"Visa");
				callableStatement.setString(5,"sanju");
				callableStatement.setString(6,"Debit");
				callableStatement.setDate(7,new Date(02,02,2016));
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_meal_plan")){
				callableStatement = conn.prepareCall("{call insert_meal_plan(?)}");
				
				callableStatement.setString(1, "Veg");
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_flight_details")){
				callableStatement = conn.prepareCall("{call insert_flight_details(?,?,?,?,?,?,?,?,?)}");
				
				callableStatement.setString(1, "text");
				callableStatement.setTimestamp(2, new Timestamp(1234));
				callableStatement.setTimestamp(3, new Timestamp(1234));
				callableStatement.setDate(4,new Date(02,02,2016));
				callableStatement.setDate(5,new Date(02,02,2016));
				callableStatement.setInt(6,1);
				callableStatement.setInt(7,1);
				callableStatement.setInt(8,1);
				callableStatement.setInt(9,1);
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_passenger_details")){
				callableStatement = conn.prepareCall("{call insert_passenger_details(?,?,?,?,?)}");
				
				callableStatement.setInt(1,1);
				callableStatement.setString(2, "L644df650");
				callableStatement.setString(3,"Veg");
				callableStatement.setString(4,"k");
				callableStatement.setString(5, "sanju");
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}
			
			if (procedureName.equals("insert_registered_user")){
				callableStatement = conn.prepareCall("{call insert_registered_user(?,?,?,?,?)}");
				
				callableStatement.setString(1, "sanju");
				callableStatement.setString(2, "sanju");
				callableStatement.setString(3,"sanju@uncc.edu");
				callableStatement.setString(4,"sanju");
				callableStatement.setString(5, "k");
				
				callableStatement.executeUpdate();
				
				PrintWriter out = response.getWriter();
				out.println("<br> Query successfully executed. Procedure ran successfully.");
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<br> An error occured in executing this query. Procedure was unable to run.");
		}
	}

	public void runSelectQuery(String tableName, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Connection conn = getDatabaseConnection();
		try {
			String query = "select * from " + tableName + ";";
			PreparedStatement prepareStatement = conn.prepareStatement(query);
			ResultSet rs = prepareStatement.executeQuery();
			if (tableName.equals("admin")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "username"));
				out.print(hu.getTH("center", "password"));
				out.print(hu.getTH("center", "email_id"));
				out.print(hu.getTH("center", "first_name"));
				out.print(hu.getTH("center", "last_name"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getString("username"));
					av.addElement(rs.getString("password"));
					av.addElement(rs.getString("email_id"));
					av.addElement(rs.getString("first_name"));
					av.addElement(rs.getString("last_name"));
				}

				out.print(hu.getTableContents("center", av, 5));
				out.print(hu.getHtmlFooter());

			}

			if (tableName.equals("airlines")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "airlines_id"));
				out.print(hu.getTH("center", "airlines_name"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getString("airlines_id"));
					av.addElement(rs.getString("airlines_name"));
				}

				out.print(hu.getTableContents("center", av, 2));
				out.print(hu.getHtmlFooter());

			}

			if (tableName.equals("booked_flights")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "booking_id"));
				out.print(hu.getTH("center", "user_name"));
				out.print(hu.getTH("center", "flight_id"));
				out.print(hu.getTH("center", "no_of_economy_seats"));
				out.print(hu.getTH("center", "no_of_business_seats"));
				out.print(hu.getTH("center", "checkin_flag"));
				out.print(hu.getTH("center", "feedback_flag"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("booking_id"));
					av.addElement(rs.getString("user_name"));
					av.addElement(rs.getInt("flight_id"));
					av.addElement(rs.getInt("no_of_economy_seats"));
					av.addElement(rs.getInt("no_of_business_seats"));
					av.addElement(rs.getInt("checkin_flag"));
					av.addElement(rs.getInt("feedback_flag"));
				}

				out.print(hu.getTableContents("center", av, 7));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("card_details")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "card_no"));
				out.print(hu.getTH("center", "name_on_card"));
				out.print(hu.getTH("center", "cvv"));
				out.print(hu.getTH("center", "card_type"));
				out.print(hu.getTH("center", "user_name"));
				out.print(hu.getTH("center", "payment_method"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("card_no"));
					av.addElement(rs.getString("name_on_card"));
					av.addElement(rs.getString("cvv"));
					av.addElement(rs.getString("card_type"));
					av.addElement(rs.getString("user_name"));
					av.addElement(rs.getString("payment_method"));
				}

				out.print(hu.getTableContents("center", av, 6));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("cities")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "city_id"));
				out.print(hu.getTH("center", "city_name"));
				out.print(hu.getTH("center", "country_id"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("city_id"));
					av.addElement(rs.getString("city_name"));
					av.addElement(rs.getInt("country_id"));
				}

				out.print(hu.getTableContents("center", av, 3));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("country")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "country_id"));
				out.print(hu.getTH("center", "country_name"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("country_id"));
					av.addElement(rs.getString("country_name"));
				}

				out.print(hu.getTableContents("center", av, 2));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("flight_details")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "flight_id"));
				out.print(hu.getTH("center", "flight_number"));
				out.print(hu.getTH("center", "arrival_time"));
				out.print(hu.getTH("center", "departure_time"));
				out.print(hu.getTH("center", "date_of_arrival"));
				out.print(hu.getTH("center", "date_of_departure"));
				out.print(hu.getTH("center", "economy_seat_count"));
				out.print(hu.getTH("center", "business_seat_count"));
				out.print(hu.getTH("center", "source_id"));
				out.print(hu.getTH("center", "destination_id"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("flight_id"));
					av.addElement(rs.getString("flight_number"));
					av.addElement(rs.getTime("arrival_time"));
					av.addElement(rs.getTime("departure_time"));
					av.addElement(rs.getDate("date_of_arrival"));
					av.addElement(rs.getDate("date_of_departure"));
					av.addElement(rs.getInt("economy_seat_count"));
					av.addElement(rs.getInt("business_seat_count"));
					av.addElement(rs.getInt("source_id"));
					av.addElement(rs.getInt("destination_id"));
				}

				out.print(hu.getTableContents("center", av, 10));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("flight_info")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "flight_number"));
				out.print(hu.getTH("center", "airlines_id"));
				out.print(hu.getTH("center", "total_economy_seats"));
				out.print(hu.getTH("center", "total_business_seats"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getString("flight_number"));
					av.addElement(rs.getInt("airlines_id"));
					av.addElement(rs.getInt("total_economy_seats"));
					av.addElement(rs.getInt("total_business_seats"));
				}

				out.print(hu.getTableContents("center", av, 4));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("meal_plan")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "meal_id"));
				out.print(hu.getTH("center", "meal_name"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getInt("meal_id"));
					av.addElement(rs.getString("meal_name"));
				}

				out.print(hu.getTableContents("center", av, 2));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("passenger_details")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "passport_no"));
				out.print(hu.getTH("center", "booking_id"));
				out.print(hu.getTH("center", "passenger_last_name"));
				out.print(hu.getTH("center", "passenger_first_name"));
				out.print(hu.getTH("center", "meal_id"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getString("passport_no"));
					av.addElement(rs.getInt("booking_id"));
					av.addElement(rs.getString("passenger_last_name"));
					av.addElement(rs.getString("passenger_first_name"));
					av.addElement(rs.getInt("meal_id"));
				}

				out.print(hu.getTableContents("center", av, 5));
				out.print(hu.getHtmlFooter());
			}

			if (tableName.equals("registered_user")) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				HtmlUtils hu = new HtmlUtils();

				out.print(hu.createHtmlHeader("Print Table"));

				out.print(hu.getTableHead("center", 1));

				out.print(hu.getTH("center", "username"));
				out.print(hu.getTH("center", "password"));
				out.print(hu.getTH("center", "first_name"));
				out.print(hu.getTH("center", "last_name"));
				out.print(hu.getTH("center", "emailid"));

				Vector av = new Vector();

				while (rs.next()) {
					av.addElement(rs.getString("username"));
					av.addElement(rs.getString("password"));
					av.addElement(rs.getString("first_name"));
					av.addElement(rs.getString("last_name"));
					av.addElement(rs.getString("emailid"));
				}

				out.print(hu.getTableContents("center", av, 5));
				out.print(hu.getHtmlFooter());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("An error occured in executing this query");
		}
	}

	public Connection getDatabaseConnection() {
		Connection conn = new DbConnection().getConnection();
		return conn;
	}
}

class HtmlUtils {

	public String createHtmlHeader(String title) {

		String htmlHeader = null;
		htmlHeader = "<HTML><HEAD><TITLE> " + title + " </TITLE></HEAD><BODY>";
		return htmlHeader;
	}

	public String getHtmlFooter() {

		String htmlFooter = "</BODY></HTML>";
		return htmlFooter;
	}

	public String getHead(int level, String heading) {
		return "<H" + level + "> " + heading + "</H" + level + ">";
	}

	public String getTableHead(String align, int border) {

		String tableHeader = null;
		tableHeader = "<TABLE align=" + align + " border=" + border + ">";
		return tableHeader;

	}

	public String getTR(String align) {
		String TRCell = null;
		TRCell = "<TR align=" + align + ">";
		return TRCell;
	}

	public String getTR() {
		String TRCell = null;
		TRCell = "<TR>";
		return TRCell;
	}

	public String getTD(String align, String value) {
		String TDCell = null;
		TDCell = "<TD align=" + align + "> " + value + " </TD>";
		return TDCell;
	}

	public String getTD() {
		String TDCell = null;
		TDCell = "<TD>";
		return TDCell;
	}

	public String getTD(int width) {
		String TDCell = null;
		TDCell = "<TD WIDTH=" + width + ">";
		return TDCell;
	}

	public String getTH(String align, String value) {
		String THCell = null;
		THCell = "<TH align=" + align + "> " + value + " </TH>";
		return THCell;
	}

	public String getTableContents(String align, Vector values, int elementCounter) throws IOException {

		StringWriter Cells = new StringWriter();
		String contents = new String();
		int vsize = values.size();

		Cells.write("<TR>");

		for (int i = 0; i < vsize; i++) {
			String value = values.elementAt(i).toString();

			if (i != 0) {
				if (i >= elementCounter) {

					if (i % elementCounter == 0) {
						Cells.write("</TR>\n\n<TR>");
					}
				}
			}

			Cells.write("<TD align=" + align + "> " + value + " </TD> \n");
		}

		Cells.write("</TR>");

		contents = Cells.toString();
		Cells.flush();
		Cells.close();

		return contents;
	}

	public String getClosedTR() {
		String TRCell = null;
		TRCell = "</TR>";
		return TRCell;
	}

	public String getClosedTD() {
		String TDCell = null;
		TDCell = "</TD>";
		return TDCell;
	}

	public String getBR(int lines) {

		StringWriter lineBR = new StringWriter();
		String lineBRs = new String();

		for (int i = 0; i <= lines; i++) {
			lineBR.write("<BR>\n");
		}
		lineBRs = lineBR.toString();

		return lineBRs;
	}

	public String getLI(String item) {

		String li = new String("<LI>");
		li += item;
		return li;

	}

}

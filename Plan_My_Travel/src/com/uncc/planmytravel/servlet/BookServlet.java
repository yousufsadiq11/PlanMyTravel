package com.uncc.planmytravel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Db.DbConnection;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		String bookingid = request.getParameter("bookingid");
		
		
		System.out.println("Bookservlet action="+action +"Booking id=" +bookingid);
		if (action.equals("book")) {

			PrintWriter out = response.getWriter();
			int meal_id = 0;
			String username=request.getParameter("username");
			System.out.println(username);
			Connection conn = (Connection) DbConnection.getConnection();
			
			if (bookingid != null)
			{
				PreparedStatement deletebooking;
				try {
					deletebooking = conn.prepareStatement("delete from booked_flights where booking_id=?");
					deletebooking.setInt(1, Integer.parseInt(bookingid));
					deletebooking.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			String s=request.getParameter("flight_id");System.out.println(s);
			int flight_id = Integer.parseInt(request.getParameter("flight_id"));
			System.out.println(flight_id);
			int seats = Integer.parseInt(request.getParameter("seats"));
			String seat_type = request.getParameter("seat_type");
			String meal_plan = request.getParameter("meal_plan");
			String payment_method=request.getParameter("payment_method");
			String save = request.getParameter("save");
			String card = request.getParameter("card");
			
			
			try {
				PreparedStatement flightset = conn.prepareStatement("select economy_seat_count,business_seat_count from flight_details where flight_id=?");
				flightset.setInt(1, flight_id);
				ResultSet flightseat=flightset.executeQuery();
				
				
				
				PreparedStatement mealset = conn.prepareStatement("select * from meal_plan where meal_name=?");
				System.out.println(meal_plan);
				mealset.setString(1, meal_plan);
				ResultSet meal=mealset.executeQuery();
				while(meal.next()){
					
					 meal_id=meal.getInt(1);
				}
				System.out.println(meal_id);
				if(seat_type.equals("economy")){
				PreparedStatement seatset = conn.prepareStatement("select economy_seat_count from flight_details where flight_id=?");
				seatset.setInt(1, flight_id);
				ResultSet seatcount=seatset.executeQuery();
				while(seatcount.next()){int reduce=seatcount.getInt(1);
					if(seats<seatcount.getInt(1)){
						
						if(card.equals("new")){
							String card_number = request.getParameter("card_number");
							String expiry_date = request.getParameter("expiry_date");
							String cvv = request.getParameter("cvv");
							String name_on_card = request.getParameter("name_on_card");
							String card_type = request.getParameter("card_type");

						if(save.equals("yes")){
							PreparedStatement carddetails = conn.prepareStatement("select * from card_details where card_no=?");
							carddetails.setString(1, card_number);
							ResultSet cardresultset=carddetails.executeQuery();
							if(cardresultset.next()){
								PreparedStatement cardset = conn.prepareStatement("update card_details set name_on_card=?, cvv=?, card_type=?, payment_method=?, user_name=? where card_no=?");
								
								cardset.setString(1, name_on_card);
								cardset.setString(2, cvv);
								cardset.setString(3, card_type);
								cardset.setString(4, payment_method);
								cardset.setString(5, username);
								cardset.setString(6, card_number);
								int cardrow=cardset.executeUpdate();
							}
							else
							{
								PreparedStatement cardset = conn.prepareStatement("insert into card_details values(?,?,?,?,?,?)");
								cardset.setString(1, card_number);
								cardset.setString(2, name_on_card);
								cardset.setString(3, cvv);
								cardset.setString(4, card_type);
								cardset.setString(5, username);
								cardset.setString(6, payment_method);
								int cardrow=cardset.executeUpdate();
							}
							}
							
							//int no_of_economy_seats = 3;
							int no_of_business_seats = 0;
							PreparedStatement pst = conn.prepareStatement("insert into booked_flights(user_name,flight_id,no_of_economy_seats,no_of_business_seats) values(?,?,?,?)");
							pst.setString(1, username);
							pst.setInt(2, flight_id);
							pst.setInt(3, seats);
							pst.setInt(4, no_of_business_seats);
							int flag = pst.executeUpdate();
							
							PreparedStatement lastid = conn.prepareStatement("SELECT LAST_INSERT_ID()");
							ResultSet bookingidlsit = lastid.executeQuery();
							int booking_id = 0;
							while(bookingidlsit.next()){
								booking_id = bookingidlsit.getInt(1);
							}
							
							PreparedStatement emailid = conn.prepareStatement("SELECT emailid from registered_user where username=?");
							emailid.setString(1, username);
							ResultSet emailidlist = emailid.executeQuery();
							String bookedemailid = null;
							while(emailidlist.next()){
								bookedemailid = emailidlist.getString(1);
							}
							
							PreparedStatement event = conn.prepareStatement("select fd.date_of_departure from flight_details fd inner join booked_flights bf on fd.flight_id = bf.flight_id where bf.booking_id=?");
							event.setInt(1, booking_id);
				            ResultSet date=event.executeQuery();
				            Date datevalue = null;
							while(date.next()){
								datevalue = date.getDate(1);
							}
							
							String event_notification_string = "event_notification" + booking_id + username;
				            int i=pst .executeUpdate();
							PreparedStatement preparredevent = conn.prepareStatement("CREATE EVENT IF NOT EXISTS ? ON SCHEDULE AT ? - interval 1 day DO INSERT INTO email_notification(booking_id,email) VALUES(?,?)");
							preparredevent.setString(1, event_notification_string);
							preparredevent.setDate(2, datevalue);
							preparredevent.setInt(3, booking_id);
							preparredevent.setString(4, bookedemailid);
							int flagevent= pst.executeUpdate();
							
							
							PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET economy_seat_count = ? where flight_id=?");
							insert.setInt(1, reduce-seats);
							insert.setInt(2, flight_id);
							int result=insert.executeUpdate();
							out.println("booked successfully");
							
						
						}
							
							
							
						
						else
						
						{
							int details=0;
							int no_of_business_seats = 0;
							PreparedStatement cardset = conn.prepareStatement("select * from card_details where user_name=?");
							cardset.setString(1,username);
							ResultSet cardrow=cardset.executeQuery();
							while(cardrow.next()){
								details++;
							}
							if(details>0){
								PreparedStatement pst = conn.prepareStatement("insert into booked_flights(user_name,flight_id,no_of_economy_seats,no_of_business_seats) values(?,?,?,?)");
								pst.setString(1, username);
								pst.setInt(2, flight_id);
								pst.setInt(3, seats);
								pst.setInt(4, no_of_business_seats);
								int flag = pst.executeUpdate();
								
								PreparedStatement lastid = conn.prepareStatement("SELECT LAST_INSERT_ID()");
								ResultSet bookingidlsit = lastid.executeQuery();
								int booking_id = 0;
								while(bookingidlsit.next()){
									booking_id = bookingidlsit.getInt(1);
								}
								
								PreparedStatement emailid = conn.prepareStatement("SELECT emailid from registered_user where username=?");
								emailid.setString(1, username);
								ResultSet emailidlist = emailid.executeQuery();
								String bookedemailid = null;
								while(emailidlist.next()){
									bookedemailid = emailidlist.getString(1);
								}
								
								PreparedStatement event = conn.prepareStatement("select fd.date_of_departure from flight_details fd inner join booked_flights bf on fd.flight_id = bf.flight_id where bf.booking_id=?");
								event.setInt(1, booking_id);
					            ResultSet date=event.executeQuery();
					            Date datevalue = null;
								while(date.next()){
									datevalue = date.getDate(1);
								}
								
								String event_notification_string = "event_notification" + booking_id + username;
					            int i=pst .executeUpdate();
								PreparedStatement preparredevent = conn.prepareStatement("CREATE EVENT IF NOT EXISTS ? ON SCHEDULE AT ? - interval 1 day DO INSERT INTO email_notification(booking_id,email) VALUES(?,?)");
								preparredevent.setString(1, event_notification_string);
								preparredevent.setDate(2, datevalue);
								preparredevent.setInt(3, booking_id);
								preparredevent.setString(4, bookedemailid);
								int flagevent= pst.executeUpdate();
								
									PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET economy_seat_count = ? where flight_id=?");
									insert.setInt(1, reduce-seats);
									insert.setInt(2, flight_id);
									int result=insert.executeUpdate();
									out.println("booked successfully");
								
							}
							else
								out.println("card details have not been saved previously" );
						}
						
						
						
					}
					else
						{out.println("entered seats are higher than availabe seats");}
				}
				}
				else
				{
					
					PreparedStatement seatset = conn.prepareStatement("select no_of_business_seats from flight_details where flight_id=?");
					seatset.setInt(1, flight_id);
					ResultSet seatcount=seatset.executeQuery();
					while(seatcount.next()){
						if(seats<seatcount.getInt(1)){int reduce=seatcount.getInt(1);
							if(card.equals("new")){
								String card_number = request.getParameter("card_number");
								String expiry_date = request.getParameter("expiry_date");
								String cvv = request.getParameter("cvv");
								String name_on_card = request.getParameter("name_on_card");
								String card_type = request.getParameter("card_type");

							if(save.equals("yes")){
								PreparedStatement carddetails = conn.prepareStatement("select * from card_details where card_no=?");
								carddetails.setString(1, card_number);
								ResultSet cardresultset=carddetails.executeQuery();
								if(cardresultset.next()){
									PreparedStatement cardset = conn.prepareStatement("update card_details set name_on_card=?, cvv=?, card_type=?, payment_method=? where card_no=?");
									
									cardset.setString(1, name_on_card);
									cardset.setString(2, cvv);
									cardset.setString(3, card_type);
									cardset.setString(4, payment_method);
									cardset.setString(5, card_number);
									int cardrow=cardset.executeUpdate();
								}
								else
								{
									PreparedStatement cardset = conn.prepareStatement("insert into card_details values(?,?,?,?,?,?)");
									cardset.setString(1, card_number);
									cardset.setString(2, name_on_card);
									cardset.setString(3, cvv);
									cardset.setString(4, card_type);
									cardset.setString(5, username);
									cardset.setString(6, payment_method);
									int cardrow=cardset.executeUpdate();
								}			
							}
								
								
								int no_of_economy_seats=0;
								
								PreparedStatement pst = conn.prepareStatement("insert into booked_flights(user_name,flight_id,no_of_economy_seats,no_of_business_seats) values(?,?,?,?)");
								pst.setString(1, username);
								pst.setInt(2, flight_id);
								pst.setInt(3, no_of_economy_seats);
								pst.setInt(4, seats);
								int flag = pst.executeUpdate();
								
								PreparedStatement lastid = conn.prepareStatement("SELECT LAST_INSERT_ID()");
								ResultSet bookingidlsit = lastid.executeQuery();
								int booking_id = 0;
								while(bookingidlsit.next()){
									booking_id = bookingidlsit.getInt(1);
								}
								
								PreparedStatement emailid = conn.prepareStatement("SELECT emailid from registered_user where username=?");
								emailid.setString(1, username);
								ResultSet emailidlist = emailid.executeQuery();
								String bookedemailid = null;
								while(emailidlist.next()){
									bookedemailid = emailidlist.getString(1);
								}
								
								PreparedStatement event = conn.prepareStatement("select fd.date_of_departure from flight_details fd inner join booked_flights bf on fd.flight_id = bf.flight_id where bf.booking_id=?");
								event.setInt(1, booking_id);
					            ResultSet date=event.executeQuery();
					            Date datevalue = null;
								while(date.next()){
									datevalue = date.getDate(1);
								}
								
								String event_notification_string = "event_notification" + booking_id + username;
					            int i=pst .executeUpdate();
								PreparedStatement preparredevent = conn.prepareStatement("CREATE EVENT IF NOT EXISTS ? ON SCHEDULE AT ? - interval 1 day DO INSERT INTO email_notification(booking_id,email) VALUES(?,?)");
								preparredevent.setString(1, event_notification_string);
								preparredevent.setDate(2, datevalue);
								preparredevent.setInt(3, booking_id);
								preparredevent.setString(4, bookedemailid);
								int flagevent= pst.executeUpdate();
								
								PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET business_seat_count = ? where flight_id=?");
								insert.setInt(1, reduce-seats);
								insert.setInt(2, flight_id);
								int result=insert.executeUpdate();
								out.println("booked successfully");
							}
								
								
								
							
							else
							
							{
								int details=0;
								PreparedStatement cardset = conn.prepareStatement("select * from card_details where user_name=?");
								cardset.setString(1,username);
								ResultSet cardrow=cardset.executeQuery();
								while(cardrow.next()){
									details++;
								}
								if(details>0){
									int no_of_economy_seats=0;
									
									PreparedStatement pst = conn.prepareStatement("insert into booked_flights(user_name,flight_id,no_of_economy_seats,no_of_business_seats) values(?,?,?,?)");
									pst.setString(1, username);
									pst.setInt(2, flight_id);
									pst.setInt(3, no_of_economy_seats);
									pst.setInt(4, seats);
									int flag = pst.executeUpdate();
									
									PreparedStatement lastid = conn.prepareStatement("SELECT LAST_INSERT_ID()");
									ResultSet bookingidlsit = lastid.executeQuery();
									int booking_id = 0;
									while(bookingidlsit.next()){
										booking_id = bookingidlsit.getInt(1);
									}
									
									PreparedStatement emailid = conn.prepareStatement("SELECT emailid from registered_user where username=?");
									emailid.setString(1, username);
									ResultSet emailidlist = emailid.executeQuery();
									String bookedemailid = null;
									while(emailidlist.next()){
										bookedemailid = emailidlist.getString(1);
									}
									
									PreparedStatement event = conn.prepareStatement("select fd.date_of_departure from flight_details fd inner join booked_flights bf on fd.flight_id = bf.flight_id where bf.booking_id=?");
									event.setInt(1, booking_id);
						            ResultSet date=event.executeQuery();
						            Date datevalue = null;
									while(date.next()){
										datevalue = date.getDate(1);
									}
									
									String event_notification_string = "event_notification" + booking_id + username;
						            int i=pst .executeUpdate();
									PreparedStatement preparredevent = conn.prepareStatement("CREATE EVENT IF NOT EXISTS ? ON SCHEDULE AT ? - interval 1 day DO INSERT INTO email_notification(booking_id,email) VALUES(?,?)");
									preparredevent.setString(1, event_notification_string);
									preparredevent.setDate(2, datevalue);
									preparredevent.setInt(3, booking_id);
									preparredevent.setString(4, bookedemailid);
									int flagevent= pst.executeUpdate();
									
									PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET business_seat_count = ? where flight_id=?");
									insert.setInt(1, reduce-seats);
									insert.setInt(2, flight_id);
									int result=insert.executeUpdate();
									out.println("booked successfully");
								}
								else
									out.println("card details have not been saved previously" );
							}
							
							
							
						}

						else{
							out.println("entered seats are higher than availabe seats");}
					
					
				}
				}
				
				
		}
			catch(Exception e){
				e.printStackTrace();}
			}
		else if (action.equals("editbookdetails"))
		{
			PrintWriter out = response.getWriter();
			int meal_id = 0;
			String username=request.getParameter("username");
			System.out.println(username);
			
			bookingid=request.getParameter("bookingid");
			System.out.println("bookingid" +bookingid);
			
			String s=request.getParameter("flight_id");System.out.println(s);
			int flight_id = Integer.parseInt(request.getParameter("flight_id"));
			System.out.println(flight_id);
			int seats = Integer.parseInt(request.getParameter("seats"));
			String seat_type = request.getParameter("seat_type");
			String meal_plan = request.getParameter("meal_plan");
			String payment_method=request.getParameter("payment_method");
			String save = request.getParameter("save");
			String card = request.getParameter("card");
			
			Connection conn = (Connection) DbConnection.getConnection();
			try {
				PreparedStatement flightset = conn.prepareStatement("select economy_seat_count,business_seat_count from flight_details where flight_id=?");
				flightset.setInt(1, flight_id);
				ResultSet flightseat=flightset.executeQuery();
				
				
				
				PreparedStatement mealset = conn.prepareStatement("select * from meal_plan where meal_name=?");
				System.out.println(meal_plan);
				mealset.setString(1, meal_plan);
				ResultSet meal=mealset.executeQuery();
				while(meal.next()){
					
					 meal_id=meal.getInt(1);
				}
				System.out.println(meal_id);
				if(seat_type.equals("economy")){
				PreparedStatement seatset = conn.prepareStatement("select economy_seat_count from flight_details where flight_id=?");
				seatset.setInt(1, flight_id);
				ResultSet seatcount=seatset.executeQuery();
				while(seatcount.next()){int reduce=seatcount.getInt(1);
					if(seats<seatcount.getInt(1)){
						
						if(card.equals("new")){
							String card_number = request.getParameter("card_number");
							String expiry_date = request.getParameter("expiry_date");
							String cvv = request.getParameter("cvv");
							String name_on_card = request.getParameter("name_on_card");
							String card_type = request.getParameter("card_type");

						if(save.equals("yes")){
							PreparedStatement carddetails = conn.prepareStatement("select * from card_details where card_no=?");
							carddetails.setString(1, card_number);
							ResultSet cardresultset=carddetails.executeQuery();
							if(cardresultset.next()){
								PreparedStatement cardset = conn.prepareStatement("update card_details set name_on_card=?, cvv=?, card_type=?, payment_method=? where card_no=?");
								
								cardset.setString(1, name_on_card);
								cardset.setString(2, cvv);
								cardset.setString(3, card_type);
								cardset.setString(4, payment_method);
								cardset.setString(5, card_number);
								int cardrow=cardset.executeUpdate();
							}
							else
							{
								PreparedStatement cardset = conn.prepareStatement("insert into card_details values(?,?,?,?,?,?)");
								cardset.setString(1, card_number);
								cardset.setString(2, name_on_card);
								cardset.setString(3, cvv);
								cardset.setString(4, card_type);
								cardset.setString(5, username);
								cardset.setString(6, payment_method);
								int cardrow=cardset.executeUpdate();
							}
							}
							
							
							PreparedStatement pst = conn.prepareStatement("update booked_flights set user_name = ?, no_of_economy_seats= ? where booking_id= ?");
							pst.setString(1, username);
							pst.setInt(2, seats);
							pst.setInt(3, Integer.parseInt(bookingid));
							int flag = pst.executeUpdate();
						
							PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET economy_seat_count = ? where flight_id=?");
							insert.setInt(1, reduce-seats);
							insert.setInt(2, flight_id);
							int result=insert.executeUpdate();
							out.println("booked successfully");
							
						
						}
							
							
							
						
						else
						
						{
							int details=0;
							PreparedStatement cardset = conn.prepareStatement("select * from card_details where user_name=?");
							cardset.setString(1,username);
							ResultSet cardrow=cardset.executeQuery();
							while(cardrow.next()){
								details++;
							}
							if(details>0){
								PreparedStatement pst = conn.prepareStatement("update booked_flights set user_name = ?, no_of_economy_seats= ? where booking_id= ?");
								pst.setString(1, username);
								pst.setInt(2, seats);
								pst.setInt(3, Integer.parseInt(bookingid));
								int flag = pst.executeUpdate();
								
									PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET economy_seat_count = ? where flight_id=?");
									insert.setInt(1, reduce-seats);
									insert.setInt(2, flight_id);
									int result=insert.executeUpdate();
									out.println("booked successfully");
								
							}
							else
								out.println("card details have not been saved previously" );
						}
						
						
						
					}
					else
						{out.println("entered seats are higher than availabe seats");}
				}
				}
				else
				{
					
					PreparedStatement seatset = conn.prepareStatement("select business_seat_count from flight_details where flight_id=?");
					seatset.setInt(1, flight_id);
					ResultSet seatcount=seatset.executeQuery();
					while(seatcount.next()){
						if(seats<seatcount.getInt(1)){int reduce=seatcount.getInt(1);
							if(card.equals("new")){
								String card_number = request.getParameter("card_number");
								String expiry_date = request.getParameter("expiry_date");
								String cvv = request.getParameter("cvv");
								String name_on_card = request.getParameter("name_on_card");
								String card_type = request.getParameter("card_type");

							if(save.equals("yes")){
								PreparedStatement carddetails = conn.prepareStatement("select * from card_details where card_no=?");
								carddetails.setString(1, card_number);
								ResultSet cardresultset=carddetails.executeQuery();
								if(cardresultset.next()){
									PreparedStatement cardset = conn.prepareStatement("update card_details set name_on_card=?, cvv=?, card_type=?, payment_method=? where card_no=?");
									
									cardset.setString(1, name_on_card);
									cardset.setString(2, cvv);
									cardset.setString(3, card_type);
									cardset.setString(4, payment_method);
									cardset.setString(5, card_number);
									int cardrow=cardset.executeUpdate();
								}
								else
								{
									PreparedStatement cardset = conn.prepareStatement("insert into card_details values(?,?,?,?,?,?)");
									cardset.setString(1, card_number);
									cardset.setString(2, name_on_card);
									cardset.setString(3, cvv);
									cardset.setString(4, card_type);
									cardset.setString(5, username);
									cardset.setString(6, payment_method);
									int cardrow=cardset.executeUpdate();
								}
								
								
							}
								
								
							PreparedStatement pst = conn.prepareStatement("update booked_flights set user_name = ?, no_of_business_seats= ? where booking_id= ?");
							pst.setString(1, username);
							pst.setInt(2, seats);
							pst.setInt(3, Integer.parseInt(bookingid));
							int flag = pst.executeUpdate();
								
								PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET business_seat_count = ? where flight_id=?");
								insert.setInt(1, reduce-seats);
								insert.setInt(2, flight_id);
								int result=insert.executeUpdate();
								out.println("booked successfully");
							}
								
								
								
							
							else
							
							{
								int details=0;
								PreparedStatement cardset = conn.prepareStatement("select * from card_details where user_name=?");
								cardset.setString(1,username);
								ResultSet cardrow=cardset.executeQuery();
								while(cardrow.next()){
									details++;
								}
								if(details>0){
									PreparedStatement pst = conn.prepareStatement("update booked_flights set user_name = ?, no_of_business_seats= ? where booking_id= ?");
									pst.setString(1, username);
									pst.setInt(2, seats);
									pst.setInt(3, Integer.parseInt(bookingid));
									int flag = pst.executeUpdate();
									
									
									
									PreparedStatement insert = conn.prepareStatement("UPDATE flight_details SET business_seat_count = ? where flight_id=?");
									insert.setInt(1, reduce-seats);
									insert.setInt(2, flight_id);
									int result=insert.executeUpdate();
									out.println("booked successfully");
								}
								else
									out.println("card details have not been saved previously" );
							}
							
							
							
						}

						else{
							out.println("entered seats are higher than availabe seats");}
					
					
				}
				}
				
				
		}
			catch(Exception e){
				e.printStackTrace();}
			
		}
		else if (action.equals("mybookingdetails") || action.equals("reschedulebooking"))
		{
			String session=request.getParameter("session");
			String username1=request.getParameter("username");
			System.out.println("session="+session);
			System.out.println("username="+username1);
			//String session = "sanju";
			//session.s("session", session);
			
			ArrayList<flight_details> boookedflights = new ArrayList<flight_details>();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/planmytravel", "root", "root");
				String username=request.getParameter("username");
				
				PreparedStatement flightdetails = conn.prepareStatement(
						"select * from flight_details fd inner join booked_flights bf on fd.flight_id = bf.flight_id inner join flight_info fi on fi.flight_number = fd.flight_number where user_name=?");
				flightdetails.setString(1,session );
				ResultSet flightset=flightdetails.executeQuery();
				while (flightset.next()) {
					flight_details f = new flight_details();
					f.setFlight_number(flightset.getString(2));
					PreparedStatement airlinesset = conn.prepareStatement(
							"select * from airlines where airlines_id=? ");
					airlinesset.setString(1, flightset.getString(19));
					ResultSet airlinesname=airlinesset.executeQuery();
					while(airlinesname.next()){
						f.setFlight_name(airlinesname.getString(2));	
					}
					f.setFlight_id(flightset.getInt(1));
					
					PreparedStatement sourcecity = conn.prepareStatement(
							"select * from cities where city_id=? ");
					sourcecity.setString(1, flightset.getString(9));
					ResultSet sourcename=sourcecity.executeQuery();
					while(sourcename.next()){
						f.setSource(sourcename.getString(2));	
					}
					
					PreparedStatement destcity = conn.prepareStatement(
							"select * from cities where city_id=? ");
					destcity.setString(1, flightset.getString(10));
					ResultSet destname=destcity.executeQuery();
					while(destname.next()){
						f.setDestination(destname.getString(2));	
					}
					
					f.setArrival(flightset.getString(3));
					f.setDeparture(flightset.getString(4));
					f.setDate_of_arrival(flightset.getString(5));
					f.setDate_of_departure(flightset.getString(6));
					//f.setDate_of_operation(rs.getDate(7));
					f.setEconomy_seat_count(flightset.getInt(7));
					f.setBusiness_seat_count(flightset.getInt(8));
					f.setbooking_id(flightset.getInt(11));
					boookedflights.add(f);

				}
				
				System.out.println(boookedflights.size());
				if(boookedflights.size()!=0){
					request.setAttribute("boookedflights", boookedflights);
					request.setAttribute("session", session);
					if (action.equals("reschedulebooking"))
					{
						request.getRequestDispatcher("reschedulebooking.jsp").forward(request, response);
					}
					else
						request.getRequestDispatcher("mybookings.jsp").forward(request, response);
					}
				else
					System.out.println("No booked flights available");
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if (action.equals("reschedulebooking"))
		{
			
		}
	}

}

package com.uncc.planmytravel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.sound.AiffFileReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vipul on 10/22/2016.
 */
public class AddFlightDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
System.out.println(1);
        String flightname = request.getParameter("flightname");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String dateofdeparture = request.getParameter("dateofdeparture");
        String dateofarrival = request.getParameter("dateofarrival");
        String arrival = request.getParameter("arrival");
        String departure = request.getParameter("departure");
        String numberofeconomyseats = request.getParameter("numberofeconomyseats");
        String numberofbusinessseats = request.getParameter("numberofbusinessseats");
        String flightnumber=request.getParameter("flightnumber");
        

        try {
        	SimpleDateFormat availDate = new SimpleDateFormat("dd/MM/yyyy");
    		Date chosenDepartureDate = availDate.parse(dateofdeparture);
    		System.out.println(chosenDepartureDate);
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    		String format = formatter.format(chosenDepartureDate);
    		System.out.println(format);
    		
    		Date chosenArrivalDate = availDate.parse(dateofarrival);
    		System.out.println(chosenArrivalDate);
    		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
    		String format1 = formatter1.format(chosenArrivalDate);
    		System.out.println(format1);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/planmytravel", "root", "root");
            
            //select airlines_id from airlines
            //where airlines_name = 'american airlines'

            PreparedStatement pstAirtlinesId = conn.prepareStatement("select airlines_id from airlines where airlines_name = ?");
            pstAirtlinesId.setString(1, flightname);
            ResultSet rs = pstAirtlinesId.executeQuery();
            int flightId = -1;
			while (rs.next()) {
				flightId = rs.getInt(1);
			}
            System.out.println("sourec = " +source + "dest = " + destination);
			PreparedStatement pstsourceId = conn.prepareStatement("select city_id from cities where city_name = ?");
			pstsourceId.setString(1, source);
			ResultSet rs1 = pstsourceId.executeQuery();
            int sourceId = -1;
			while (rs1.next()) {
				sourceId = rs1.getInt(1);
			}
			
			PreparedStatement pstdestinationId = conn.prepareStatement("select city_id from cities where city_name = ?");
			pstdestinationId.setString(1, destination);
			ResultSet rs2 = pstdestinationId.executeQuery();
            int destinationId = -1;
			while (rs2.next()) {
				destinationId = rs2.getInt(1);
			}
				
			System.out.println("airlines_id =" + flightId + " source = " + sourceId + " destination = " +  destinationId);
            PreparedStatement pst = conn.prepareStatement("insert into flight_details(flight_number,business_seat_count,source_id,destination_id,arrival_time, departure_time, date_of_departure, date_of_arrival, economy_seat_count) values (?,?,?,?,?,?,?,?,?)");
            pst.setString(1, flightnumber);
            pst.setString(2, numberofbusinessseats);
            pst.setInt(3, sourceId);
            pst.setInt(4, destinationId);
            pst.setString(5, arrival);
            pst.setString(6, departure);
            pst.setString(7, format);
            pst.setString(8, format1);
            pst.setString(9, numberofeconomyseats);

            int i=pst .executeUpdate();

            if(i==1)
            	out.println("successfully added flight details");
            else
            	out.println("There was an error in adding flight details");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//}
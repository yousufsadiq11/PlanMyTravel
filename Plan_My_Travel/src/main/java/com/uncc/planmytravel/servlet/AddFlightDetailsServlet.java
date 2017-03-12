package main.java.com.uncc.planmytravel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by vipul on 10/22/2016.
 */
public class AddFlightDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String flightname = request.getParameter("flightname");
        String source = request.getParameter("selectsource");
        String destination = request.getParameter("selectdestination");
        String dateofoperation = request.getParameter("dateofoperation");
        String arrival = request.getParameter("arrival");
        String departure = request.getParameter("departure");
        String numberofeconomyseats = request.getParameter("numberofeconomyseats");
        String numberofbusinessseats = request.getParameter("numberofbusinessseats");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan", "root", "1234");
            PreparedStatement pst = conn.prepareStatement("insert into flight_details(flight_name,source,destination,arrival, departure, date_of_operation,econmy_seat_count,business_seat_count) values (?,?,?,?,?,?,?,?)");
            pst.setString(1, flightname);
            pst.setString(2, source);
            pst.setString(3, destination);
            pst.setString(4, arrival);
            pst.setString(5, departure);
            pst.setString(6, dateofoperation);
            pst.setString(7, numberofeconomyseats);
            pst.setString(8, numberofbusinessseats);
            pst .executeUpdate();

            response.sendRedirect("addflightdetailssuccess.jsp");
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
//}

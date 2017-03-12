package main.java.com.uncc.planmytravel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Db.DbConnection;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String flight_number=request.getParameter("flight_number");
		String date_of_arrival=request.getParameter("date_of_arrival");
		String arrival_time=request.getParameter("arrival_time");
		if(action.equals("delete")){
			//DbConnection db=new DbConnection();
			Connection conn=(Connection) DbConnection.getConnection();try{
			PreparedStatement pst = conn.prepareStatement("delete from flight_details where flight_number=? and date_of_arrival=? and arrival_time=?");
			pst.setString(1, flight_number);
			pst.setString(2, date_of_arrival);
			pst.setString(3, arrival_time);
			int flag=pst.executeUpdate();
			conn.close();
			if(flag!=0){
				out.println("success");
			}
			else
				out.println("details does not exist");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}

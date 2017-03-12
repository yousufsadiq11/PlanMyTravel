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
 * Servlet implementation class AdminAddMealPlan
 */
@WebServlet("/AdminAddMealPlan")
public class AdminAddMealPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAddMealPlan() {
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
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String meal_name = request.getParameter("meal_name");
		if (action.equals("add_meal"))

		{

			Connection conn = (Connection) DbConnection.getConnection();
			try {

				PreparedStatement pst = conn.prepareStatement("insert into meal_plan(meal_name) values(?)");
				pst.setString(1, meal_name);
				int flag = pst.executeUpdate();
				conn.close();
				if (flag != 0)
					out.println("successful addition of meal plan");

				else
					out.println("Meal plan already exists");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}

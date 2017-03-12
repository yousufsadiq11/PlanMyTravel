package com.uncc.planmytravel.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

public class AdminLoginPageServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String adminUsername = request.getParameter("username");
        String adminPassword = request.getParameter("password");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plan", "root", "1234");
                PreparedStatement pst = conn.prepareStatement("select * from admin where username=? and password=?");
                pst.setString(1, adminUsername);
                pst.setString(2, adminPassword);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                	 response.sendRedirect("adminhome.jsp");
                   // response.sendRedirect("addflightdetails.jsp");
                }
                else {
                    response.sendRedirect("adminloginunsuccessful.jsp");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        }

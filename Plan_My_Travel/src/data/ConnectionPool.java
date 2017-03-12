package data;

import java.sql.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static Connection connection = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            //connection = (Connection) ic.lookup("java:/comp/env/jdbc/planmytravel");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/planmytravel", "root", "root");
            //	dataSource = (DataSource) ic.lookup("jdbc:mysql://localhost:3306/planmytravel");
        } catch (NamingException e) {
            System.out.println(e);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        return connection;
        
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
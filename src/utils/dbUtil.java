package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dbUtil {

	private static final String URL="jdbc:mysql://localhost:3306/trainteam?useSSL=false&serverTimezone=UTC";
	private static final String USER="root";
    private static final String PASSWORD="root";
    private static Connection conn=null;
	static
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
}

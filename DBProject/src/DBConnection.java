import java.sql.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;
//************************************************************************************
//DBConection.java		Created By: Larry Gaitan-Rodriguez	Date: 11/24/2020
//
//Connects to the application's database. Makes use of the ReadDBProperties.java file.
//************************************************************************************
import java.util.concurrent.Executor;

public class DBConnection {
	
	//Assigns the properties values to global variables
	private static String url = ReadDBProperties.getURL();
	private static String userName = ReadDBProperties.getUsername();
	private static String password = ReadDBProperties.getPassword();
	private static Connection conn;
	
	public static Connection connectToDB(){
		try {
			System.out.println(url);
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Successfully connected to the DB! :D");
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Failed to create the database connection! D:"); 
		}
		return conn;
	}
	

}

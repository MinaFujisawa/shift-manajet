package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DatabaseConnection {
	
	private static Connection connection;
	
	public static void createConnection(){
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public static Connection getConnection(){
		if(connection == null){
			try {
				
				Properties prop = new Properties();
				InputStream input =  new FileInputStream("resources/config.properties");
				
				prop.load(input);
				
			    connection =
			       DriverManager.getConnection("jdbc:mysql://"+ prop.getProperty("dbhost") +"/"
			       		+ prop.getProperty("database") + "?" 
			    		+ "user=" + prop.getProperty("dbuser")
			       		+ "&password="+ prop.getProperty("dbpassword")
			       		+ "&autoReconnect=true&useSSL=false");
			} catch (SQLException ex) {
			    ex.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	/*
	 * Execute the query passed as a parameter. If invalid query, it will 
	 * return null end print exception on the console. When the query is valid
	 * it will return the ResultSet.
	*/
	public static ResultSet executeQuery(String query){
		try {
			return getConnection().createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int executeUpdate(String query){
		try {
			return getConnection().createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}

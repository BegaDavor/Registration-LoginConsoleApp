package bega.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	// instanca klase
	private static ConnectionManager instance = null;
	
	// moje postavke za MySQL server
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "toor";
	
	// database url string
	
	private static final String CONN_STRING = "jdbc:mysql://127.0.0.1:3306/users?useSSL=false&serverTimezone=UTC";
	
	// connection object
	
	private Connection connection = null;
	
	// private konstruktor
	
	private ConnectionManager() {
		
	}
	
	// getInstance metoda
	
	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	// openConnection metoda
	
	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch(SQLException e) {
			System.err.println(e);
			return false;
		}
	}
	
	// getConnection metoda
	
	public Connection getConnection() {
		if(connection == null) {
			if (openConnection()) {
				return connection;
			}else {
				return null;
			}
		}
		return connection;
	}
	
	// close() metoda
	
	public void close() {
		try {
			connection.close();
			connection = null;
		}catch (Exception e) {
			System.err.println(e);
		}
	}

}

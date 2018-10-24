package bega.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bega.DTO.Account;

public class AccountDAOImplementation implements AccountDAOInterface{
	
	// konekcija na bazu podataka
	Connection connection = ConnectionManager.getInstance().getConnection();

	@Override
	public Account getAccountByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		
		Account account = null;
		
		String query = "SELECT * FROM account WHERE username = ?";
		
		ResultSet rs = null;
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, username);
			
			rs = statement.executeQuery();
			
			if(rs.next()) {
				account = new Account(rs.getInt("userID"), 
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("email"), 
						rs.getString("username"), 
						rs.getString("password"));
				rs.close();
			}
		}
		
		return account;
	}

	@Override
	public void updateAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		if(account != null) {
			String query = "UPDATE account SET firstname = ?, lastname = ?, email = ?, username = ?, password = ? WHERE userID = ?";
			try (PreparedStatement statement = connection.prepareStatement(query);){
				statement.setString(1, account.getFirstname());
				statement.setString(2, account.getLastname());
				statement.setString(3, account.getEmail());
				statement.setString(4, account.getUsername());
				statement.setString(5, account.getPassword());
				statement.setInt(6, account.getUserID());
				
				statement.executeUpdate();
				
				System.out.println("Account is up to date!");
			}
		}
		
	}

	@Override
	public void deleteAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		if(account != null) {
		String query = "DELETE FROM account WHERE userID = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
				statement.setInt(1, account.getUserID());
				
				statement.executeUpdate();
				
				System.out.println("Account is deleted!");
			}
		}
		
	}

	@Override
	public void addAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO account (firstname, lastname, email, username, password) VALUES (?, ?, ?, ?, ?)";
		
		try(PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, account.getFirstname());
			statement.setString(2, account.getLastname());
			statement.setString(3, account.getEmail());
			statement.setString(4, account.getUsername());
			statement.setString(5, account.getPassword());
			
			statement.executeUpdate();
			
			System.out.println("Account is added!");
		}
		
	}
	
	

}

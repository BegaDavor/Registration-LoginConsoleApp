package bega.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import bega.DTO.Account;

public interface AccountDAOInterface {
	
	// vraca konkretan account
	
	public Account getAccountByUsername(String username) throws SQLException;
	
	// vrsi promjene u account-u
	
	public void updateAccount(Account account) throws SQLException;
	
	// brise konkretan account
	
	public void deleteAccount(Account account) throws SQLException;
	
	// dodaje account
	
	public void addAccount(Account account) throws SQLException;

}

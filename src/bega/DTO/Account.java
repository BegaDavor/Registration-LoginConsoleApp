package bega.DTO;

public class Account {
	
	// atributi
	
	private int userID;
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	
	// kostruktori
	
	public Account(int userID, String firstname, String lastname, String email, String username, String password) {
		this.userID = userID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public Account(String firstname, String lastname, String email, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	// getters & setters
	
	public int getUserID() {
		return userID;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userID: " + getUserID() + 
				"\nFirst name: " + getFirstname() +
				"\nLast name " + getLastname() +
				"\nEmail: " + getEmail() +
				"\nUsername: " + getUsername() + "\n";
	}
	
}

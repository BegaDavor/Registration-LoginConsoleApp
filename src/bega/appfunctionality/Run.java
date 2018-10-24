package bega.appfunctionality;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import bega.DTO.Account;

public class Run {
	
	static Scanner input = new Scanner(System.in);
	public static int isInteger() { // handle exception za integer brojeve
		while (true) {
			try {
				return input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Your entry is wrong. Try again! ");
			}
		}
	}
	
	public static void run() throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {
		
		System.out.println("1 - Sign in \n"
				+ "2 - Sign up" + "\n0 - Exit");
		int option = isInteger();
		
		
		switch (option) {
		case 1:
			boolean loged = Login.login();
			
			if(loged)
				Login.options();
			break;
			
		case 2: 
			Registration.register();
			break;
		default:
			System.out.println("That option is not available! Try again!");
			run();
			break;
		}
		
		input.close();
		} 

}

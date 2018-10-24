package bega.appfunctionality;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import bega.DAO.AccountDAOImplementation;
import bega.DTO.Account;

public class Login {
	
	static Account account = null;
	
	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public static String encrypt(String key, String initVector, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static boolean login() throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Username: ");
		String username = input.next();
		System.out.println("Password: ");
		String password = input.next();
		
		AccountDAOImplementation login = new AccountDAOImplementation();
		
		account = login.getAccountByUsername(username);
		
		
		if(decrypt("Bar12345Bar12345", "RandomInitVector", account.getPassword()).equals(password))
			return true;
		else {
			
			System.out.println("Unijeli ste pogresan password ili username!");
			return false;
			
		}
	}

	public static void options() throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {

			Scanner input = new Scanner(System.in);

			System.out.println("1 - Account info \n" + "2 - Edit firstname \n" + "3 - Edit lastname \n"
					+ "4 - Change password \n" + "5 - Delete account \n" + "0 - Log out");

			int key = -1;

			try {
				key = input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println(e.getMessage());
			}
			
			switch (key) {
			case 0:
				Run.run();
				break;

			case 1:
				System.out.println(account.toString());
				break;

			case 2:
				System.out.println("Enter new first name: ");
				String newFirstName = input.next();
				account.setFirstname(newFirstName);
				AccountDAOImplementation impl = new AccountDAOImplementation();
				impl.updateAccount(account);
				break;
				
			case 3:
				System.out.println("Enter new last name: ");
				String newLastName = input.next();
				account.setLastname(newLastName);
				AccountDAOImplementation impl1 = new AccountDAOImplementation();
				impl1.updateAccount(account);
				break;
			
			case 4:
				System.out.println("Enter old password: ");
				String oldPassword = input.next();
				
				if (oldPassword.equals(decrypt("Bar12345Bar12345", "RandomInitVector", account.getPassword()))) {
					System.out.println("Enter new password: ");
					String newPassword = input.next();
					account.setPassword(encrypt("Bar12345Bar12345", "RandomInitVector", newPassword));
					AccountDAOImplementation impl2 = new AccountDAOImplementation();
					impl2.updateAccount(account);
				}else {
					System.out.println("You missed old password!");
				}
				break;
			case 5: 
				System.out.println("Are you sure that you want to delete account? (1 - yes, 0 - no) ");
				int validation = -1;
				try {
					validation = input.nextInt();
				} catch (InputMismatchException e) {
					input.next();
					System.out.println(e.getMessage());
				}
				
				if(validation == 1) {
					AccountDAOImplementation impl3 = new AccountDAOImplementation();
					impl3.deleteAccount(account);
				}
				break;
			}
			input.close();
		
	}

}

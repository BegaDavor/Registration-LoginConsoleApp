package bega.appfunctionality;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import bega.DAO.AccountDAOImplementation;
import bega.DTO.Account;

public class Registration {

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

	public static void register() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
		// new Scanner
		Scanner input = new Scanner(System.in);
		String email = null;
		String username = null;
		String password = null;
		System.out.println("Welcome! \n * - must enter");
		System.out.println("First name: ");
		String firstname = input.next();
		System.out.println("Last name: ");
		String lastname = input.next();
		do {
			System.out.println("E-mail*: ");
			email = input.next();
		} while (email == null);
		do {
			System.out.println("Username*: ");
			username = input.next();
		} while (username == null);
		do {
			System.out.println("Password*: ");
			password = input.next();
		} while (password == null);

		String hashedPassword = encrypt("Bar12345Bar12345", "RandomInitVector", password);
		Account account = new Account(firstname, lastname, email, username, hashedPassword);

		AccountDAOImplementation reg = new AccountDAOImplementation();
		reg.addAccount(account);
	}

}

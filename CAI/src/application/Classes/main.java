package application.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class main {
	private static User loggedInUser;
	
	
	 
	
	public static void main(String[] args) {
		/*
		Scanner scanner = new Scanner(System.in);
		String username;
		String password;
		
		while (true) {
			
		
			do  {
				
				
				System.out.println("Please log in: ");
				System.out.println("Username: ");
			    
				username = scanner.nextLine();
				
				System.out.println("Password: ");
				password = scanner.nextLine();
				
				if ((main.loggedInUser = User.login(username, password)) == null) {
					System.out.println("Not a valid user.");
				}
			} while(main.loggedInUser == null);
		
			main.loggedInUser.interfacePrompt(scanner);
			main.loggedInUser.logout();
		}
		*/
		ArrayList<User> users = DatabaseReference.getUsersFromDatabase();
		System.out.println(users.size());
		//System.out.println(users.get(0).get);
	}
}



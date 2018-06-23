package application.Classes;

import java.util.*;

public abstract class User {
	private static ArrayList<User> users;
 	private boolean authenticated;
	private String username;
	private String password;
	private int id;
	private static int idCount = 1;
	
	public User(String username, String password, int id) {
		this.authenticated = false;
		this.username = username;
		this.password = password;
		this.id = id;
		User.idCount++;
	}
	
	public boolean isUserWithCredentials(String username, String password) {
		if (this.username.equals(username) && this.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	public static User login(String username, String password) {
		if (User.users == null) {
			User.getUsersFromDatabase();
		}
		
		for (User user: User.users) {
			if (user.isUserWithCredentials(username, password)) {
				user.authenticated = true;
				return user;
			}
		}
		return null;
	}
	
	public void logout() {
		this.authenticated = false;
		return;
	}
	
	
	private static boolean getUsersFromDatabase() {
		User.users = DatabaseReference.getUsersFromDatabase();
		return true;
	}
	
	public static ArrayList<Employee> getEmployees() {
		if (User.users == null) {
			User.getUsersFromDatabase();
		}
		
		ArrayList<Employee> employees = new ArrayList<>();
		for (User user: User.users) {
			if (user instanceof Employee) {
				employees.add((Employee)user);
			}
		}
		return employees;
	}
	
	
	public abstract void interfacePrompt(Scanner scanner);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
} 




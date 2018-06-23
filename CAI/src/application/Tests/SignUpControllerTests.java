package application.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import application.SignUpController;
import application.Classes.DatabaseReference;
import application.Classes.*;



public class SignUpControllerTests {

	@Test
	public void testIfClickingSignUpAddsUserToDatabase() throws IOException {
		ArrayList<User> users = DatabaseReference.getUsersFromDatabase();
	
		SignUpController signUpController = new SignUpController();
		signUpController.signUpUser("aaa", "bbbb");
		
		assertEquals(DatabaseReference.getUsersFromDatabase().size(), users.size()+1);
		
	}
	
	
}

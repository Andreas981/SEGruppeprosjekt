package View;

import Dummy.Database;

import java.util.Scanner;

public class RegisterUserView {
    // Anyone can create a customer account
    // For creating a organizer, an administrator account must be used.

    public String createNewUser(){
        // Ask for username:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a desired username:");
        try {
            String userName = scanner.next();
        }catch (Exception e){
            System.out.println("Please enter a username:");
        }
        // Search through "database" to see if the already exist a user with current name

        return "Successfully register user:"
    }
}

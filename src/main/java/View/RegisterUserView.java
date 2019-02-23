package View;

import Dummy.Database;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RegisterUserView {
    // Anyone can create a customer account
    // For creating a organizer, an administrator account must be used.

    public String createNewUser(){
        boolean inputValidation = true;
        // Ask for username:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a desired username:");
        try {
            String userName = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a username:");
            inputValidation = false;
        }
        // Make call to controller to search for user name
        // Search through "database" to see if the already exist a user with current name
        System.out.println("Enter your first name:");
        try {
            String firstName = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a valid first name:");
            inputValidation = false;
        }
        System.out.println("Enter your last name:");
        try {
            String lastName = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a valid first name:");
            inputValidation = false;
        }

        System.out.println("Enter your email:");
        try {
            String mail = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a valid first name:");
            inputValidation = false;
        }
        System.out.println("Please enter your phone number");
        try {
            String telephone = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a valid first name:");
            inputValidation = false;
        }
        // TODO Validate password
        System.out.println("Please set a desired password");
        try {
            String password = scanner.next();
        }catch (StringIndexOutOfBoundsException s){
            System.out.println("Please enter a valid first name:");
            inputValidation = false;
        }
        System.out.println("Please type in your date of birth:");
        SimpleDateFormat birthDay = new SimpleDateFormat("dd-MMM-yyyy");
        String dateEntered = scanner.next();
        Date dateParsed=null;
        try {
            //Parsing the String
            dateParsed = birthDay.parse(dateEntered);
        } catch (ParseException e) {
            e.printStackTrace();
            inputValidation = false;

        }

        if(!inputValidation){
            return "Could not register user";
        }else {
            //Send all fields to controller
            return "Registering user";
        }
    }
}

/*
String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday
 */

package View;

import Controller.RegisterUserController;
import Dummy.Database;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RegisterUserView {
    public String createNewUser() {
        // Anyone can create a customer account
        // For creating a organizer, an administrator account must be used.
        Boolean inputValidation = true;
        while (inputValidation) {
            // Ask for username:
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a desired username:");
            String userName;
            try {
                // Make call to controller to search for user name
                // Search through "database" to see if the already exist a user with current name
                userName = scanner.next();
                inputValidation = RegisterUserController.userNameIsValid(userName);
                if(!inputValidation){
                    System.out.println("Username is already taken + \n " +
                            "Please try again");
                }
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid username:");
                break;

            }

            System.out.println("Enter your first name:");
            String firstName ="";
            try {
                 firstName = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                break;
            }
            System.out.println("Enter your last name:");
            String lastName;
            try {
               lastName = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                break;
            }

            System.out.println("Enter your email:");
            String mail;
            try {
                mail = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                break;
            }
            System.out.println("Please enter your phone number");
            String telephone;
            try {
                telephone = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                break;
            }
            // TODO Validate password
            System.out.println("Please set a desired password");
            String password;
            try {
                password = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                break;
            }
            System.out.println("Please type in your date of birth:");
            System.out.println("DD-MM-YYYY");
            String dateEntered = scanner.next();
            Date birthDay;
            try {
                 birthDay = RegisterUserController.parseInputDate(dateEntered);
                 if(birthDay.equals(null)){
                     break;
                 }
            }catch (Exception e){
                // TODO Proper exception method
                break;
            }

            if(inputValidation){
                RegisterUserController.registerUserIntoDatabase(firstName,lastName,mail,telephone
                        ,userName,password,birthDay );
                return "User registered";
            }
                //Send all fields to controller

        }
        return "Failed creating user";
    }
}

/*
String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday
 */

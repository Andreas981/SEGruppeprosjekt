package View;

import Controller.RegisterUserController;
import Dummy.Database;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RegisterUserView {

    public RegisterUserView(){

    }

    public void displayPromptForUserName() {
        System.out.println("Please enter a desired username:");
    }

    public void displayErrorToUser(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void displayPromptForUserFirstname() {
        System.out.println("Please enter your firstname:");
    }

    public void displayPromptForUserLastname() {
        System.out.println("Please enter your lastname:");
    }

    public void displayPromptForUserEmail() {
        System.out.println("Please enter your email:");
    }

    public void displayPromptForUserPhoneNumber() {
        System.out.println("Please enter a valid phone number:");
    }

    public void displayPromptForUserPassword() {
        System.out.println("Please type in a valid password:");
    }

    public void displayPromptForBirthdate(){
        System.out.println("Please enter a valid birth date. \n" +
                "In format: DD-MM-YYYY");
    }

}
    /*
    public String createNewUser() {
        // Anyone can create a customer account
        // For creating a organizer, an administrator account must be used.
        Boolean inputValidation = true;
        while (inputValidation) {
            // Ask for username:
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a desired username:");
            String userName = scanner.next();
                // Make call to controller to search for user name
                // Search through "database" to see if the already exist a user with current name
                if(RegisterUserController.userNameIsValid(userName)){

                }else {
                    System.out.println("Username is already taken + \n " +
                            "Please try again");
                    createNewUser();
                }
            System.out.println("Enter your first name:");
            String firstName;
            try {
                 firstName = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                createNewUser();
                break;
            }
            System.out.println("Enter your last name:");
            String lastName;
            try {
               lastName = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                createNewUser();
                break;
            }
            System.out.println("Enter your email:");
            String mail;
            try {
                mail = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                createNewUser();
                break;
            }
            System.out.println("Please enter your phone number");
            String telephone;
            try {
                telephone = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                createNewUser();
                break;
            }
            // TODO Validate password
            System.out.println("Please set a desired password");
            String password;
            try {
                password = scanner.next();
            } catch (StringIndexOutOfBoundsException s) {
                System.out.println("Please enter a valid first name:");
                createNewUser();
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
            //Send all fields to controller
            if(inputValidation){
                RegisterUserController.registerUserIntoDatabase(firstName,lastName,mail,telephone
                        ,userName,password,birthDay );
                return "User registered";
            }
            inputValidation = false;

        }
        return "Failed creating user";
    }
}

/*
String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday
 */

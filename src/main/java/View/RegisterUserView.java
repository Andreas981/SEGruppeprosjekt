package View;

import Controller.RegisterUserController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Dummy.Database;
import Model.Customer;
import Model.Organizer;
import View.RegisterUserView;
import org.joda.time.LocalDate;

public class RegisterUserView {
    private Scanner scanner;
    RegisterUserController registerUserController;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
    private LocalDate userBirthDay;
    private String organization;
    private int accessLevel;

    public RegisterUserView(){
        new RegisterUserController();
    }

    public void displayPromptForUserName() {
        System.out.println("Please enter a desired username:");
        userName = scanner.next();
        if (registerUserController.checkUsername(userName)) displayPromptForUserFirstname();
        else{
            System.out.println("Invalid username selected. Please try again");
            displayPromptForUserName();
        }
    }

    public void displayPromptForUserFirstname() {
        System.out.println("Please enter your firstname:");
        userFirstName = scanner.next();
        if(registerUserController.checkFirstname(userFirstName)) displayPromptForUserLastname();
        else{
            System.out.println("Invalid first name detected. Please try again");
            displayPromptForUserFirstname();
        }
    }

    public void displayPromptForUserLastname() {
        System.out.println("Please enter your lastname:");
        userLastName = scanner.next();
        if(registerUserController.checkLastName(userLastName)) displayPromptForUserEmail();
        else{
            System.out.println("Invalid last name detected. Please try again");
            displayPromptForUserLastname();
        }
    }

    public void displayPromptForUserEmail() {
        System.out.println("Please enter your email:");
        userEmail = scanner.next();

    }

    public void displayPromptForUserPhoneNumber() {
        System.out.println("Please enter a valid phone number:");
        userPhoneNumber = scanner.next();
    }

    public void displayPromptForUserPassword() {
        System.out.println("Please type in a valid password:");
        userPassword = scanner.next();
    }

    public void displayPromptForBirthdate(){
        System.out.println("Please enter a valid birth date. \n" +
                "In format: YYYY-MM-DD");
        String birthDayEntered = scanner.next();
    }
    public void displayErrorToUser(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void displayUserRegistered(){
        System.out.println("User registered successfully");
    }

    public void displayOranizationAffiloaton(){
        System.out.println("Organization affiliation?");;
    }

    private void askForAccessLevel() {
        System.out.println("Access level 1 or 2?");
        int level = 0;
        try{
            level = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            askForAccessLevel();
        }

        accessLevel = level;
        if(accessLevel>0 && accessLevel <3){
        }else{
            askForAccessLevel();
        }
    }

}

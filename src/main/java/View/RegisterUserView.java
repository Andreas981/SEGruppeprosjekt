package View;

import Controller.RegisterUserController;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.joda.time.LocalDate;

public class RegisterUserView {
    private Scanner scanner = new Scanner(System.in);
    RegisterUserController registerUserController = new RegisterUserController();
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
    private LocalDate userBirthDay;
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
        if(registerUserController.checkEmail(userEmail)) displayPromptForUserPhoneNumber();
        else {
            System.out.println("Invalid email. Please try again");
            displayPromptForUserEmail();
        }

    }

    public void displayPromptForUserPhoneNumber() {
        System.out.println("Please enter a valid phone number:");
        userPhoneNumber = scanner.next();
        if(registerUserController.checkPhoneNumber(userPhoneNumber)) displayPromptForUserPassword();
        else{
            System.out.println("Incorrect phone number length..");
            displayPromptForUserPhoneNumber();
        }
    }

    public void displayPromptForUserPassword() {
        System.out.println("Please type in a valid password:");
        userPassword = scanner.next();
        if(registerUserController.checkPasswordStrength(userPassword)) displayPromptForBirthdate();
        else{
            System.out.println("Password length too short");
            displayPromptForUserPassword();
        }
    }

    public void displayPromptForBirthdate(){
        System.out.println("Please enter a valid birth date. \n" +
                "In format: YYYY-MM-DD");
        String birthDayEntered = scanner.next();
        System.out.println("Bday: " + birthDayEntered);
        userBirthDay = registerUserController.checkDateEntered(birthDayEntered);
        if(userBirthDay!=null) sendRegistrationToController();
        else{
            System.out.println("Invalid date entered");
            displayPromptForBirthdate();
        }
    }

    private void sendRegistrationToController() {
        if(registerUserController.registerCustomerIntoDatabase(userFirstName,userLastName,
                userEmail,userPhoneNumber,userName,
                userPassword,userBirthDay)) System.out.println("User registered");
        else{
            System.out.println("Error could not register user");
        }
    }

    // TODO Organizer registration
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

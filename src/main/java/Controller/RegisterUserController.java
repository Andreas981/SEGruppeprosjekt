package Controller;

import Dummy.Database;
import Model.Customer;
import Model.Organizer;
import View.RegisterUserView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegisterUserController {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
    private Date userBirthDay;
    private String organization;
    private int accessLevel;
    private RegisterUserView registerUserView;
    private Scanner scanner;
    private boolean isOrganizer = false;

    // Constructor for a customer
    public RegisterUserController(){
        registerUserView = new RegisterUserView();
        scanner = new Scanner(System.in);
    }

    // TODO Validation for a registered admin is logged in
    // Constructor for a organizer, can only be used by admin

    public RegisterUserController(boolean isOrganizer) {
        this.isOrganizer = isOrganizer;
    }

    public void startRegistrationForUser(){
        registerUserView.displayPromptForUserName();
        userName = scanner.next();
        if(userName.length()>5){
            if(isUserNameIsValid(userName)){
                askForFirstname();
            }else{
                registerUserView.displayErrorToUser("You have selected a invalid username");
                startRegistrationForUser();
            }
        }else{
            registerUserView.displayErrorToUser("Username must at least contain 5 characters");
            startRegistrationForUser();
        }
    }

    private void askForFirstname() {
        registerUserView.displayPromptForUserFirstname();
        userFirstName = scanner.next();
        if(userFirstName.length()>1){
            askForLastname();
        }else{
            registerUserView.displayErrorToUser("Please input a first name");
            askForFirstname();
        }
    }

    private void askForLastname() {
        registerUserView.displayPromptForUserLastname();
        userLastName = scanner.next();
        if(userLastName.length()>1){
            askForUserEmail();
        }else{
            registerUserView.displayErrorToUser("Please input a last name");
            askForLastname();
        }
    }

    private void askForUserEmail() {
        registerUserView.displayPromptForUserEmail();
        userEmail = scanner.next();
        if(userEmail.length()>1){
            if(mailIsValid(userEmail)){
                askForUserPhoneNumber();
            }else{
                registerUserView.displayErrorToUser("You already have a account registered with this email");
            }

        }else{
            registerUserView.displayErrorToUser("Please input a valid email");
        }
    }

    private void askForUserPhoneNumber() {
        registerUserView.displayPromptForUserPhoneNumber();
        userPhoneNumber = scanner.next();
        if(userPhoneNumber.length()>=8){
            askForUserBirthDate();
        }else{
            registerUserView.displayErrorToUser("Please input a valid phone number");
            askForUserPhoneNumber();
        }
    }

    private void askForUserBirthDate() {
        registerUserView.displayPromptForBirthdate();
        String birthDayEntered = scanner.next();
        if(tryParseInputDate(birthDayEntered)){
            userBirthDay = parseInputDate(birthDayEntered);
            askForUserPassword();
        }else{
            registerUserView.displayErrorToUser("Invalid date entered! \n" +
                    "Enter it like 01-02-1983");
            askForUserBirthDate();
        }
    }

    // TODO Password validation method
    private void askForUserPassword() {
        registerUserView.displayPromptForUserPassword();
        userPassword = scanner.next();
        if(userPassword.length()>=5){
            if(isOrganizer){
                askForOrganization();
            }else{
                if((registerCustomerIntoDatabase(userFirstName,userLastName,userEmail,userPhoneNumber,
                        userName,userPassword,userBirthDay))){
                    registerUserView.displayUserRegistered();
                }else{
                    registerUserView.displayErrorToUser("Database down?");
                }
            }

        }else{
            registerUserView.displayErrorToUser("Please input a stronger password");
            askForUserPassword();
        }
    }

    private void askForOrganization() {
        System.out.println("Organization affiliation?");;
        organization = scanner.next();
        if(organization.length()>1){
            askForAccessLevel();
        }else{
            registerUserView.displayErrorToUser("Please input a organization");
            askForOrganization();
        }
    }

    // TODO INTEGER parse validation
    private void askForAccessLevel() {
        System.out.println("Access level 1 or 2?");
        accessLevel = Integer.parseInt(scanner.next());
        if(accessLevel>0 && accessLevel <3){
            registerOrganizerIntoDatabase();
        }else{
            registerUserView.displayErrorToUser("Invalid level detected");
            askForAccessLevel();
        }
    }

    private Boolean isUserNameIsValid(String userName){
        // Search through database to see if userName exist
        for (int i = 0; i < Database.customers.size(); i++){
            if (Database.customers.get(i).getUsername().equals(userName)){
                return false;
            }
        }
        // User name is not taken
        return true;
    }

    private static Boolean mailIsValid(String mail){
        // Search through database to see if userName exist
        for (int i = 0; i < Database.customers.size(); i++){
            if (Database.customers.get(i).getMail().equals(mail)){
                return false;
            }
        }
        // User name is not taken
        return true;
    }

    private boolean tryParseInputDate(String dateEntered){
        SimpleDateFormat birthDay = new SimpleDateFormat("dd-MM-yyyy");
        Date dateParsed = null;
        try {
            //Parsing the String
            dateParsed = birthDay.parse(dateEntered);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date input! Should be Day-Month-Year");
            return false;
        }
        return true;
    }

    private Date parseInputDate(String dateEntered){
        SimpleDateFormat birthDay = new SimpleDateFormat("dd-MM-yyyy");
        Date dateParsed = null;
        try {
            //Parsing the String
            dateParsed = birthDay.parse(dateEntered);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date input! Should be Day-Month-Year");
            return null;
        }
        return dateParsed;
    }

    private boolean registerCustomerIntoDatabase(String firstName, String lastName, String mail
            , String telephone, String username, String password, Date birthday){
        try {
            Database.customers.add(new Customer(firstName,lastName,mail
                    ,telephone,username,Security.PassHash.hashPassword(password),birthday));
        }catch (Exception e){
            System.out.println("Could not add user to database");
            return false;
        }
        return true;
    }

    private boolean registerOrganizerIntoDatabase(){
        try {
            Database.organizers.add(new Organizer(userFirstName,userLastName,userEmail
                    ,userPhoneNumber,userName,Security.PassHash.hashPassword(userPassword),
                    userBirthDay,organization,accessLevel));
        }catch (Exception e){
            System.out.println("Could not add user to database");
            return false;
        }
        return true;
    }
}

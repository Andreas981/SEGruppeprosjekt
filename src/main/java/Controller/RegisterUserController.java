package Controller;

import Dummy.Database;
import Model.Customer;
import Model.Organizer;
import View.RegisterUserView;
import org.joda.time.LocalDate;

import java.util.InputMismatchException;


public class RegisterUserController {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhoneNumber;
    private String userPassword;
    private LocalDate userBirthDay;
    private String organization;
    private int accessLevel;
    private RegisterUserView registerUserView;

    private boolean isOrganizer = false;

    // Constructor for a customer
    public RegisterUserController(){

    }

    // TODO Validation for a registered admin is logged in
    // Constructor for a organizer, can only be used by admin
    public RegisterUserController(boolean isOrganizer) {
        this.isOrganizer = isOrganizer;
    }

    public boolean checkUsername(String userName){
        if(userName.length()>5){
            if(isUserNameIsValid(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean checkFirstname(String userFirstName) {
        if(userFirstName.length()>1){
            return true;
        }
        return false;
    }

    private void askForLastname() {
        registerUserView.displayPromptForUserLastname();

        if(userLastName.length()>1){
            askForUserEmail();
        }else{
            registerUserView.displayErrorToUser("Please input a last name");
            askForLastname();
        }
    }

    private void askForUserEmail() {
        registerUserView.displayPromptForUserEmail();

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

        if(userPhoneNumber.length()>=8){
            askForUserBirthDate();
        }else{
            registerUserView.displayErrorToUser("Please input a valid phone number");
            askForUserPhoneNumber();
        }
    }

    private void askForUserBirthDate() {
        registerUserView.displayPromptForBirthdate();

        if(tryParseInputDate(birthDayEntered)){
            userBirthDay = parseInputDate(birthDayEntered);
            askForUserPassword();
        }else{
            registerUserView.displayErrorToUser("Invalid date entered! \n" +
                    "Enter it like 1980-02-03");
            askForUserBirthDate();
        }
    }

    // TODO Password validation method
    private void askForUserPassword() {
        registerUserView.displayPromptForUserPassword();

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

    private void askForOrganization(String organization) {

        organization = scanner.next();
        if(organization.length()>1){
            askForAccessLevel();
        }else{
            registerUserView.displayErrorToUser("Please input a organization");
            askForOrganization();
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

    // See if the data entered is valid:
    private boolean tryParseInputDate(String dateInput) {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        return Security.RegEx.regEx(patternForDate, dateInput);
    }


    private LocalDate parseInputDate(String dateEntered){
        //Parsing the String
        return  LocalDate.parse(dateEntered);
    }

    private boolean registerCustomerIntoDatabase(String firstName, String lastName, String mail
            , String telephone, String username, String password, LocalDate birthday){
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

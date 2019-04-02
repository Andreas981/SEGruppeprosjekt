package Controller;

import Dummy.Database;
import Model.Customer;
import org.joda.time.LocalDate;

public class RegisterUserController {

    public boolean checkUsername(String userName){
        if(userName.length()>5){
            return isUserNameIsValid(userName);
        }
        return false;
    }

    public boolean checkFirstname(String userFirstName) {
        if(userFirstName.length()>1){
            return true;
        }
        return false;
    }

    public Boolean checkLastName(String userLastName) {
        return userLastName.length() > 1;
    }

    public Boolean checkEmail(String userEmail) {
        if (userEmail.length() > 1) return mailIsValid(userEmail);
        return false;
    }

    public Boolean checkPhoneNumber(String userPhoneNumber) {
        return userPhoneNumber.length() >= 8;
    }

    public LocalDate checkDateEntered(String birthDayEntered) {
        if(tryParseInputDate(birthDayEntered)){
            String[] birthDayArray = birthDayEntered.split("-");
            LocalDate birthDay = new LocalDate(Integer.parseInt(birthDayArray[0]), Integer.parseInt(birthDayArray[1]), Integer.parseInt(birthDayArray[2]));
            return  birthDay;
        }
        return null;
    }

    // TODO Password validation method
    public Boolean checkPasswordStrength(String userPassword) {
        return userPassword.length()>=5;
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

    private  Boolean mailIsValid(String mail){
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

    public boolean registerCustomerIntoDatabase(String firstName, String lastName, String mail
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


}

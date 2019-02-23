package Controller;

import Dummy.Database;
import Model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserController {

    public static Boolean userNameIsValid(String userName){
        // Search through database to see if userName exist
        for (int i = 0; i < Database.customers.size(); i++){
            if (Database.customers.get(i).getUsername().equals(userName)){
                return false;
            }
        }
        // User name is not taken
        return true;
    }

    public static Boolean mailIsValid(String mail){
        // Search through database to see if userName exist
        for (int i = 0; i < Database.customers.size(); i++){
            if (Database.customers.get(i).getMail().equals(mail)){
                return false;
            }
        }
        // User name is not taken
        return true;
    }

    public static Date parseInputDate(String dateEntered){
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



    public static boolean registerUserIntoDatabase(String firstName, String lastName, String mail
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
}

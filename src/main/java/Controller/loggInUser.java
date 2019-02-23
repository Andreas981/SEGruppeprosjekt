package Controller;

import Dummy.Database;
import Model.Organizer;
import Model.User;

import java.util.ArrayList;

public class loggInUser {

    public static Boolean login(int userRole, String username, String password){

        if(userRole == 1){
            //Login organizer
        }else if(userRole == 2){
            //Login customer
        }

        return false;
    }
}

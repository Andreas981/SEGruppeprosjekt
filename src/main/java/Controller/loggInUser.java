package Controller;

import Dummy.Database;
import Model.Organizer;
import Model.User;

import java.util.ArrayList;

public class loggInUser {

    public static Boolean login(int userRole, String username, String password){

        if(userRole == 1){
            //Login organizer
            int foundUsername = -1;
            for (int i = 0; i < Database.organizers.size(); i++){
                if (Database.organizers.get(i).getUsername().equals(username)){
                    foundUsername = i;
                    break;
                }
            }
        }else if(userRole == 2){
            //Login customer
        }

        return false;
    }
}

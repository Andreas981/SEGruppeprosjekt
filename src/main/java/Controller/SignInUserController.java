package Controller;

import Dummy.Database;

public class SignInUserController {

    public Boolean singIn(int userRole, String username, String password){

        if(userRole == 1){
            //Login organizer
            int foundUsername = -1;
            for (int i = 0; i < Database.organizers.size(); i++){
                if (Database.organizers.get(i).getUsername().equals(username)){
                    foundUsername = i;
                    break;
                }
            }
            if (foundUsername == -1){
                return false;
            }

            if(Database.organizers.get(foundUsername).getPassword().equals(Security.PassHash.hashPassword(password))){
                Database.currentLoggedInOrganizer = Database.organizers.get(foundUsername);
                return true;
            }

        }else if(userRole == 2){
            //Login customer
            int foundUsername = -1;
            for (int i = 0; i < Database.customers.size(); i++){
                if (Database.customers.get(i).getUsername().equals(username)){
                    foundUsername = i;
                    break;
                }
            }
            if(foundUsername == -1){
                return false;
            }

            if(Database.customers.get(foundUsername).getPassword().equals(Security.PassHash.hashPassword(password))){
                Database.currentLoggedInCustomer = Database.customers.get(foundUsername);
                return true;
            }

        }

        return false;
    }
}

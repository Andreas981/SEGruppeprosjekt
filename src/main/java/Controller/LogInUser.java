package Controller;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LogInUser {


    public int initLogin(){
        System.out.println("\nAre you a (1)Organizer or (2)Customer?");

        int userRole = 0;
        try{
            userRole = new Scanner(System.in).nextInt();

            int role = askForUserName(userRole);

            if (role == 1 || role == 2){
                System.out.println(userRole);
                goToMenu(userRole);
            }else{
                System.out.println("Sorry, that is not an option");
                initLogin();
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
            initLogin();
        }
        return 0;
    }

    private void goToMenu(int userRole) {
        switch(userRole){

            case 1:
                new OrganizerMenuController().initOrganizerMenu();
                break;
            case 2:
                System.out.println("HAHA");
                break;
            default:
                System.out.println("Something went wrong while logging in");
                break;
        }
    }

    private int askForUserName(int userRole) {
        System.out.print("\nUsername: ");
        String username = new Scanner(System.in).next();
        System.out.print("Password: ");
        String password = new Scanner(System.in).next();

        if(login(userRole, username, password)){
            System.out.println("You're logged in");
            return userRole;
        }else{
            System.out.println("Sorry, you could not login");
        }
        return 0;
    }

    private Boolean login(int userRole, String username, String password){

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

package Controller;

import Dummy.Database;
import View.LogInView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LogInUserController {

    LogInView logInView = new LogInView();

    public int initLogin(){
        logInView.displayPromptForUserRoleChoice();

        int userRole = 0;
        try{
            userRole = new Scanner(System.in).nextInt();

            int role = askForUserName(userRole);

            if (role == 1 || role == 2){
                System.out.println(userRole);
                goToMenu(userRole);
            }else{
                logInView.displayPromptForNotAnOption();
                initLogin();
            }
        }catch (InputMismatchException e){
            logInView.displayPromptForNotAnOption();
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
                //TODO Add customer login
                //TODO Add customer menu
                break;
            default:
                logInView.displayPromptForSomwthingWentWrong();
                break;
        }
    }

    private int askForUserName(int userRole) {
        logInView.displayPromptForUsername();
        String username = new Scanner(System.in).next();
        logInView.displayPromptForPassword();
        String password = new Scanner(System.in).next();

        if(login(userRole, username, password)){
            logInView.displayPromptForLoggedIn();
            return userRole;
        }else{
            logInView.displayPromptForNotAnOption();
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

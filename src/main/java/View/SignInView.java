package View;

import Controller.MenuController;
import Controller.SignInUserController;
import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SignInView {

    private Scanner scanner = new Scanner(System.in);
    private SignInUserController signInController = new SignInUserController();

    public void askForSignInType(){

        System.out.println("\nAre you a (1)Organizer or (2)Customer?");

        int userRole = 0;
        try{
            userRole = scanner.nextInt();
        }catch (InputMismatchException e){
            displayPromptForNotAnOption();
            return;
        }

       if(userRole == 1 || userRole == 2){
           signInUser(userRole);
       }else{
           displayPromptForNotAnOption();
       }
    }

    private void signInUser(int userRole) {
        System.out.print("\nUsername: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        if(signInController.singIn(userRole, username, password)){
            switch(userRole){
                case 1:
                    System.out.println("You're logged in");
                    new MenuController().startOrganizerView();
                    break;
                case 2:
                    System.out.println("You're logged in");
                    System.out.println(Database.currentLoggedInCustomer.getFirstName());
                    new CustomerMenuView().displayOptions();
                    break;
                default:
                    System.out.println("Something went wrong while logging in");
                    break;
            }
        }else{
            Database.currentLoggedInOrganizer = null;
            Database.currentLoggedInCustomer = null;
            System.out.println("Something went wrong while logging in");
        }
    }

    private void displayPromptForNotAnOption() {
        System.out.println("Sorry, that is not an option");
    }

}

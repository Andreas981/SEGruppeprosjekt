package View;

import Controller.CustomerMenuController;
import Controller.RegisterUserController;
import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuView {

    public void displayMenu(){

        Database.currentLoggedInOrganizer = null;
        Database.currentLoggedInCustomer = null;

        Scanner scanner = new Scanner(System.in);
        printMenu();

        int userSelection = -1;
        try{
            userSelection = scanner.nextInt();
            switch (userSelection) {
                case 1:
                    new SignInView().askForSignInType();
                    break;
                case 2:
                    RegisterUserController registerAnewUser = new RegisterUserController();
                    //registerAnewUser.startRegistrationForUser();
                    displayMenu();
                    break;
                case 3:
                    CustomerMenuController.displayOnGoingEvents();
                    displayMenu();
                    break;
                case 4:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Something went wrong");
                    printMenu();
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
        }

        displayMenu();

    }

    public static void printMenu(){
        System.out.println("\nPlease enter one of the following options:");
        System.out.println("\tType '1' to sign in");
        System.out.println("\tType '2' to register a new user");
        System.out.println("\tType '3' to look at events");
        System.out.println("\tType '4' to quit");
    }
}

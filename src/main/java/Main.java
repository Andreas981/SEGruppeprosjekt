import Controller.CustomerMenuController;
import Controller.RegisterUserController;
import Dummy.*;
import View.SignInView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        //Initializing the dummy database
        InitStart.Init();

        displayMenu();

    }

    public static void displayMenu(){
        Boolean exit = false;
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
                    registerAnewUser.startRegistrationForUser();
                    displayMenu();
                    break;
                case 3:
                    CustomerMenuController.displayOnGoingEvents();
                    displayMenu();
                    break;
                case 4:
                    System.out.println("Goodbye");
                    exit = true;
                    break;
                default:
                    System.out.println("Something went wrong");
                    printMenu();
            }
        }catch (InputMismatchException e){
            System.out.println("Sorry, that is not an option");
        }

        if(!exit){
            displayMenu();
        }
    }

    public static void printMenu(){
        System.out.println("\nPlease enter one of the following options:");
        System.out.println("\tType '1' to sign in");
        System.out.println("\tType '2' to register a new user");
        System.out.println("\tType '3' to look at events");
        System.out.println("\tType '4' to quit");
    }
}

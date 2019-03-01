import Controller.CustomerMenuController;
import Controller.LogInUserController;
import Controller.RegisterUserController;
import Dummy.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initializing the dummy database
        InitStart.Init();

        displayMenu();

    }

    public static void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        printMenu();
        // TODO input validation

        int userSelection = scanner.nextInt();
        switch (userSelection) {
            case 1:
                new LogInUserController().initLogin();
                break;
            case 2:
                RegisterUserController registerAnewUser = new RegisterUserController();
                registerAnewUser.startRegistrationForUser();
                displayMenu();
                break;
            case 3:
                CustomerMenuController displayEvents = new CustomerMenuController();
                displayEvents.enterCustomerMenu();
                break;
            case 4:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("Something went wrong");
                printMenu();



        }
    }

    public static void printMenu(){
        System.out.println("Please enter one of the following options:");
        System.out.println("Type '1' to login");
        System.out.println("Type '2' to register a new user");
        System.out.println("Type '3' to look at events");
        System.out.println("Type '4' to quit");
    }
}

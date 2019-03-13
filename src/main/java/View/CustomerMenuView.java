package View;

import Controller.CustomerMenuController;

import java.util.Scanner;

public class CustomerMenuView {
    private Scanner scanner = new Scanner(System.in);
    CustomerMenuController customerMenuController = new CustomerMenuController();
    public static void displayCustomerMenu(){

    }

    public void promptUserForAEventSeletion(){
        customerMenuController.enterCustomerMenu();
        System.out.println("Please enter the event number of the performance you wish to purchase tickets to.");
        String eventNumberInput = scanner.next();
        // TODO Move validation to controller
        if(eventNumberInput.length()<4) {
            System.out.println("Invalid selection entered");
                promptUserForAEventSeletion();
            }else{
                customerMenuController.validateUserSelection(eventNumberInput);
                // TODO Send user to OrderView
            }
        }
    }


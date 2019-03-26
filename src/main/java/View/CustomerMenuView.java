package View;

import Controller.CustomerEventSelectionController;
import Controller.OrderController;
import Dummy.Database;
import Model.Customer;
import Model.NonSeatedPlannedEvent;
import Model.SeatedPlannedEvent;


import java.util.Scanner;

public class CustomerMenuView {
    private Scanner scanner = new Scanner(System.in);
    private CustomerEventSelectionController customerEventSelectionController = new CustomerEventSelectionController();
    private OrderController orderController;
    public void displayOptions() {
        String selection = "";
        printMenu();
        try {
            selection = scanner.next();
        } catch (NumberFormatException n) {

        }
        if (selection.equals("1")) {
            new CustomerEventSelectionView().promptUserForAEventSeletion();
            displayOptions();
        } else if (selection.equals("2")) {
            System.out.println("Displaying tickets: ");
            for (int i = 0; i < Database.currentLoggedInCustomer.getCustomerTickets().size(); i++) {
                System.out.println(Database.currentLoggedInCustomer.getCustomerTickets().get(i).getValidForEvent().getNameOfEvent());
            }
            displayOptions();
        } else if (selection.equals("3")) {
            new CustomerEventSelectionController().enterCustomerMenu();
            System.out.println("Please enter the event number");
            selection = scanner.next();
            int[] event = customerEventSelectionController.validateUserSelection(selection);
            if (event != null) {
                orderController = new OrderController(event);
                // Validate tickets
                    boolean validTickets = false;
                    for (int i = 0; i < Database.currentLoggedInCustomer.getCustomerTickets().size(); i++) {
                        if (orderController.getPlannedEvent() instanceof  NonSeatedPlannedEvent) {
                            if (((NonSeatedPlannedEvent) orderController.getPlannedEvent()).getTickets().contains(Database.currentLoggedInCustomer.getCustomerTickets().get(i))) {
                                validTickets = true;
                            }
                        }
                        if(orderController.getPlannedEvent() instanceof  SeatedPlannedEvent){
                            if (((SeatedPlannedEvent) orderController.getPlannedEvent()).getTickets().contains(Database.currentLoggedInCustomer.getCustomerTickets().get(i))) {
                                validTickets = true;
                            }


                        }
                        System.out.println("You have " + (validTickets ? "valid " :" no valid ") +  "tickets for" + orderController.getPlannedEvent().getNameOfEvent());
                        displayOptions();

                    }

            } else {
                displayOptions();
            }
        }else if(selection.equals("4")) return;
    }

    private void printMenu() {
        System.out.println("Enter 1 to look at available events + \n" +
                "Enter 2 to display tickets in your inventory \n" +
                "Enter 3 to use a ticket at an event \n" +
                "Enter 4 to log out");
    }


}

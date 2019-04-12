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
            n.getCause();
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
        } else if (selection.equals("4")) {
            System.out.println("You have been successfully logged out!");
        } else if (selection.equals("3")) {
            new CustomerEventSelectionController().enterCustomerMenu();
            System.out.println("Please enter the event number(X-X-X-X)");
            selection = scanner.next();
            int[] event = customerEventSelectionController.validateUserSelection(selection);
            if (event != null) {
                orderController = new OrderController(event);
                // Validate tickets
                boolean validTickets = false;
                if (Database.currentLoggedInCustomer.getCustomerTickets().size() < 1) {
                    System.out.println("You don't have any tickets");
                    displayOptions();
                } else {
                    System.out.println();
                    int amountOfTickets = 0;
                    for (int i = 0; i < Database.currentLoggedInCustomer.getCustomerTickets().size(); i++) {
                        if (orderController.getPlannedEvent() instanceof NonSeatedPlannedEvent) {
                            if (((NonSeatedPlannedEvent) orderController.getPlannedEvent()).getTickets().contains(Database.currentLoggedInCustomer.getCustomerTickets().get(i))) {
                                validTickets = true;
                                amountOfTickets++;
                            }
                        } else if (orderController.getPlannedEvent() instanceof SeatedPlannedEvent) {
                            if (((SeatedPlannedEvent) orderController.getPlannedEvent()).getTickets().contains(Database.currentLoggedInCustomer.getCustomerTickets().get(i))) {
                                validTickets = true;
                                amountOfTickets++;
                            }

                        }

                    }
                    System.out.println("You have" + (validTickets ? " valid " : " no valid ") + (amountOfTickets > 1 ? amountOfTickets + " tickets" : " ticket") + " for " + orderController.getPlannedEvent().getNameOfEvent());
                    displayOptions();
                }
            } else {
                displayOptions();
            }
            // Log the user out
        } else if (selection.equals("4")) {
            return;
        } else {

            displayOptions();
        }
    }

    private void printMenu() {
        System.out.println(
                "Type '1' Buy ticket(s)\n" +
                "Type '2' Display bought ticket(s) \n" +
                "Type '3' Validate ticket(s) \n" +
                "Type '4' Logout");
    }


}

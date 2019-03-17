package View;

import Controller.PaymentController;
import Model.Order;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PaymentView {
    private Order order;
    private PaymentController paymentController;
    Scanner scanner = new Scanner(System.in);

    public PaymentView(Order order) {
        this.order = order;
        paymentController = new PaymentController(order);
    }

    public void displayAmountDue(){
        int amountToPay = paymentController.getAmountOfOrder();
        System.out.println("Amount to pay: " + amountToPay +" NOK");
        displayPaymentOptions();
    }

    public void displayPaymentOptions(){
        System.out.println("How would you like to pay?");
        printMenu();
        selectedOption(scanner.nextInt());
    }

    public void selectedOption(int userSelection) {
        try {
            switch (userSelection) {
                case 1:
                    System.out.println("PayPal Selected");
                    break;
                case 2:
                    System.out.println("Debitcard selected");
                    break;
                case 3:
                    System.out.println("Pay at desk selected");
                    break;
                case 4:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("I did not get that...");
                    printMenu();
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Sorry, that is not an option");

        }
    }

    public void printMenu() {
        System.out.println("\nPlease enter one of the following options:");
        System.out.println("\tType '1' to pay with PayPal");
        System.out.println("\tType '2' to pay with DebitCard");
        System.out.println("\tType '3' to pay at desk");
        System.out.println("\tType '4' to quit");
    }
}

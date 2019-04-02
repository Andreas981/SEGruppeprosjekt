package View;

import Controller.PaymentController;
import Dummy.Database;
import Dummy.PaymentStub;
import Model.Order;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PaymentView {
    private Order order;
    private PaymentController paymentController;
    private Scanner scanner;
    private boolean paymentOK;

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
        scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine();
            selectedOption(Integer.parseInt(input));
        }catch(NumberFormatException n){
            System.err.println("Please enter a valid option");
            displayPaymentOptions();
        }
        if(paymentOK){
            if(paymentController.reserveSlots()){
                System.out.println("Tickets added to your account");
            }else{
                System.out.println("There was an error processing your order, please contact service");
            }

        }else{
            displayPaymentOptions();
        }
    }

    public void selectedOption(int userSelection) {
        paymentOK = false;
        try {
            switch (userSelection) {
                case 1:
                    System.out.println("PayPal Selected");
                    paymentOK = new PaymentStub().payPal(Database.currentLoggedInCustomer.getUsername());
                    break;
                case 2:
                    System.out.println("Debit card selected");
                    System.out.println("Enter a 4 digit card number:");
                    paymentOK = new PaymentStub().debitCard(scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Vipps selected");
                    paymentOK = new PaymentStub().vipps((Integer.parseInt(Database.currentLoggedInCustomer.getTelephone())));
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Enter a valid option");
                    displayPaymentOptions();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option selected");

        }
    }

    public void printMenu() {
        System.out.println("\nPlease enter one of the following options:");
        System.out.println("\tType '1' to pay with PayPal");
        System.out.println("\tType '2' to pay with DebitCard");
        System.out.println("\tType '3' to pay with Vipps");
        System.out.println("\tType '4' to quit");
    }
}

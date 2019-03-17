package View;

import Controller.PaymentController;
import Model.Order;

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
        paymentController.printMenu();
        paymentController.selectedOption(scanner.nextInt());

    }
}

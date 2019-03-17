package Controller;

import Model.Order;
import View.RegisterUserView;
import View.SignInView;

import java.util.InputMismatchException;

public class PaymentController {
    Order order;

    public PaymentController(Order order) {
        this.order = order;
    }

    public int getAmountOfOrder(){
        int amount = order.getAmountDueInNOK();
        return amount;
    }

    public void selectedOption(int userSelection){
        try{
            switch (userSelection) {
                case 1:
                    System.out.println("PayPal Selected");
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
                    printMenu();
            }
        }catch (InputMismatchException e){
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

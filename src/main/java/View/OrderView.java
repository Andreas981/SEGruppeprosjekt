package View;

import Controller.OrderController;
import Controller.PaymentController;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {
    private OrderController orderController;
    private Scanner scanner = new Scanner(System.in);

    public OrderView(int[] eventNumber) {
        this.orderController = new OrderController(eventNumber);
    }

    public void displayPromptForSelectingAEvent(){
        orderController.getAvailableSlots();




        String slotsInput = scanner.nextLine();

        if(orderController.validateUserInput(slotsInput)){
            System.out.println("Order is valid");
            new PaymentView(orderController.getPlaceOrder()).displayAmountDue();
        }else {
            System.out.println("Invalid input");
            displayPromptForSelectingAEvent();
        }

        
    }
}

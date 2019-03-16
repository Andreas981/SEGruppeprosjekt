package View;

import Controller.OrderController;

import java.util.Scanner;

public class OrderView {
    private OrderController orderController;
    private Scanner scanner = new Scanner(System.in);

    public OrderView(int eventNumber[]) {
        this.orderController = new OrderController(eventNumber);
    }

    public void displayPromptForSelectingAEvent(){

        orderController.getAvailableSlots();

        System.out.println("Enter one or more seats you wish to reserve:");
        
    }
}

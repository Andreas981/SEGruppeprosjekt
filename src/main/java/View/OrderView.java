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

        System.out.println("\n\nEnter one or more seats you wish to reserve:");
        System.out.println("Example: 1,2,3");
        if(orderController.validateUserInput(scanner.nextLine())){
            System.out.println("Go to payment");
        }else {
            System.out.println("Invalid input");
            displayPromptForSelectingAEvent();
        }

        
    }
}

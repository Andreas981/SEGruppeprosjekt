package View;

import Controller.OrderController;
import Controller.PaymentController;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderView {
    private OrderController orderController;
    private Scanner scanner = new Scanner(System.in);
    private PaymentController paymentController = new PaymentController();

    public OrderView(int[] eventNumber) {
        this.orderController = new OrderController(eventNumber);
    }

    public void displayPromptForSelectingAEvent(){
        orderController.getAvailableSlots();

        System.out.println("Enter one or more seats you wish to reserve:");
        System.out.println("Example: 1,2,3");

        String slotsInput = scanner.nextLine();

        if(orderController.validateUserInput(slotsInput)){

            String[] slotAsText = slotsInput.split(",");
            ArrayList<Integer> slots = new ArrayList<Integer>();

            for(int i = 0; i < slotAsText.length; i++){
                slots.add(Integer.parseInt(slotAsText[i]));
            }

            System.out.println("Go to payment");
            paymentController.addPayment(orderController.setupAorder(slots));
            
        }else {
            System.out.println("Invalid input");
            displayPromptForSelectingAEvent();
        }

        
    }
}

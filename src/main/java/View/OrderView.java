package View;

import Controller.OrderController;

public class OrderView {
    private OrderController orderController;

    public OrderView(int eventNumber[]) {
        this.orderController = new OrderController(eventNumber);
    }

    public void displayPromptForSelectingAEvent(){

        System.out.println(" ");
        orderController.getAvailableSlots();
    }
}

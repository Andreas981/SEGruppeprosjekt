package View;

import Controller.OrderController;

public class OrderView {
    private OrderController orderController = new OrderController();


    public void displayPromptForSelectingAEvent(){
        orderController.displayAvalibleSlots();
        System.out.println("");
    }
}

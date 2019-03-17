package Controller;

import Model.Order;

public class PaymentController {
    Order order;

    public PaymentController(Order order) {
        this.order = order;
    }

    public int getAmountOfOrder(){
        int amount = order.getAmountDueInNOK();
        return amount;
    }

}

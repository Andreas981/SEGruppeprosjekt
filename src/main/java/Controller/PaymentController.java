package Controller;

import Model.Order;
import Model.SeatedPlannedEvent;
import Model.Ticket;

import java.util.ArrayList;
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

    public ArrayList<Ticket> reserveSeats(){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        SeatedPlannedEvent plannedEvent = (SeatedPlannedEvent) order.getPlannedEvent() ;
        for(int i = 0;i<order.getSlots().size();i++){
            plannedEvent.getTickets().get(order.getSlots().get(i)).setAvailable(false);
            tickets.add(plannedEvent.getTickets().get(order.getSlots().get(i)));
        }
        return tickets;
    }






}

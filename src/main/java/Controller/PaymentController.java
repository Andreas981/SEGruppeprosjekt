package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
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

    // TODO Simplify method to new object
    public boolean reserveSlots(){
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        if(order.getPlannedEvent() instanceof SeatedPlannedEvent) {
            SeatedPlannedEvent plannedEvent = (SeatedPlannedEvent) order.getPlannedEvent();
            for (int i = 0; i < order.getSlots().size(); i++) {
                plannedEvent.getTickets().get(order.getSlots().get(i)).setAvailable(false);

                tickets.add(plannedEvent.getTickets().get(order.getSlots().get(i)));
            }
            Database.currentLoggedInCustomer.getCustomerTickets().addAll(tickets);
            return true;
        }else{
            NonSeatedPlannedEvent ns = (NonSeatedPlannedEvent) order.getPlannedEvent();
            for(int i = 0; i< order.getSlots().get(0);i++){
                tickets.add(ns.getTickets().get(0));
            }
            ns.setFreeSpace(ns.getFreeSpace() - order.getSlots().get(0));
            Database.currentLoggedInCustomer.getCustomerTickets().addAll(tickets);
            return true;
        }

    }






}

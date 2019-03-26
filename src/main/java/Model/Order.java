package Model;

import java.util.ArrayList;

/**
 * Model used for generating a order to buy ticket(s) for a customer
 */
public class Order {
    private ArrayList<Integer> slots;
    private PlannedEvent plannedEvent;
    private int amountDueInNOK;

    public Order(ArrayList<Integer> slots, PlannedEvent plannedEvent) {
        this.slots = slots;
        this.plannedEvent = plannedEvent;
    }

    public ArrayList<Integer> getSlots() {
        return slots;
    }

    private int amountDue(){
        System.out.println("Amount due called");
        amountDueInNOK = 0;
        // Get ticket prize
        for (Integer slot : slots) {
            if (plannedEvent instanceof SeatedPlannedEvent) {
                amountDueInNOK += ((SeatedPlannedEvent) plannedEvent).getTickets().get(slot).getPriceInNOK();
           }else{
                // TODO Implement price
                amountDueInNOK = ((NonSeatedPlannedEvent) plannedEvent).getTickets().get(0).getPriceInNOK() * slot;
            }
        }
        return amountDueInNOK;
    }



    public void setSlots(ArrayList<Integer> slots) {
        this.slots = slots;
    }

    public PlannedEvent getPlannedEvent() {
        return plannedEvent;
    }

    public void setPlannedEvent(PlannedEvent plannedEvent) {
        this.plannedEvent = plannedEvent;
    }

    public int getAmountDueInNOK() {
        return amountDue();
    }

    public void setAmountDueInNOK(int amountDueInNOK) {
        this.amountDueInNOK = amountDueInNOK;
    }
}

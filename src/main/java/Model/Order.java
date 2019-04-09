package Model;

import java.util.ArrayList;

/**
 * Model used for generating a order to buy ticket(s) for a customer
 */
public class Order {
    private ArrayList<Integer> slots;
    private PlannedEvent plannedEvent;
    private int[] eventNumber;
    private int amountDueInNOK;

    public Order(ArrayList<Integer> slots, PlannedEvent plannedEvent, int[] eventNumber) {
        this.slots = slots;
        this.plannedEvent = plannedEvent;
        this.eventNumber = eventNumber;
    }
    public Order(ArrayList<Integer> slots, PlannedEvent plannedEvent) {
        this.slots = slots;
        this.plannedEvent = plannedEvent;

    }

    public int[] getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int[] eventNumber) {
        this.eventNumber = eventNumber;
    }

    public ArrayList<Integer> getSlots() {
        return slots;
    }

    private int amountDue(){
        amountDueInNOK = 0;
        // Get ticket prize
        for (Integer slot : slots) {
            if (plannedEvent instanceof SeatedPlannedEvent) {
                amountDueInNOK += ((SeatedPlannedEvent) plannedEvent).getTickets().get(slot).getPriceInNOK();
           }else{
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

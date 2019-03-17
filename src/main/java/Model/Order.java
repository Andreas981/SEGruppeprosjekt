package Model;

import java.util.ArrayList;

/**
 * Model used for generating a order to buy ticket(s) for a customer
 */
public class Order {
    private ArrayList<Integer> slots;
    private PlannedEvent plannedEvent;
    private int amountDueInNOK;

    public ArrayList<Integer> getSlots() {
        return slots;
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
        return amountDueInNOK;
    }

    public void setAmountDueInNOK(int amountDueInNOK) {
        this.amountDueInNOK = amountDueInNOK;
    }
}

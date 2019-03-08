package Model;

import java.util.ArrayList;

/**
 * Model used for generating a order to buy ticket(s) for a customer
 */
public class Order {
    private int totalAmountInNOK;
    private ArrayList<Ticket> amountOfTickets;
    // TODO Discount system
    // TODO Send to accounting



    public Order(int totalAmountInNOK, ArrayList<Ticket> amountOfTickets) {
        this.totalAmountInNOK = totalAmountInNOK;
        this.amountOfTickets = amountOfTickets;
    }

    public int getTotalAmountInNOK() {
        return totalAmountInNOK;
    }

    public ArrayList<Ticket> getAmountOfTickets() {
        return amountOfTickets;
    }
}

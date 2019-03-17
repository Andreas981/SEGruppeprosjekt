package Model;

import org.joda.time.LocalDate;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Ticket> customerTickets = new ArrayList<Ticket>();

    public Customer(String firstName, String lastName, String mail, String telephone, String username, String password, LocalDate birthday) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
    }

    public ArrayList<Ticket> getCustomerTickets() {
        return customerTickets;
    }

    public void setCustomerTickets(ArrayList<Ticket> customerTickets) {
        this.customerTickets = customerTickets;
    }

    public void addTicket(Ticket ticket){
        this.customerTickets.add(ticket);
    }
}

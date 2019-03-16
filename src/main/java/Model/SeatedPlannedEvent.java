package Model;

import Dummy.Database;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class SeatedPlannedEvent extends PlannedEvent {
    private int soldtickets;
    private ArrayList<Ticket> tickets;

    public SeatedPlannedEvent(String nameOfEvent, LocalDateTime dateOfEvent, int lengthOfEvent, int ageLimit) {
        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.tickets = new ArrayList<Ticket>();
    }

// TODO Method for displaying sold tickets

    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    @Override
    public String toString(){
        return getNameOfEvent();
    }
}

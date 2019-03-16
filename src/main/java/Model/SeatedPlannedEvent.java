package Model;

import Dummy.Database;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class SeatedPlannedEvent extends PlannedEvent {
    private int soldtickets;
    private ArrayList<Ticket> tickets;

    public SeatedPlannedEvent(String nameOfEvent, LocalDateTime dateOfEvent, int lengthOfEvent, int ageLimit, int location, int room) {
        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.tickets = new ArrayList<Ticket>();

        addTicketsToEvent(location, room);
    }

    private void addTicketsToEvent(int location, int room){
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getMaxParticipents(); i++) {
            tickets.add(new Ticket("ID", this, 100, i));
        }

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

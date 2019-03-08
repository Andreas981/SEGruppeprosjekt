package Model;


import org.joda.time.LocalDateTime;

public class SeatedPlannedEvent extends PlannedEvent {
    private int soldtickets;

    public SeatedPlannedEvent(String nameOfEvent, LocalDateTime dateOfEvent, int lengthOfEvent, int ageLimit) {
        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
    }

// TODO Method for displaying sold tickets

    @Override
    public String toString(){
        return getNameOfEvent();
    }
}

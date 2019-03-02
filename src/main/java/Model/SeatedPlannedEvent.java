package Model;

import java.sql.Time;
import java.util.Date;

public class SeatedPlannedEvent extends PlannedEvent {
    private int soldtickets;

    public SeatedPlannedEvent(String nameOfEvent, String dateOfEvent, Time timeOfEventStart, int lengthOfEvent, int ageLimit) {
        super(nameOfEvent, dateOfEvent, timeOfEventStart, lengthOfEvent, ageLimit);
    }

    // TODO Method for displaying sold tickets

    @Override
    public String toString(){
        return getNameOfEvent();
    }
}

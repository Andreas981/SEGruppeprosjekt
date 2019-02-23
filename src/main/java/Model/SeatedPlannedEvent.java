package Model;

import java.sql.Time;
import java.util.Date;

public class SeatedPlannedEvent extends PlannedEvent {
    private int soldtickets;

    public SeatedPlannedEvent(String nameOfEvent, Date date, Time timeOfEventStart, int lengthOfEvent, int ageLimit) {
        super(nameOfEvent, date, timeOfEventStart, lengthOfEvent, ageLimit);
    }

    // TODO Method for displaying sold tickets
}

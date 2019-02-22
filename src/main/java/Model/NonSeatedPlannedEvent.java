package Model;

import java.sql.Time;
import java.util.Date;

public class NonSeatedPlannedEvent extends PlannedEvent {
    private int freeSpace;
    private String meetUp;

    // TODO Modified constructor for different events:
    public NonSeatedPlannedEvent(String nameOfEvent, Date date, Time timeOfEventStart, int lengthOfEvent, int ageLimit
            , int freeSpace, String meetUp) {
        super(nameOfEvent, date, timeOfEventStart, lengthOfEvent, ageLimit);
        this.freeSpace = freeSpace;
        this.meetUp = meetUp;
    }
    // TODO Field for list of specialNotice
    // TODO field for boolean that can be checked if the event is free
    // TODO Allow users with an account to be added to participants ArrayList<customer>


    // TODO Method for displaying all notice's regarding the event
    // TODO Method for adding a notice to the event
}

package model;

import java.sql.Time;
import java.util.Date;

public class PlannedEvent {
    private String nameOfEvent;
    // TODO Add organizer field
    private Date date;
    private Time timeOfEventStart;
    private int lengthOfEvent;
    private int ageLimit;
    // TODO Add tickets<Ticket>


    public PlannedEvent(String nameOfEvent, Date date, Time timeOfEventStart, int lengthOfEvent, int ageLimit) {
        // TODO Faulty input try/catch?
        this.nameOfEvent = nameOfEvent;
        this.date = date;
        this.timeOfEventStart = timeOfEventStart;
        this.lengthOfEvent = lengthOfEvent;
        this.ageLimit = ageLimit;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimeOfEventStart() {
        return timeOfEventStart;
    }

    public int getLengthOfEvent() {
        return lengthOfEvent;
    }

    public int getAgeLimit() {
        return ageLimit;
    }
}

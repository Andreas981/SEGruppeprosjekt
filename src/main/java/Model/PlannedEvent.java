package Model;

import org.joda.time.LocalDateTime;

/**
 * Abstract class used for creating Events by Organizer's
 */
public abstract class PlannedEvent {
    private String nameOfEvent;
    // TODO Add organizer<organizer> field
    private LocalDateTime dateOfEvent;
    private int lengthOfEvent;
    private int ageLimit;
    // TODO Add tickets<Ticket>


    public PlannedEvent(String nameOfEvent, LocalDateTime dateOfEvent, int lengthOfEvent, int ageLimit) {
        this.nameOfEvent = nameOfEvent;
        this.dateOfEvent = dateOfEvent;
        this.lengthOfEvent = lengthOfEvent;
        this.ageLimit = ageLimit;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public int getLengthOfEvent() {
        return lengthOfEvent;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public void setLengthOfEvent(int lengthOfEvent) {
        this.lengthOfEvent = lengthOfEvent;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString(){
        return nameOfEvent ;
    }

}

package Model;

import java.util.ArrayList;

/**
 * Class for creating Room in LocationController.
 * A Room can have one or more events
 */

public class Room {
    private String name;
    private int maxParticipents;
    private Boolean eventInProgress;
    private ArrayList<SeatedPlannedEvent> events;
    private int amountOfRows;
    //TODO Make SeatedEvent Class
    //TODO Add accounting for event

    public Room(String name, int maxParticipents, Boolean eventInProgress, int amountOfRows) {
        this.name = name;
        this.maxParticipents = maxParticipents;
        this.eventInProgress = eventInProgress;
        this.amountOfRows = amountOfRows;
        this.events = new ArrayList<SeatedPlannedEvent>();
    }


    //TODO Method for increasing max participents
    //TODO Method for decreasing max participents

    public void addEvent(SeatedPlannedEvent seatedPlannedEvent){
        events.add(seatedPlannedEvent);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxParticipents() {
        return maxParticipents;
    }
    public void setMaxParticipents(int maxParticipents) {
        this.maxParticipents = maxParticipents;
    }
    public Boolean getEventInProgress() {
        return eventInProgress;
    }
    public void setEventInProgress(Boolean eventInProgress) {
        this.eventInProgress = eventInProgress;
    }
    public ArrayList<SeatedPlannedEvent> getEvents() {
        return events;
    }

}

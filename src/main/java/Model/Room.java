package Model;

import java.util.ArrayList;

/**
 * Class for creating Room in Location.
 * A Room can have one or more events
 */

public class Room {
    String name;
    int maxParticipents;
    Boolean eventInProgress;
    ArrayList<SeatedPlannedEvent> events;
    //TODO Make SeatedEvent Class
    //TODO Add accounting for event

    public Room(String name, int maxParticipents, Boolean eventInProgress) {
        this.name = name;
        this.maxParticipents = maxParticipents;
        this.eventInProgress = eventInProgress;
        this.events = new ArrayList<SeatedPlannedEvent>();
    }


    //TODO Method for increasing max participents
    //TODO Method for decreasing max participents

    //TODO Method for adding seated event
    //TODO Method for removing seated event

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

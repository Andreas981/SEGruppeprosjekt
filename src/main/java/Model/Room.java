package Model;

import java.util.ArrayList;

public class Room {
    String name;
    int maxParticipents;
    Boolean eventInProgress;
    ArrayList<SeatedEvent> events;
    //TODO Make SeatedEvent Class
    //TODO Add accounting for event

    public Room(String name, int maxParticipents, Boolean eventInProgress) {
        this.name = name;
        this.maxParticipents = maxParticipents;
        this.eventInProgress = eventInProgress;
        this.events = new ArrayList<SeatedEvent>();
    }


    //TODO Method for increasing max participents
    //TODO Method for decreasing max participents


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
}

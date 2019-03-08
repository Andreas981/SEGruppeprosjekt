package Model;


import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * The type of user that will organize an event
 */

public class Organizer extends User{

    String organization;
    int accessLevel;
    ArrayList<Location> locations = new ArrayList<Location>();
    ArrayList<NonSeatedPlannedEvent> nonSeatedPlannedEvents = new ArrayList<NonSeatedPlannedEvent>();
    //TODO Add accounting for organizer


    public Organizer(String firstName, String lastName, String mail, String telephone, String username, String password, LocalDate birthday, String organization, int accessLevel) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
        this.organization = organization;
        this.accessLevel = accessLevel;
    }

    public void addLocation(Location location){
        locations.add(location);
    }
    public ArrayList<Location> getLocations(){
        return locations;
    }

    public void addNonSeatedPlannedEvent(NonSeatedPlannedEvent event){
        nonSeatedPlannedEvents.add(event);
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public int getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}

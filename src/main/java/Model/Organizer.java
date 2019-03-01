package Model;

import java.util.Date;
import java.util.ArrayList;

/**
 * The type of user that will organize an event
 */

public class Organizer extends User{

    String organization;
    int accessLevel;
    ArrayList<Location> locations;
    //TODO Add accounting for organizer
    // TODO Add nonSeatedEvents booking

    public Organizer(String firstName, String lastName, String mail, String telephone, String username, String password, Date birthday, String organization, int  accessLevel) {
        super(firstName, lastName, mail, telephone, username, password, birthday);
        this.organization = organization;
        this.accessLevel = accessLevel;
        locations = new ArrayList<Location>();
    }

    public void addLocation(Location location){
        locations.add(location);
    }
    public ArrayList<Location> getLocations(){
        return locations;
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

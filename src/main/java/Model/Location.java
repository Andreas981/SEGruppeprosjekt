package Model;

import java.util.ArrayList;

/**
 * Class for creating Location in Organizer.
 * A location can have one or more rooms.
 */

public class Location {
    String name, address;
    Boolean publicLocation;
    ArrayList<Room> rooms;
    //TODO Add accounting for location

    public Location(String name, String address, Boolean publicLocation) {
        this.name = name;
        this.address = address;
        this.publicLocation = publicLocation;
        this.rooms = new ArrayList<Room>();
    }


    //TODO Method for removing a Room
    public void addRoom(Room room){
        rooms.add(room);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getPublicLocation() {
        return publicLocation;
    }
    public void setPublicLocation(Boolean publicLocation) {
        this.publicLocation = publicLocation;
    }
}

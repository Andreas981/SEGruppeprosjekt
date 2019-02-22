package Model;

import java.util.ArrayList;

public class Location {
    String name, address;
    Boolean publicLocation, multipleRoom;
    ArrayList<Room> rooms;
    //TODO Add accounting for location

    public Location(String name, String address, Boolean publicLocation, Boolean multipleRoom) {
        this.name = name;
        this.address = address;
        this.publicLocation = publicLocation;
        this.multipleRoom = multipleRoom;
        this.rooms = new ArrayList<Room>();
    }


    //TODO Method for adding a Seated Event
    //TODO Method for removing a Seated Event

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
    public Boolean getMultipleRoom() {
        return multipleRoom;
    }
    public void setMultipleRoom(Boolean multipleRoom) {
        this.multipleRoom = multipleRoom;
    }
}

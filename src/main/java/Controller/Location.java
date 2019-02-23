package Controller;

import Dummy.Database;

public class Location {
    public static Boolean addLocation(String name, String address, int publicLocaiton) {
        Database.currentLoggedInOrganizer.addLocation(new Model.Location(name, address, (publicLocaiton==1)));
        return (Database.currentLoggedInOrganizer.getLocations().get(Database.currentLoggedInOrganizer.getLocations().size()-1).getName().equals(name));
    }
}

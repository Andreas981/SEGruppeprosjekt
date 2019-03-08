package Controller;

import Dummy.Database;

public class LocationController {
    public static Boolean addLocation(String name, String address, int publicLocaiton) {

        if(publicLocaiton == 1 || publicLocaiton == 2){
            Database.currentLoggedInOrganizer.addLocation(new Model.Location(name, address, (publicLocaiton==1)));
            return (Database.currentLoggedInOrganizer.getLocations().get(Database.currentLoggedInOrganizer.getLocations().size()-1).getName().equals(name));
        }
        return false;
    }
}

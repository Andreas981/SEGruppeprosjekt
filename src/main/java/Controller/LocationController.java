package Controller;

import Dummy.Database;
import Model.Location;

public class LocationController {
    public static Boolean addLocation(String name, String address, int publicLocaiton) {
        if(publicLocaiton == 1 || publicLocaiton == 2){
            Database.currentLoggedInOrganizer.addLocation(new Model.Location(name, address, (publicLocaiton==1)));
            return (Database.currentLoggedInOrganizer.getLocations().get(Database.currentLoggedInOrganizer.getLocations().size()-1).getName().equals(name));
        }
        return false;
    }
    public static Boolean removeLocation(int location){
        if(location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){
            Database.currentLoggedInOrganizer.getLocations().remove(location);
            return true;
        }
        return false;
    }

    public static boolean checkExistance(int choice) {
        if (choice >= 0 && choice < Database.currentLoggedInOrganizer.getLocations().size())
            return true;
        return false;
    }
}

package Controller;

import Dummy.Database;

public class LocationController {
    public Boolean addLocation(String name, String address, int publicLocaiton) {
        if(publicLocaiton == 1 || publicLocaiton == 2){
            Database.currentLoggedInOrganizer.addLocation(new Model.Location(name, address, (publicLocaiton==1)));
            return (Database.currentLoggedInOrganizer.getLocations().get(Database.currentLoggedInOrganizer.getLocations().size()-1).getName().equals(name));
        }
        return false;
    }
    public Boolean removeLocation(int location){
        if(location >= 0 && location <= Database.currentLoggedInOrganizer.getLocations().size()){
            Database.currentLoggedInOrganizer.getLocations().remove(location);
            return true;
        }
        return false;
    }
    public boolean checkExistance(int choice) {
        if (choice >= 0 && choice < Database.currentLoggedInOrganizer.getLocations().size())
            return true;
        return false;
    }
    public void editLocaiton(int location, String name, String address, String publicLocaiton){
        if(!name.equals("."))
            Database.currentLoggedInOrganizer.getLocations().get(location).setName(name);
        if(!address.equals("."))
            Database.currentLoggedInOrganizer.getLocations().get(location).setAddress(address);
        if(!publicLocaiton.equals("."))
            Database.currentLoggedInOrganizer.getLocations().get(location).setPublicLocation((publicLocaiton.toLowerCase().startsWith("y")));

    }
}

package Controller;

import Dummy.Database;

public class LevelTwoOrganizerController {
    public void printLocationForCurrenOrganizer() {
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++)
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());
    }
}

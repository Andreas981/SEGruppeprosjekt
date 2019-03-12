package Controller;

import Dummy.Database;
import View.LevelTwoOrganizerView;

public class MenuController {
    public void startOrganizerView(){
        switch (Database.currentLoggedInOrganizer.getAccessLevel()){
            case 1:
                break;
            case 2:
                new LevelTwoOrganizerView().startView();
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }
}

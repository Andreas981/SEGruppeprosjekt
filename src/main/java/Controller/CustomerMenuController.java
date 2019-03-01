package Controller;

import Dummy.Database;
import Model.Organizer;

public class CustomerMenuController {


    public void enterCustomerMenu(){
        for (Organizer organizer : Database.organizers){
            for(Model.Location location : organizer.getLocations()){

            }

        }

    }
}

package Controller;

import Dummy.Database;
import Model.Location;
import Model.Organizer;
import Model.Room;
import org.w3c.dom.events.Event;

public class CustomerMenuController {


    public void enterCustomerMenu(){
        for (Organizer organizer : Database.organizers){
            System.out.println("");
            System.out.println("Oranizer name: " + organizer.getOrganization());
            // TODO Add listing of nonSeated events
            // for(Event nonSeated : organizer.a)
            for(Location location : organizer.getLocations()){
                System.out.println("Location name: " + location.getName());
                for(Room aRoom : location.getRooms()){
                    System.out.println("Playing at: " + aRoom.getName());
                    for(Model.PlannedEvent aEvent : aRoom.getEvents()){
                        if(aRoom.getEvents().size()<1){
                            System.out.println("No planned events");
                        }
                        System.out.println(aEvent.toString());

                    }
                }
            }

        }

    }
}

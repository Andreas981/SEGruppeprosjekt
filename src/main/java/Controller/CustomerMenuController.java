package Controller;

import Dummy.Database;
import Model.Location;
import Model.Organizer;
import Model.Room;
import org.w3c.dom.events.Event;
import sun.invoke.empty.Empty;

import java.lang.reflect.Method;

public class CustomerMenuController {

    public static void displayOnGoingEvents(){
        // For use in main screen
        System.out.println("_____________________________________________________________________");
        for (Organizer organizer : Database.organizers){
            System.out.println("*********************************************************************");
            System.out.println("Arranger: " + organizer.getOrganization());
            // TODO Add listing of nonSeated events
            // for(Event nonSeated : organizer.a)
            for(Location location : organizer.getLocations()){
                System.out.println("    Playing at: " + location.getName());
                for(Room aRoom : location.getRooms()){
                    System.out.println("        In room: " + aRoom.getName());
                    if(aRoom.getEvents().size()<1){
                        System.out.println("            No planned events");
                    }else{
                        for(Model.PlannedEvent aEvent : aRoom.getEvents()){
                            System.out.println("            " + aEvent.getNameOfEvent()
                                    + " " + aEvent.getDateOfEvent().toLocalDate().toString() + " \n" +
                                    "               Starting at: " + aEvent.getDateOfEvent().toLocalTime().getHourOfDay() + ":" +aEvent.getDateOfEvent().getMinuteOfHour() + " "
                            + aEvent.getLengthOfEvent() + "min runtime ");
                        }
                    }

                }
            }

        }
        System.out.println("_____________________________________________________________________");
        System.out.println("To buy a ticket for a event, please log in");
    }

    // Used by a logged in user
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
                        if(aEvent.toString().equals("")){
                            System.out.println("No planned events");
                        }
                        System.out.println(aEvent.toString());

                    }
                }
            }

        }

    }
}

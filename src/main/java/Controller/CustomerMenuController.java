package Controller;

import Dummy.Database;
import Model.*;


public class CustomerMenuController {
    public static void displayOnGoingEvents(){
        // For use in main screen
        System.out.println("_____________________________________________________________________");

            System.out.println("*********************************************************************");
             for(UpcomingEvent upcomingEvent : Database.EventPool){
                System.out.println("    Playing at: " + upcomingEvent.getAddress());
                if(!upcomingEvent.getFreeEvent()){
                    // Display prive
                }
                 System.out.println( upcomingEvent.getTickets().size());
                 System.out.println();
             }

        System.out.println("_____________________________________________________________________");
        System.out.println("To buy a ticket for a event, please log in");
    }

    // Used by a logged in user
    public void enterCustomerMenu() {
        System.out.println("_____________________________________________________________________");
        for(UpcomingEvent upcomingEvent : Database.EventPool) {
            System.out.println(upcomingEvent.getNameOfEvent());
            System.out.println("    Playing at: " + upcomingEvent.getAddress());
        }

    }

    public boolean validateUserSelection(String userInput) {
        if(userInput.length()<1){
            return false;
        }
        return false;
    }

    // Control that that valid int selection exist in the database
    private Boolean checkIfEventExist(int eventNumber) {
        // TODO Implement check for checking that the event exist
        return false;
    }
}

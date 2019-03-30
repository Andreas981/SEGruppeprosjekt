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
        String[] eventSplit = userInput.split("");
        int[] eventNumber = new int[eventSplit.length];
        try  {
            for (int i = 0; i < eventSplit.length; i++) {
                eventNumber[i] = Integer.parseInt(eventSplit[i]);
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid selection entered");
            return false;
        }
        if(checkIfEventExist(eventNumber)){
            return true;
        }
        return null;
    }

    // Control that that valid int selection exist in the database
    private Boolean checkIfEventExist(int[] eventNumber) {

        if(Database.organizers.size()>eventNumber[0]){

            if (Database.organizers.get(eventNumber[0]).getLocations().size()>eventNumber[1]) {

                if (Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms().size()>eventNumber[2]){
                    return Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                            .get(eventNumber[2]).getEvents().size() > (eventNumber[3]);
                }
                return false;
            }
            return false;
        }
        return false;

    }

    private void selectEvent(int[] eventNumber){

    }
}

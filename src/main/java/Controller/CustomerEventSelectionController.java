package Controller;

import Dummy.Database;
import Model.*;


public class CustomerEventSelectionController {
    public static void displayOnGoingEvents(){
        // For use in main screen

        // TODO Refactor to eventlist
        System.out.println("_____________________________________________________________________");
        for (UpcomingEvent upcomingEvent : Database.EventPool){
            System.out.println("*********************************************************************");
            System.out.println(upcomingEvent.getNameOfEvent());
            System.out.println("Arranger: " + upcomingEvent.getOrganizer());
            System.out.println(upcomingEvent.getAddress());
            System.out.println(upcomingEvent.getDateOfEvent().toLocalTime());
            // TODO Add listing of nonSeated events

        }

        System.out.println("_____________________________________________________________________");
        System.out.println("To buy a ticket for a event, please log in");
    }

    // Used by a logged in user
    public void enterCustomerMenu() {
        // TODO Refactor to eventlist
        System.out.println("_____________________________________________________________________");
        for (UpcomingEvent upcomingEvent : Database.EventPool) {
            System.out.println("*********************************************************************");
            System.out.println(upcomingEvent.getNameOfEvent());
            System.out.println("Arranger name: " + upcomingEvent.getOrganizer());
            System.out.println(upcomingEvent.getAddress());
            System.out.println("Age limit" + upcomingEvent.getAgeLimit());
            System.out.println("Price: " + (upcomingEvent.getFreeEvent() ? " free" : upcomingEvent.getPrice()));
                // Show number of avalible tickets
                System.out.println("Event number: " + upcomingEvent.getEventNumber());


                System.out.println("_____________________________________________________________________");
            }

    }

    public int[] validateUserSelection(String userInput) {
        if(userInput.length()<1){
            return null;
        }
        String[] eventSplit = userInput.split("");
        int[] eventNumber = new int[eventSplit.length];
        try  {
            for (int i = 0; i < eventSplit.length; i++) {
                eventNumber[i] = Integer.parseInt(eventSplit[i]);
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid selection entered");
            return null;
        }
        if(checkIfEventExist(eventNumber)){
            return eventNumber;
        }
        return null;
    }

    // Control that that valid int selection exist in the database
    private Boolean checkIfEventExist(int[] eventNumber) {
        if(Database.organizers.size()>eventNumber[0]) {
            // Check if the event number is for a seated XXXX or Nonseated Event XX
            if (eventNumber.length < 3) {
                if(Database.organizers.get(eventNumber[0]).getNonSeatedPlannedEvents().size() > eventNumber[1])
                {   NonSeatedPlannedEvent ns = Database.organizers.get(eventNumber[0]).getNonSeatedPlannedEvents().get(eventNumber[1]);
                    return true;
                }
            } else {
                if (Database.organizers.get(eventNumber[0]).getLocations().size() > eventNumber[1]) {

                    if (Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms().size() > eventNumber[2]) {
                        return Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                                .get(eventNumber[2]).getEvents().size() > (eventNumber[3]);
                    }
                    return false;
                }
                return false;
            }

        }
        return false;
    }

    // Check that there is still free slots for the event the user wants to but tickets to:
    private boolean checkSlots(NonSeatedPlannedEvent nonSeatedPlannedEvent) {
        return (nonSeatedPlannedEvent.getFreeSpace()>0);
    }

}

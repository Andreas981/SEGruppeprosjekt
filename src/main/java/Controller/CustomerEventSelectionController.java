package Controller;

import Dummy.Database;
import Model.*;


public class CustomerEventSelectionController {
    public static void displayOnGoingEvents(){
        // For use in main screen
        System.out.println("_____________________________________________________________________");
        for (Organizer organizer : Database.organizers){
            for(NonSeatedPlannedEvent nonSeatedPlannedEvent : organizer.getNonSeatedPlannedEvents()) {
                System.out.println("*********************************************************************");
                System.out.println("Arranger: " + organizer.getOrganization());
                System.out.println("Outdoor event");
                System.out.println(nonSeatedPlannedEvent.getNameOfEvent() + " Age Limit: " + nonSeatedPlannedEvent.getAgeLimit() +"+"
                        +"\n" + "Meet up: " + nonSeatedPlannedEvent.getMeetUp()
                        );

                System.out.println("_____________________________________________________________________");
            }

            for(Location location : organizer.getLocations()){

                for(Room aRoom : location.getRooms()){

                    if(aRoom.getEvents().size()<1){

                    }else{
                        for(Model.PlannedEvent aEvent : aRoom.getEvents()){
                            System.out.println("    Playing at: " + location.getName());
                            System.out.println("        In room: " + aRoom.getName());
                            System.out.println("            No planned events");
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
    public void enterCustomerMenu() {
        System.out.println("_____________________________________________________________________");
        for(int i = 0;i<Database.organizers.size();i++) {
            for(int nonS = 0; nonS < Database.organizers.get(i).getNonSeatedPlannedEvents().size();nonS++ ) {
                System.out.println("*********************************************************************");
                System.out.println("Arranger name: " + Database.organizers.get(i).getOrganization());
                System.out.println("Outdoor event");
                NonSeatedPlannedEvent aEvent = Database.organizers.get(i).getNonSeatedPlannedEvents().get(nonS);
                System.out.println(aEvent.getNameOfEvent() + " Age Limit: " + aEvent.getAgeLimit() +"+"
                        +"\n" + "Meet up: " + aEvent.getMeetUp());
                System.out.println("Available tickets: " + (aEvent.getFreeSpace() > 0 ? aEvent.getFreeSpace() : "Sold out" ));
                System.out.println("Event number: (" + (i) +"-" + (nonS) +")");

                System.out.println("_____________________________________________________________________");
            }

            for (int j = 0; j < Database.organizers.get(i).getLocations().size();j++) {
                for(int k = 0; k < Database.organizers.get(i).getLocations().get(j).getRooms().size();k++){

                    for(int m = 0; m<Database.organizers.get(i).getLocations().get(j)
                            .getRooms().get(k).getEvents().size();m++){
                        System.out.println("*********************************************************************");
                        System.out.println("Arranger name: " + Database.organizers.get(i).getOrganization());
                        System.out.println("Playing at: " + Database.organizers.get(i)
                                .getLocations().get(j).getName());
                        System.out.println("        _______________________________________________________");
                        System.out.println("        In room: " + Database.organizers.get(i).getLocations()
                                .get(j).getRooms().get(k).getName());
                        System.out.println("            _________________________________________________");
                        PlannedEvent plannedEvent = Database.organizers.get(i).getLocations().get(j).getRooms()
                                .get(k).getEvents()
                                .get(m);
                        int freeSeats = 0;
                        for(int h = 0; h < ((SeatedPlannedEvent) plannedEvent).getTickets().size();h++){
                            if(((SeatedPlannedEvent) plannedEvent).getTickets().get(h).getAvailable()){
                                freeSeats++;
                            }
                        }
                        System.out.println("            " + plannedEvent.getNameOfEvent()
                                + " " + plannedEvent.getDateOfEvent().toLocalDate().toString() + " \n" +
                                "            Starting at: " + plannedEvent.getDateOfEvent().toLocalTime().getHourOfDay()
                                + ":" +plannedEvent.getDateOfEvent().getMinuteOfHour() + " "
                                + plannedEvent.getLengthOfEvent() +  "min runtime \n" +
                                "            Seats available:" +  (freeSeats > 0 ? ((SeatedPlannedEvent) plannedEvent).getTickets().size() : "Sold out" ) + "\n"+
                                "            Event number: (" + (i)+"-"+(j)+"-"+(k)+"-"+(m) + ")");

                    }
            }
        }

        }
        System.out.println("___________________________________________________________________________");
    }

    public int[] validateUserSelection(String userInput) {
        if(userInput.length()<2){
            return null;
        }
        String[] eventSplit = userInput.split("-");
        int[] eventNumber = new int[eventSplit.length];
        try  {
            for (int i = 0; i < eventSplit.length; i++) {
                eventNumber[i] = Integer.parseInt(eventSplit[i]);
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid selection entered");
            return null;
        }
        if(eventNumber.length<2)return null;
        if(eventNumber.length>2&& eventNumber.length<4) return null;
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

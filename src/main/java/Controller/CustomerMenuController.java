package Controller;

import Dummy.Database;
import Model.Location;
import Model.Organizer;
import Model.PlannedEvent;
import Model.Room;



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
    public void enterCustomerMenu() {
        System.out.println("_____________________________________________________________________");
        for(int i = 0;i<Database.organizers.size();i++) {
            System.out.println("*********************************************************************");
            System.out.println("Arranger name: " + Database.organizers.get(i).getOrganization());

            for (int j = 0; j < Database.organizers.get(i).getLocations().size();j++) { ;
                System.out.println("Playing at: " + Database.organizers.get(i)
                        .getLocations().get(j).getName());
                for(int k = 0; k < Database.organizers.get(i).getLocations().get(j).getRooms().size();k++){
                    System.out.println("        _______________________________________________________");
                    System.out.println("        In room: " + Database.organizers.get(i).getLocations()
                            .get(j).getRooms().get(k).getName());
                    for(int m = 0; m<Database.organizers.get(i).getLocations().get(j)
                            .getRooms().get(k).getEvents().size();m++){
                        System.out.println("            _________________________________________________");
                        PlannedEvent plannedEvent = Database.organizers.get(i).getLocations().get(j).getRooms()
                                .get(k).getEvents()
                                .get(m);
                        System.out.println("            " + plannedEvent.getNameOfEvent()
                                + " " + plannedEvent.getDateOfEvent().toLocalDate().toString() + " \n" +
                                "            Starting at: " + plannedEvent.getDateOfEvent().toLocalTime().getHourOfDay()
                                + ":" +plannedEvent.getDateOfEvent().getMinuteOfHour() + " "
                                + plannedEvent.getLengthOfEvent() +  "min runtime \n" +
                                "            Event number: (" + (i) + (j) + (k) + (m) + ")");

                    }
            }
        }

        }
    }

    public boolean validateUserSelection(String userInput) {
        if(userInput.length()<4){
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
        return false;
    }

    // Control that that valid int selection exist in the database
    private Boolean checkIfEventExist(int[] eventNumber) {
        // TODO Migrate to real db
        // TODO Query
        if(Database.organizers.contains(eventNumber[0])){
            if (Database.organizers.get(eventNumber[0]).getLocations().contains(eventNumber[1])) {
                if (Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms().contains(eventNumber[2])){
                    if(Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                            .get(eventNumber[2]).getEvents().contains(eventNumber[3])){
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private void selectEvent(int[] eventNumber){
        PlannedEvent plannedEvent = Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                .get(eventNumber[2]).getEvents()
                .get(eventNumber[3]);
    }
}

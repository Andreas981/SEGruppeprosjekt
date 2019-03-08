package Controller;

import Dummy.Database;
import Model.Location;
import Model.Organizer;
import Model.PlannedEvent;
import Model.Room;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


public class CustomerMenuController {
    private Scanner scanner = new Scanner(System.in);

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
            askForUserSelection();

    }

    //TODO SOUT IN VIEW of customerMenu
    private void askForUserSelection() {
        System.out.println("Please enter the event number of the performance you wish to purchase tickets to.");
        // TODO Input validation
        String eventNumberInput = scanner.next();
        if(eventNumberInput.length()<4) {
            System.out.println("Invalid selection entered");
            enterCustomerMenu();
        }else{
            validateUserSelection(eventNumberInput);
        }
    }

    private void validateUserSelection(String userInput) {
        String[] eventSplit = userInput.split("");
        int[] eventNumber = new int[eventSplit.length];
        try  {
            for (int i = 0; i < eventSplit.length; i++) {
                eventNumber[i] = Integer.parseInt(eventSplit[i]);
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid selection entered");
            enterCustomerMenu();
        }
        if(checkIfEventExist(eventNumber)){
            // Grab the event and send to order controller
            System.out.println("OKAY");
        }
        else{
            System.out.println("The event you have entered does not exist");
            enterCustomerMenu();
        }

    }

    // Control that that valid int selection exist in the database
    private Boolean checkIfEventExist(int[] eventNumber) {
        // TODO Migrate to real db
        // TODO Query
        for (int i = 0; i < eventNumber.length;i++){
            System.out.println(eventNumber[i]);
        }
        if(Database.organizers.indexOf(eventNumber[0]) != -1){
            if (Database.organizers.get(eventNumber[0]).getLocations().indexOf(eventNumber[1]) != -1) {
                if (Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms().indexOf(eventNumber[2]) != -1){
                    if(Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                            .get(eventNumber[2]).getEvents().indexOf(eventNumber[3]) != -1){
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

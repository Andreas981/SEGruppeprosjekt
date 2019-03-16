package Controller;

import Dummy.Database;
import Model.SeatedPlannedEvent;
import Model.Ticket;
import org.joda.time.LocalDateTime;

public class SeatedEventController {

    public void addSeatedEvent(int location, int room, String name, String date, String time, int ageLimit, int lengthOfEvent) {
        String[] dateArray = date.split("-");
        String[] timeArray = time.split("-");
        Database.currentLoggedInOrganizer
                .getLocations()
                .get(location)
                .getRooms()
                .get(room)
                .getEvents()
                .add(new SeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1])), lengthOfEvent, ageLimit));

        addTicketsToEvent(location, room);
    }

    private void addTicketsToEvent(int location, int room){

        SeatedPlannedEvent currentEvent = Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(
                Database.currentLoggedInOrganizer
                .getLocations()
                .get(location)
                .getRooms()
                .get(room)
                .getEvents().size()-1);

        System.out.println("ADDING");

            for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getMaxParticipents(); i++) {
                System.out.println("FOR");
                Database.currentLoggedInOrganizer.getLocations()
                        .get(location)
                        .getRooms()
                        .get(room)
                        .getEvents()
                        .get(Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().size() - 1)
                        .getTickets()
                        .add(new Ticket("ID", currentEvent, 100, i));
            }

                System.out.println("Size: " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().size() - 1).getTickets().size());

    }

    public void removeSeatedEvent(int location, int room, int event) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().remove(event);
    }

    public void editSeatedEvent(int location, int room, int event, String name, String date, String lengthOfEvent, String ageLimit) {
        String patternForDate = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
        if(!name.equals("."))
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).setNameOfEvent(name);
        if(!date.equals(".") && Security.RegEx.regEx(patternForDate, date)){
            String[] dateArray = date.split("-");
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).setDateOfEvent(new LocalDateTime(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), 00, 00));
        }
        if(!lengthOfEvent.equals(".") && Integer.parseInt(lengthOfEvent) > 0)
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).setLengthOfEvent(Integer.parseInt(lengthOfEvent));
        if(!ageLimit.equals(".") && Integer.parseInt(ageLimit) > 0)
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).setAgeLimit(Integer.parseInt(ageLimit));
    }
}

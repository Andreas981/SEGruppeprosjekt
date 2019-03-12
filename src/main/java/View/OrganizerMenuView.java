package View;

import Dummy.Database;

public class OrganizerMenuView {



    public void displayPromptForEventRemoved() {
        System.out.println("Event removed");
    }

    public void displayPromptForWhichEventToRemove() {
        System.out.println("Which event do you want to remove?");
    }


    public void displayPromptForSeeEvent() {
        System.out.println("Which event do you want to see?");
    }

    public void displayPromptForAddEventName() {
        System.out.println("\nName of event: ");
    }

    public void displayPromptForAddEventDate() {
        System.out.println("Date of event (YYYY-MM-DD): ");
    }

    public void displayPromptForAddEventTime() {
        System.out.println("Start time of event (HH-MM): ");
    }

    public void displayPromptForAddEventAgeLimit() {
        System.out.println("Age limit");
    }

    public void displayPromptForAddEventAdded() {
        System.out.println("Event added");
    }

    public void displayPromptForSeeEventDetail(int location, int room, int event) {
        displayPromptForStars();
        System.out.println("\tName:      " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).getNameOfEvent());
        System.out.println("\tDate:      " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).getDateOfEvent().toLocalDate().toString() +
                " " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).getDateOfEvent().toLocalTime().toString());
        System.out.println("\tDurance:   " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).getLengthOfEvent());
        System.out.println("\tAge limit: " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEvents().get(event).getAgeLimit());
        displayPromptForStars();
    }

    public void displayPromptForAddEventLength() {
        System.out.println("Length of event (in houres): ");
    }
}

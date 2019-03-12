package View;

import Dummy.Database;

public class OrganizerMenuView {

    public void displayPromptForSpesificLocationMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(0) See a room");
        System.out.println("\t(1) Delete a room");
        System.out.println("\t(2) Add a room");
        System.out.println("\t(3) Edit a room");
        System.out.println("\t(4) Go back");
    }

    public void displayPromptForRoomName() {
        System.out.println("\nRoom name: ");
    }

    public void displayPromptForMaxParticipents() {
        System.out.println("Max participents:");
    }

    public void displayPromptForRoomAdded() {
        System.out.println("Room added...");
    }

    public void displayPromptForWhichRoomToDelete() {
        System.out.println("Which room do you want to delete?");
    }

    public void displayPromptForRoomRemoved() {
        System.out.println("Room removed");
    }

    public void displayPromptForSeeingSpesificRoom() {
        System.out.println("Which room do you want to see?");
    }

    public void displayPromptForRoomDetails(int location, int room) {
        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getName() +
                "\nMax participents: " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getMaxParticipents() +
                "\nEvent in progress: " + Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).getEventInProgress());
    }

    public void displayPromptForNotEditField() {
        System.out.println("\nWrite \"-1\" if you dont want to edit a field");
    }

    public void displayPromptForNewRoomName() {
        System.out.println("Name: ");
    }

    public void displayPromptForNewMaxParticipents() {
        System.out.println("Max participents: ");
    }

    public void displayPromptForRoomEdited() {
        System.out.println("Room edited");
    }

    public void displayPromptForWhichRoomToEdit() {
        System.out.println("Which room?");
    }

    public void displayPromptForEventRemoved() {
        System.out.println("Event removed");
    }

    public void displayPromptForStars() {
        System.out.println("****************************");
    }

    public void displayPromptForWhichEventToRemove() {
        System.out.println("Which event do you want to remove?");
    }

    public void displayPromptForEventsInRoom() {
        System.out.println("Events in this room: ");
    }

    public void displayPromptForEventMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(0) See event");
        System.out.println("\t(1) Add event");
        System.out.println("\t(2) Remove event");
        System.out.println("\t(3) Edit event");
        System.out.println("\t(4) Go back");
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

package View;

import Dummy.Database;


public class OrganizerMenu {

    public void displayPromptForLevelTwoOrganizer() {

        //The menu that the organizer will see
        System.out.println("\nWhat would you like to do?");
        System.out.println("(1) See locations");
        System.out.println("(2) Add location");
        System.out.println("(3) Sign out");

    }

    public void listRegisteredLocations(String registeredLocations) {
        System.out.println(registeredLocations);
    }

    public void promptLocationMenu() {

        System.out.println("\nWhat would you do?");
        System.out.println("\t(1) See a location");
        System.out.println("\t(2) Add a location");
        System.out.println("\t(3) Remove a location");
        System.out.println("\t(4) Go back");

    }

    public void displayPromptForLocationName(){
        System.out.println("\nName of location?");
    }

    public void displayPromptForLocationAddress() {
        System.out.println("Location address?");
    }

    public void displayPromptForLocationPublic() {
        System.out.println("Is it a public location? (1)yes (2)no");
    }

    public void displayPromptForSpesificLocation() {
        System.out.println("\nWhich location do you want to see?");
    }

    public void displayPromptForRemovingLocation() {
        System.out.println("Which location do you want to remove?");
    }

    public void displayPromptForAreYouSure() {
        System.out.println("Are you sure?");
        System.out.println("yes(y)/no(n)");
    }

    public void displayPromptForLocationDeleted() {
        System.out.println("Location deleted!");
    }

    public void displayPromptForSpesicficLocationDetails(int location) {
        System.out.println("\n****************************");
        System.out.println("Name: "+Database.currentLoggedInOrganizer.getLocations().get(location).getName() +
                "\nAddress: "+Database.currentLoggedInOrganizer.getLocations().get(location).getAddress() +
                "\nIs public: "+Database.currentLoggedInOrganizer.getLocations().get(location).getPublicLocation()
        );
    }

    public void displayPromptForNoRegisteredRooms() {
        System.out.println("Thre is no registered rooms for this locaton");
    }

    public void displayPromptForRoomsInSpesificLocation() {
        System.out.println("Rooms for this location: ");
    }

    public void displayPromptForNotAnOption() {
        System.out.println("Sorry, that is not an option");
    }

    public void displayPromptForRegisteredLocation() {
        System.out.println("\nThis is you're registered locations: ");
    }

    public void displayPromptForSomethingWentWrong() {
        System.out.println("Something went wrong");
    }

    public void displayPromptForLocationAdded() {
        System.out.println("Location added");
    }

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
}

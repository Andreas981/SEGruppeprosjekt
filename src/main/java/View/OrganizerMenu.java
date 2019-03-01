package View;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    public void displayPromptForLevelTwoOrganizer() {

        //The menu that the organizer will see
        System.out.println("What would you like to do?");
        System.out.println("(1) See locations");
        System.out.println("(2) Add location");

    }

    public void listRegisteredLocations(String registeredLocations) {
        System.out.println(registeredLocations);
    }

    public void promptLocationMenu() {

        System.out.println("\nWhat would you do?");
        System.out.println("(1) See a location");
        System.out.println("(2) Add a location");
        System.out.println("(3) Remove a location");
        System.out.println("(4) Go back");

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
        System.out.println("\nName: "+Database.currentLoggedInOrganizer.getLocations().get(location).getName() +
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
}

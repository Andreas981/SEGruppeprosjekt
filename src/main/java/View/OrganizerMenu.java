package View;

import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrganizerMenu {

    public void promptMenuLevelTwo() {

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
}

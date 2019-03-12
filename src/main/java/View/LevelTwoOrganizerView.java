package View;

import Controller.LevelTwoOrganizerController;
import Controller.LocationController;
import Controller.RoomController;
import Dummy.Database;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LevelTwoOrganizerView {

    private Scanner scanner = new Scanner(System.in);
    private LocationController locationController = new LocationController();
    private RoomController roomController = new RoomController();
    private int currentLocation = -1;
    private int currentRoom = -1;

    public void startView() {
        System.out.println("This is your registred locations: ");
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().size(); i++)
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(i).getName());

        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(1) See a location");
        System.out.println("\t(2) Add location");
        System.out.println("\t(3) Remove a location");
        System.out.println("\t(4) Edit room");
        System.out.println("\t(5) Sign out");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    seeLocation();
                    break;
                case 2:
                    addLocation();
                    break;
                case 3:
                    removeLocation();
                    break;
                case 4:
                    editLocation();
                    break;
                case 5:
                    Database.currentLoggedInOrganizer = null;
                    return;
                default:
                    displayNotAnOption();
                    break;
            }
        }catch (InputMismatchException e){
            displayNotAnOption();
        }
        startView();
    }

    private void seeLocation(){
        System.out.println("\nWhich location do you want to see?");

        int choice = -1;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            startView();
        }

        if(choice > -1 && locationController.checkExistance(choice)){
            currentLocation = choice;
            seeLocationView();
        }else {
            displaySomethingWentWrong();
            startView();
        }

    }
    private void addLocation() {
        System.out.println("\nName of location?");
        String name = new Scanner(System.in).next();
        System.out.println("Location address?");
        String address = new Scanner(System.in).next();
        System.out.println("Is it a public location? (1)yes (2)no");
        int publicLocaiton = -1;
        try{
            publicLocaiton = new Scanner(System.in).nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
        }

        if(publicLocaiton == 1 || publicLocaiton == 2){
            if(locationController.addLocation(name, address, publicLocaiton)){
                System.out.println("Location added!");
                startView();
            }else{
                displaySomethingWentWrong();
                startView();
            }
        }

    }
    private void removeLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which location do you want to remove?");

        int choice;
        try{
            choice = scanner.nextInt();
            System.out.println("Are you sure?");
            System.out.println("yes(y)/no(n)");

            String confirm = scanner.next();
            if (confirm.toLowerCase().startsWith("n")){
                startView();
            }else if(confirm.toLowerCase().startsWith("y")){
                if(locationController.removeLocation(choice)){
                    System.out.println("Location removed!");
                }else{
                    displaySomethingWentWrong();
                }
                startView();
            }
        }catch (InputMismatchException e){
            displayNotAnOption();
            startView();
        }
    }
    private void editLocation(){
        System.out.println("\nWhich location do you want to remove?");

        int location = -1;
        try{
            location = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            startView();
        }

        if (location >  -1 && location < Database.currentLoggedInOrganizer.getLocations().size()){
            System.out.println("Type \".\" if you don't want to change a field");

            System.out.println("Name: ");
            String name = new Scanner(System.in).nextLine();

            System.out.println("Address: ");
            String address = new Scanner(System.in).nextLine();

            System.out.println("Public location (yes/no): ");
            String publicLocation = new Scanner(System.in).next();

            locationController.editLocaiton(location, name, address, publicLocation);

            System.out.println("Location edited!");
        }else{
            displaySomethingWentWrong();
        }
        startView();

    }

    private void seeLocationView() {
        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getName() +
                "\nAddress: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getAddress() +
                "\nIs public: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getPublicLocation()
        );
        if (Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size() == 0){
            System.out.println("Thre is no registered rooms for this locaton");
        }else{
            System.out.println("Rooms for this location: ");
            for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size(); i++){
                System.out.println("\t(" + i + ")" + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(i).getName());
            }
        }
        System.out.println("****************************");


        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(1) See a room");
        System.out.println("\t(2) Add a room");
        System.out.println("\t(3) Edit a room");
        System.out.println("\t(4) Remove a room");
        System.out.println("\t(5) Go back");

        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeLocationView();
        }
        switch (choice){
            case 1:
                seeRoom();
                break;
            case 2:
                addRoom();
                break;
            case 3:
                removeRoom();
                break;
            case 4:
                editRoom();
                break;
            case 5:
                break;
            default:
                displayNotAnOption();
                seeLocationView();
                break;
        }
        seeLocationView();

    }

    private void seeRoom(){
        System.out.println("\nWhich room do you want to see?");

        int choice = -1;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }

        if(choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()){
            currentRoom = choice;
            seeRoomView();
        }else {
            displayNotAnOption();
            currentRoom = -1;
            seeLocationView();
        }

    }
    private void addRoom() {
        System.out.println("\nRoom name: ");
        String name = new Scanner(System.in).nextLine();

        System.out.println("Max participents: ");
        int max = -1;
        try{
            max = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeLocationView();
        }
        roomController.addRoom(currentLocation, name, max);
        System.out.println("Room added!");

        seeLocationView();
    }
    private void removeRoom() {
        System.out.println("\nWhich room do you want to remove?");

        int choice = -1;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeLocationView();
        }
        System.out.println("Are you sure?");
        System.out.println("yes(y)/no(n)");

        String confirm = scanner.next();
        if(confirm.toLowerCase().startsWith("y")){
            if (choice > -1 && choice < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()) {
                roomController.removeRoom(currentLocation, choice);
                System.out.println("Room removed");
            }else{
                displaySomethingWentWrong();
            }
        }

        seeLocationView();


    }
    private void editRoom(){
        System.out.println("Which room do you want to edit?");
        int room;
        try{
            room = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            return;
        }
        if(room >= 0 && room < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().size()){

            System.out.println("Write \".\" if you dont want to change a field");
            System.out.println("Name: ");
            String name = new Scanner(System.in).nextLine();
            String max = scanner.next();

            try{
                Integer.parseInt(max);
            }catch (NumberFormatException e){
                displayNotAnOption();
                return;
            }

            roomController.editRoom(currentLocation, room, name, max);

            System.out.println("Room edited!");
        }else{
            displayNotAnOption();
        }
    }

    private void seeRoomView(){
        System.out.println("\n****************************");
        System.out.println("Name: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getName() +
                "\nMax participents: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getMaxParticipents() +
                "\nEvent in progress: " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEventInProgress());
        System.out.println("Events in this room: ");
        for(int i = 0; i < Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().size(); i++)
            System.out.println("\t(" + i + ") " + Database.currentLoggedInOrganizer.getLocations().get(currentLocation).getRooms().get(currentRoom).getEvents().get(i).getNameOfEvent());
        System.out.println("****************************");

        System.out.println("\nWhat would you like to do?");
        System.out.println("\t(1) See event");
        System.out.println("\t(2) Add event");
        System.out.println("\t(3) Remove event");
        System.out.println("\t(4) Edit event");
        System.out.println("\t(5) Go back");

        int choice = 0;
        try{
            choice = scanner.nextInt();
        }catch (InputMismatchException e){
            displayNotAnOption();
            seeRoomView();
        }

        switch (choice){
            case 1:
                seeEvent();
                break;
            case 2:
                addEvent();
                break;
            case 3:
                removeEvent();
                break;
            case 4:
                editEvent();
                break;
            case 5:
                currentRoom = -1;
                seeLocationView();
                break;
            default:
                displayNotAnOption();
                seeRoomView();
                break;
        }
        seeRoomView();

    }

    private void seeEvent() {

    }
    private void addEvent() {

    }
    private void removeEvent() {

    }
    private void editEvent() {

    }


    private void displayNotAnOption(){
        System.out.println("Sorry, that is not an option");
    }
    private void displaySomethingWentWrong(){
        System.out.println("Something went wrong");
    }
}

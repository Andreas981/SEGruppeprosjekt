package Dummy;


import Model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;




public class InitStart {
    public static void Init() {

        // Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", Security.PassHash.hashPassword("abc123"), new LocalDate(2000, 2, 2), "HiØ", 2));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "oleol", Security.PassHash.hashPassword("abc123"), new LocalDate(2000, 2, 2), "Thon Hotell", 1));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "test1", Security.PassHash.hashPassword("test"), new LocalDate(2000, 2, 2), "Thon Hotell", 1));
        Database.organizers.add(new Organizer("Ole", "Olsen", "Ole.12@gmail.no", "98765432", "test2", Security.PassHash.hashPassword("test"), new LocalDate(2000, 2, 2), "Thon Hotell", 2));


        // Setting up customer
        Database.customers.add(new Customer("Per", "Persen", "per@persen.com", "11223344", "persen", Security.PassHash.hashPassword("abc123"), new LocalDate(2000, 2, 2)));

        // Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        // Setting up locations for Ole Olsen
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Ski", "SkiVeien 123", true));
        Database.organizers.get(1).addLocation(new Location("Thon Hotell Oslo", "OsloVeien 1", true));

        // Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 50, false, 10));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud 4", 50, false, 10));
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("D1-058", 50, true, 10));

        // Dummy event for HIØ Playing at Aud Max
        Database.currentLoggedInOrganizer = Database.organizers.get(0);
        Database.organizers.get(0).addNonSeatedPlannedEvent(new NonSeatedPlannedEvent("Sopptur", new LocalDateTime(2019, 3, 2, 22, 0), 90, 18, 200, "HIOF", false, 2));
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(new SeatedPlannedEvent("Forelesning i Inf. Prog", new LocalDateTime(2019, 3, 2, 22, 0), 200, 20, 0, 0, 100));
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(new SeatedPlannedEvent("Forelesning i OOP", new LocalDateTime(2019, 3, 2, 22, 0), 200, 20, 0, 0, 100));

        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(1).addEvent(new SeatedPlannedEvent("Forelesning i Datasikkerhet", new LocalDateTime(2019, 3, 2, 22, 0), 200, 20, 0, 0, 100));
        Database.organizers.get(0).getLocations().get(0).getRooms().get(1).getEvents().get(0).getTickets().get(1).setAvailable(false);
    }
}
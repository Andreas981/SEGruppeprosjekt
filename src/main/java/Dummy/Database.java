package Dummy;

import Model.Customer;
import Model.Organizer;
import Model.UpcomingEvent;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Organizer> organizers = new ArrayList<Organizer>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<UpcomingEvent> EventPool = new ArrayList<UpcomingEvent>();

    public static Organizer currentLoggedInOrganizer = null;
    public static Customer currentLoggedInCustomer = null;

}

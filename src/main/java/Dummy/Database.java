package Dummy;

import Model.Customer;
import Model.Organizer;
import Model.newEvent;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Organizer> organizers = new ArrayList<Organizer>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<newEvent> EventPool = new ArrayList<newEvent>();

    public static Organizer currentLoggedInOrganizer = null;
    public static Customer currentLoggedInCustomer = null;

}

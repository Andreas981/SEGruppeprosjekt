package Dummy;

import Model.Customer;
import Model.Organizer;
import Model.User;

import java.util.ArrayList;
import java.util.Timer;

public class Database {
    public static ArrayList<Organizer> organizers = new ArrayList<Organizer>();
    public static ArrayList<Customer> customers = new ArrayList<Customer>();

    public static Organizer currentLoggedInOrganizer = null;
    public static Customer currentLoggedInCustomer = null;

}

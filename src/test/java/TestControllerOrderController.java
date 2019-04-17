import Controller.OrderController;
import Controller.SeatedEventController;
import Dummy.Database;
import Dummy.InitStart;
import Model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestControllerOrderController {

    Organizer organizer;
    SeatedEventController seatedEventController = new SeatedEventController();
    OrderController orderController;
    PlannedEvent plannedEvent;
    int[] eventNunber = new int[4];

    @Before
    public void init() {
        // Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", Security.PassHash.hashPassword("abc123"),new LocalDate(2000,2,2), "HiØ", 2));
        // Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        // Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 400, false, 10));
        // Dummy event for HIØ Playing at Aud Max

        // Dummy event for a non seated event
        Database.currentLoggedInOrganizer = Database.organizers.get(0);
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(new SeatedPlannedEvent("Forelesning i Inf. Prog",new LocalDateTime(2019,3,2,22,0),200,20, 0, 0, 100));
        orderController = new OrderController(eventNunber);
        Database.organizers.get(0).addNonSeatedPlannedEvent(new NonSeatedPlannedEvent("NonSeated",new LocalDateTime(2019,3,2,22,0),2,18,200,"Brygga",false,200));

        plannedEvent = orderController.getPlannedEvent();
    }



    // A seated event is tested with room size 400
    @Test
    public void testValidSeatsSelected(){
        orderController.getAvailableSlots();
        Assert.assertTrue(orderController.validateUserInput("1,2,3"));
    }

    @Test
    public void userShouldNotBeAbleToSelectReservedSeat(){
        // Reserve seat:
        Database.organizers.get(0).getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().get(1).setAvailable(false);
        orderController.getAvailableSlots();
        // User selects a reserved seat
        Assert.assertFalse(orderController.validateUserInput("1"));
    }

    @Test
    public void testInvalidSeatsSelected(){
        orderController.getAvailableSlots();
        Assert.assertFalse(orderController.validateUserInput("500"));
    }

    // A non seated event is selected with 200 available tickets
    @Test
    public void userShouldNotBeAbleToSelectMoreTicketsThanAvalible(){
        orderController = new OrderController(new int[]{0,0});
        // User selects more tickets than are available;
        Assert.assertFalse(orderController.validateUserInput("201"));
    }

    @Test
    public void userShouldBeAbleToSelectTicketsThatAreAvaible(){
        orderController = new OrderController(new int[]{0,0});
        // User selects more tickets than are available;
        Assert.assertTrue(orderController.validateUserInput("200"));
    }
 
    @Test
    public void testInvalidInputEntered(){
        orderController.getAvailableSlots();
        Assert.assertFalse(orderController.validateUserInput("abc,33,a"));
    }

    @Test
    public void testGetEventFromDatabase () {
        Assert.assertSame("Forelesning i Inf. Prog",Database.organizers.get(0).getLocations().get(0).getRooms());
    }
}
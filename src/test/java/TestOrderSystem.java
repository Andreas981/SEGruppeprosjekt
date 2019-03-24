import Controller.OrderController;
import Dummy.Database;
import Dummy.InitStart;
import Model.*;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestOrderSystem {

    OrderController orderController;
    Organizer organizer;
    Location location;
    Room room;
    PlannedEvent plannedEvent;

    @Before
    public void init() {
        InitStart.Init();

        organizer = new Organizer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1),"test org",2);
        room = new Room("test romnavn", 123,false,2);
        location = new Location("test navn","test adresse",false);


        location.addRoom(room);
        organizer.addLocation(location);


        orderController = new OrderController(new int[1]);


    }
/*
    @Test
    public void testAEventIsSelected(){
        Assert.assertNotNull(orderController.getPlannedEvent());
    }
*/
    @Test
    public void testSeatedEventContainsSeats(){

    }
}

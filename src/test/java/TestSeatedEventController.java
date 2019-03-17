import Dummy.Database;
import Model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSeatedEventController {

    Location location;
    SeatedPlannedEvent seatedPlannedEvent;
    Room room;
    Organizer organizer;
    Database database;

    @Before
    public void init() {

        organizer = new Organizer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1),"test org",2);
        Database.currentLoggedInOrganizer = organizer;
        organizer.getLocations().add(new Location("test name","test address",false));
        organizer.getLocations().get(0).getRooms().add(new Room("test room name", 123,false,10));
        organizer.getLocations().get(0).getRooms().get(0).getEvents().add(new SeatedPlannedEvent("test name", new LocalDateTime(2002,2,2,12,12),1,18,0,0));

    }

    @Test
    public void TestAddSeatedEvent() {
        /* Checks if array's size is not null/0 */
        Assert.assertNotEquals(null,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0));
    }
}

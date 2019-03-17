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

        location = new Location("test name","test address",false);
        room = new Room("test room name", 123,false,1);
        seatedPlannedEvent = new SeatedPlannedEvent("test name", new LocalDateTime(16),1,18,1,1);
        organizer = new Organizer("test name","test address","test email","12312312",
                "test username", "test password", new LocalDate(1991,1,1),"test org",1);
        room.addEvent(seatedPlannedEvent);
        location.addRoom(room);
        Database.currentLoggedInOrganizer = organizer;
        Database.currentLoggedInOrganizer.addLocation(location);
    }

    @Test
    public void TestAddSeatedEvent() {
        /* Checks if array's size is not null/0 */
        Assert.assertNotEquals(null,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0));
    }
}

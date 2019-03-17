import Dummy.Database;
import Model.*;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSeatedEventController {

    Location location;
    SeatedPlannedEvent seatedPlannedEvent;
    Room room;
    Database database;

    @Before
    public void init() {

        location = new Location("test name","test address",false);
        seatedPlannedEvent = new SeatedPlannedEvent("test name", new LocalDateTime(16),1,18);
        room = new Room("test room name", 123,false);

        room.addEvent(seatedPlannedEvent);
        location.addRoom(room);
        Database.currentLoggedInOrganizer.addLocation(location);

    }

    @Test
    public void TestAddSeatedEvent() {
        /* Checks if array's size is not null/0 */
        Assert.assertNotEquals(null,location.getRooms().get(0).getEvents().get(0));
    }
}

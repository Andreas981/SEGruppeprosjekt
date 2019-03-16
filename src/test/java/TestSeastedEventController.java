import Dummy.Database;
import Model.Location;
import Model.Room;
import Model.SeatedPlannedEvent;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSeastedEventController {

    Location location;
    SeatedPlannedEvent seatedPlannedEvent;
    Database database;
    Room room;

    @Before
    public void init() {
        location = new Location("test navn","test adresse",false);
        seatedPlannedEvent = new SeatedPlannedEvent("test navn", new LocalDateTime(16),1,18);
        database = new Database();
        room = new Room("test romnavn", 123,false);
        
        database.currentLoggedInOrganizer.addLocation(location);
        database.currentLoggedInOrganizer.getLocations().get(0).addRoom(room);
        Database.currentLoggedInOrganizer
                .getLocations()
                .get(0)
                .getRooms()
                .get(0)
                .getEvents()
                .add(seatedPlannedEvent);
    }

    @Test
    public void TestAddSeatedEvent() {
        /* Checks if array's size is not null/0 */
        Assert.assertNull(Database.currentLoggedInOrganizer
                .getLocations()
                .get(0)
                .getRooms()
                .get(0)
                .getEvents().size());

    }
}

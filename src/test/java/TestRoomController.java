import Controller.RoomController;
import Dummy.Database;
import Model.Location;
import Model.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRoomController {

    RoomController roomController;
    Room room;
    Location location;

    @Before
    public void init() {
        roomController = new RoomController();
        room = new Room("Rom 1", 123,false);
        location = new Location("test navn","test adresse",false);
    }

    @Test
    public void TestAddRoomInExistingLocation() {

        Assert.assertEquals(Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().add
                (room.getName(),room.getMaxParticipents(),room.getEventInProgress()));

    }


}

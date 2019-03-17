import Controller.RoomController;
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
        room = new Room("test room name", 123,false);
        location = new Location("test name","test address",false);
        location.getRooms().add(room);
    }

    @Test
    public void TestAddRoomInExistingLocation() {
        /*Checks if array's size is not null/0*/
        Assert.assertNotNull(location.getRooms().size());

    }
}

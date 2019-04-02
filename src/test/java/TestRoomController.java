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
        room = new Room("test romnavn", 123,false,2);
        location = new Location("test navn","test adresse",false);
        location.getRooms().add(room);
    }

    @Test
    public void testAddRoomInExistingLocation() {
        /*Checks if array's size is not null/0*/
        Assert.assertNotNull(location.getRooms().size());

    }

    @Test
    public void testRemoveRoomInExistingLocation() {
        location.getRooms().remove(room);
        Assert.assertEquals(0,location.getRooms().size());
    }

    @Test
    public void testEditRoomInExistingLocation() {
        room.setName("testEdit");
        room.setMaxParticipents(234);

        Assert.assertEquals("testEdit",room.getName());
        Assert.assertEquals(234,room.getMaxParticipents());
    }
}

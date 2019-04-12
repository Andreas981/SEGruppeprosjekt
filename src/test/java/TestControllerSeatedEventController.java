import Controller.SeatedEventController;
import Dummy.Database;
import Model.Location;
import Model.Organizer;
import Model.Room;
import Model.SeatedPlannedEvent;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestControllerSeatedEventController {

    Organizer organizer;
    SeatedEventController seatedEventController = new SeatedEventController();

    @Before
    public void initTest(){
        organizer = new Organizer("test name","test address","test email","12312312", "test username", "test password", new LocalDate(1991,1,1),"test org",2);
        Database.currentLoggedInOrganizer = organizer;
        organizer.getLocations().add(new Location("Location Name", "Location Address", true));
        organizer.getLocations().get(0).getRooms().add(new Room("Room Name", 100, false, 10));
        seatedEventController.addSeatedEvent(0,0, "NAme", "2002-02-02", "12-12", 12, 12, 100);
    }

    @Test
    public void testGettingSeatNumberForAticketInEvent(){
        System.out.println(organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().size());
        Assert.assertEquals(3, organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().get(3).getSeatNumber());
    }

    @Test
    public void testAddSeatedEvent() {
        /* Checks if array's size is not null/0 */
        Assert.assertNotEquals(null,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0));
    }

    @Test
    public void testRemoveSeatedEvent() {
        Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().remove(0);
        Assert.assertEquals(0,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().size());
    }

    @Test
    public void testEditSeatedEvent() {
        Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).setNameOfEvent("Test edit");
        Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).setAgeLimit(1);
        Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).setDateOfEvent(new LocalDateTime(1990,1,1,1,1));
        Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).setLengthOfEvent(1);

        Assert.assertEquals("Test edit",Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getNameOfEvent());
        Assert.assertEquals(1,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getAgeLimit());
        Assert.assertEquals(new LocalDateTime(1990,1,1,1,1),Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getDateOfEvent());
        Assert.assertEquals(1,Database.currentLoggedInOrganizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getLengthOfEvent());
    }
}
/*

*/
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

public class TestLevelTwoOrganizer {

    private Organizer organizer;

    @Before
    public void initTests(){
        organizer = new Organizer("Per", "Persen", "Mail", "wewewe", "username", "password", new LocalDate(2000,2,2), "Org", 2);
        Database.currentLoggedInOrganizer = organizer;
    }

    @Test
    public void testOrganizerCanAddNewEventInNewRoomToNewLocation(){

        organizer.getLocations().add(new Location("Location Name", "Location Address", true));
        Assert.assertEquals("Location Name", organizer.getLocations().get(0).getName());

        organizer.getLocations().get(0).getRooms().add(new Room("Room Name", 100, false, 10));
        Assert.assertEquals("Room Name", organizer.getLocations().get(0).getRooms().get(0).getName());

        organizer.getLocations().get(0).getRooms().get(0).addEvent(new SeatedPlannedEvent("Event Name", new LocalDateTime(2000,2,2,2,2), 12, 18, 0, 0));
        Assert.assertEquals("Event Name", organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getNameOfEvent());

    }

}

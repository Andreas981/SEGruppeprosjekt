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



public class TestEventCreation {

    Organizer organizer;

    @Before
    public void initTest(){
        organizer = new Organizer("Per", "Persen", "Mail", "wewewe", "username", "password", new LocalDate(2000,2,2), "Org", 2);

        organizer.getLocations().add(new Location("Location Name", "Location Address", true));
        organizer.getLocations().get(0).getRooms().add(new Room("Room Name", 100, false, 10));

    }

    @Test
    public void gettingSeatNumberForAticketInEvent(){
        organizer.getLocations().get(0).getRooms().get(0).getEvents().add(new SeatedPlannedEvent("Event Name", new LocalDateTime(2019,2,2,2,2), 100, 18));
        System.out.println(organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().size());
        Assert.assertEquals(2, organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().get(3).getSeatNumber());
    }

    // TODO Test for ticketbuying
    // TODO Test
}

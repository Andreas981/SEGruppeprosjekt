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



public class TestEventCreation {

    Organizer organizer;
    SeatedEventController seatedEventController = new SeatedEventController();

    @Before
    public void initTest(){
        organizer = new Organizer("Per", "Persen", "Mail", "wewewe", "username", "password", new LocalDate(2000,2,2), "Org", 2);
        Database.currentLoggedInOrganizer = organizer;

        organizer.getLocations().add(new Location("Location Name", "Location Address", true));
        organizer.getLocations().get(0).getRooms().add(new Room("Room Name", 100, false, 10));

        seatedEventController.addSeatedEvent(0,0, "NAme", "2002-02-02", "12-12", 12, 12, 100);
    }

    @Test
    public void gettingSeatNumberForAticketInEvent(){
        System.out.println(organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().size());
        Assert.assertEquals(3, organizer.getLocations().get(0).getRooms().get(0).getEvents().get(0).getTickets().get(3).getSeatNumber());
    }

    // TODO Test for ticketbuying
    // TODO Test
}

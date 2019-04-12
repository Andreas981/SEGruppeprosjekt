import Controller.CustomerEventSelectionController;
import Dummy.Database;
import Dummy.InitStart;
import Model.Location;
import Model.Organizer;
import Model.Room;
import Model.SeatedPlannedEvent;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestControllerCustomerEventSelectionController {
    CustomerEventSelectionController customerEventSelectionController;
    int[] validSelectionNumber = {0,0,0,0};

    @Before
    public void initTests(){
        /* Insert a dummy event into database
         * Since its the first element the address should be 0-0-0-0 */
        // Setting up organizers
        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", Security.PassHash.hashPassword("abc123"),new LocalDate(2000,2,2), "HiØ", 2));
        // Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        // Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 400, false, 10));
        // Dummy event for HIØ Playing at Aud Max
        Database.currentLoggedInOrganizer = Database.organizers.get(0);
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(new SeatedPlannedEvent("Forelesning i Inf. Prog",new LocalDateTime(2019,3,2,22,0),200,20, 0, 0, 100));
        customerEventSelectionController = new CustomerEventSelectionController();
        //InitStart.Init();
    }

    @Test
    public void enterInvalidEventnumber(){
        Assert.assertNull(null,customerEventSelectionController.validateUserSelection("A-B-C-D"));
    }

    @Test
    public void enterValidNumberButEventDontExist(){
        Assert.assertNull(null,customerEventSelectionController.validateUserSelection("5-0-0-0"));
    }

    @Test
    public void enterValidNumberForAEvent(){
        Assert.assertArrayEquals(validSelectionNumber,customerEventSelectionController.validateUserSelection("0-0-0-0"));
    }

    // TODO Init test


}

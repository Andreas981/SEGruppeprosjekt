import Controller.PaymentController;
import Dummy.Database;
import Model.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestControllerPaymentController {

    Order orderSeated;
    Order nonSeated;
    SeatedPlannedEvent seatedPlannedEvent;
    PaymentController paymentController;


    @Before
    public void init() {
        ArrayList<Integer> slots = new ArrayList();
        slots.add(2);


        Database.organizers.add(new Organizer("Kari", "Normann", "kari@normann.no", "12345678", "karino", Security.PassHash.hashPassword("abc123"),new LocalDate(2000,2,2), "HiØ", 2));
        // Setting up locations for Kari Normann
        Database.organizers.get(0).addLocation(new Location("Høgskolen i Østfold", "Veien 12", true));
        // Setting up rooms for Kari Normann
        Database.organizers.get(0).getLocations().get(0).addRoom(new Room("Aud Max", 400, false, 10));
        // Dummy event for HIØ Playing at Aud Max
        Database.currentLoggedInOrganizer = Database.organizers.get(0);
        SeatedPlannedEvent seatedPlannedEvent = new SeatedPlannedEvent("Forelesning i Inf. Prog",new LocalDateTime(2019,3,2,22,0),200,20, 0, 0, 100);
        NonSeatedPlannedEvent  nonSeatedPlannedEvent = new NonSeatedPlannedEvent("test name",
                new LocalDateTime(1,2,3,4,5),1, 18,2,
                "test address",true, 100);
        Database.organizers.get(0).getLocations().get(0).getRooms()
                .get(0).addEvent(seatedPlannedEvent);
        Database.organizers.get(0).addNonSeatedPlannedEvent(nonSeatedPlannedEvent);
        nonSeated = new Order(slots,nonSeatedPlannedEvent);
        orderSeated = new Order(slots,seatedPlannedEvent);

    }

    @Test
    public void testGetAmountOfOrderForSeatedEvent() {
        paymentController = new PaymentController(this.orderSeated);
        // Since the slot is "2" it is treated as a seat selection
        Assert.assertEquals(100,paymentController.getAmountOfOrder());
    }

    @Test
    public void testGetAmountOfOrderForNonSeatedEvent() {
        paymentController = new PaymentController(this.nonSeated);
        // The customer orders "2" tickets
        Assert.assertEquals(200,paymentController.getAmountOfOrder());
    }


}

import Model.Order;
import Model.SeatedPlannedEvent;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestControllerPaymentController {

    Order order;
    SeatedPlannedEvent seatedPlannedEvent;

    @Before
    public void init() {
        ArrayList<Integer> slots = new ArrayList();
        slots.add(2);
        seatedPlannedEvent = new SeatedPlannedEvent("test name",
                new LocalDateTime(1990,1,1,1,1,1),1,1,0,0,1);

        order = new Order(slots,seatedPlannedEvent);
    }

    @Test
    public void testGetAmountOfOrder() {
        Assert.assertEquals(2,order.getAmountDueInNOK());
    }


}

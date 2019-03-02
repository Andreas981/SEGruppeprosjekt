import Model.SeatedPlannedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;

public class TestSeatedPlannedEvent {

    // TODO Test case for viewing sold tickets

    @Test
    public void createAseatedEvent(){
        SeatedPlannedEvent seatedPlannedEvent = new SeatedPlannedEvent("TestEvent","2-3-2019",new Time(22),4,22);
        Assert.assertEquals(seatedPlannedEvent,seatedPlannedEvent);
    }
}

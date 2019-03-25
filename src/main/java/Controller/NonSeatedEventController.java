package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import org.joda.time.LocalDateTime;

public class NonSeatedEventController {

    public void addNonSeatedEvent(String name, String date, String time, int lengthOfEvent, int ageLimit,
                                  int freeSpace, String address, String freeEvent) {
        String[] dateArray = date.split("-");
        String[] timeArray = time.split("-");
        Database.currentLoggedInOrganizer
                .getNonSeatedPlannedEvents()
                .add((new NonSeatedPlannedEvent(name, new LocalDateTime(Integer.parseInt(dateArray[0]),
                        Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]), Integer.parseInt(timeArray[0]),
                        Integer.parseInt(timeArray[1])), lengthOfEvent, ageLimit, freeSpace, address,
                        (freeEvent.toLowerCase().equals("y")))));
    }
}

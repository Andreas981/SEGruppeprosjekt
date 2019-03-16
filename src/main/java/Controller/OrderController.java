package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.PlannedEvent;
import Model.SeatedPlannedEvent;

public class OrderController {
    private int[] eventNumber;
    private PlannedEvent plannedEvent;
    private SeatedPlannedEvent seatedPlannedEvent;
    private NonSeatedPlannedEvent nonSeatedPlannedEvent;

    public OrderController(int[] eventNumber) {
        this.eventNumber = eventNumber;
        getEventFromDatabase(eventNumber);
    }

    public int[] getEventNumber() {
        return eventNumber;
    }

    public void getEventFromDatabase(int[] eventNumber){
         plannedEvent = Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                .get(eventNumber[2]).getEvents()
                .get(eventNumber[3]);
    }

    public PlannedEvent getPlannedEvent() {
        return plannedEvent;
    }

    public void getAvailableSlots() {
        if( plannedEvent instanceof NonSeatedPlannedEvent){
            plannedEvent = (NonSeatedPlannedEvent) getPlannedEvent();
        }
        else if(plannedEvent instanceof SeatedPlannedEvent){
            displayTickets();
        }
    }

    private void displayTickets(){
        seatedPlannedEvent = (SeatedPlannedEvent) plannedEvent;
        for (int i = 0; i<seatedPlannedEvent.getTickets().size();i++){
            System.out.print(seatedPlannedEvent.getTickets().get(i));

        }

    }

    public boolean checkIfPositionIsTaken(int[]...a){
        return false;
    }
}

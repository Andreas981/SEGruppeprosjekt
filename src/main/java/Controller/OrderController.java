package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.Order;
import Model.PlannedEvent;
import Model.SeatedPlannedEvent;

import java.util.ArrayList;

public class OrderController {
    private int[] eventNumber;
    private PlannedEvent plannedEvent;
    private SeatedPlannedEvent seatedPlannedEvent;
    private NonSeatedPlannedEvent nonSeatedPlannedEvent;

    public Order getPlaceOrder() {
        return placeOrder;
    }

    private Order placeOrder;

    public OrderController(int[] eventNumber) {
        this.eventNumber = eventNumber;
        getEventFromDatabase(eventNumber);
    }

    public int[] getEventNumber() {
        return eventNumber;
    }

    public void getEventFromDatabase(int[] eventNumber){
        if(eventNumber.length<3){
            plannedEvent = Database.organizers.get(eventNumber[0]).getNonSeatedPlannedEvents().get(eventNumber[1]);

        }else
         plannedEvent = Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                .get(eventNumber[2]).getEvents()
                .get(eventNumber[3]);
    }

    public PlannedEvent getPlannedEvent() {
        return plannedEvent;
    }

    // Available tickets
    public void getAvailableSlots() {
        if( plannedEvent instanceof NonSeatedPlannedEvent){
            displayAvalibleSlots((NonSeatedPlannedEvent) plannedEvent);
        }
        else if(plannedEvent instanceof SeatedPlannedEvent){
            displayTickets();
        }
    }

    private void displayAvalibleSlots(NonSeatedPlannedEvent plannedEvent) {
        System.out.println(plannedEvent.getFreeSpace() + " tickets available.");
        System.out.println("Enter how many reservations you want:");
        System.out.println("Example: 1 or 5");
    }

    private void displayTickets(){
        System.out.println("Enter how many seats you want:");
        System.out.println("Example: 1,2,3");
        seatedPlannedEvent = (SeatedPlannedEvent) plannedEvent;

        for (int i = 0; i < seatedPlannedEvent.getTickets().size(); i++){
            if( i%30 == 0 && i != 0) System.out.println(" ");
            if(!seatedPlannedEvent.getTickets().get(i).getAvailable()){
                // TODO Format output in columns
                System.out.format("%5s", "X");
            }else{
                System.out.print((seatedPlannedEvent.getTickets().get(i).getSeatNumber())+ "   ,");
            }
            // TODO Replace with row number
            if(i%30==0&&i!=0) System.out.println(" ");
        }

    }

    private boolean checkSlots(NonSeatedPlannedEvent nonSeatedPlannedEvent) {
        return (nonSeatedPlannedEvent.getFreeSpace()>0);
    }

    public boolean validateUserInput(String reservation){
        if(reservation.length()<1) return false;

        String[] strings = reservation.split(",");

        ArrayList<Integer> slots = parseInputForSlots(strings);
        // If there was no numbers extracted from the input:
        if(slots == null|| slots.size()<1){
            return false;
        }
        if(getPlannedEvent() instanceof  NonSeatedPlannedEvent){
            // Check that the amount of slots are valid
            if(((NonSeatedPlannedEvent) getPlannedEvent()).getFreeSpace()<slots.get(0)|| slots.get(0)<1){
                return false;
            }
            // If the user has entered a number for a sold out event
            if(!checkSlots((NonSeatedPlannedEvent)getPlannedEvent()));
        }
        setupAorder(slots);
        // If the event selected is a NonSeatedPlannedEvent, we don't need to check seats
        if(getPlannedEvent() instanceof  NonSeatedPlannedEvent) return true;
        return checkIfPositionIsTaken(slots);
    }

    public ArrayList<Integer> parseInputForSlots(String[] slots){
        ArrayList<Integer> seats = new ArrayList<Integer>();
        try{
            for (String slot : slots) {
                seats.add(Integer.parseInt(slot));
                // If we discover a negative value, stop parsing the slots:
                if(Integer.parseInt(slot) < 0) return null;
            }
        }catch (NumberFormatException e){
            return null;
        }
        return seats;
    }

    private void setupAorder(ArrayList<Integer> slots) {
        placeOrder = new Order(slots,plannedEvent,getEventNumber());
    }

    private boolean checkIfPositionIsTaken(ArrayList<Integer> seats) {
        for (int i = 0; i < seats.size(); i++) {
            if (!(seatedPlannedEvent.getTickets().size() > seats.get(i)-1)) {
                return false;
            } else {
                if (!seatedPlannedEvent.getTickets().get((seats.get(i))).getAvailable()) return false;
            }

        }
        return true;
    }

    public void setEventNumber(int[] eventNumber) {
        this.eventNumber = eventNumber;
    }

    public void setPlannedEvent(PlannedEvent plannedEvent) {
        this.plannedEvent = plannedEvent;
    }

    public void setSeatedPlannedEvent(SeatedPlannedEvent seatedPlannedEvent) {
        this.seatedPlannedEvent = seatedPlannedEvent;
    }

    public void setNonSeatedPlannedEvent(NonSeatedPlannedEvent nonSeatedPlannedEvent) {
        this.nonSeatedPlannedEvent = nonSeatedPlannedEvent;
    }

    public void setPlaceOrder(Order placeOrder) {
        this.placeOrder = placeOrder;
    }
}

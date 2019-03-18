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
         plannedEvent = Database.organizers.get(eventNumber[0]).getLocations().get(eventNumber[1]).getRooms()
                .get(eventNumber[2]).getEvents()
                .get(eventNumber[3]);
    }

    public PlannedEvent getPlannedEvent() {
        return plannedEvent;
    }

    public void getAvailableSlots() {
        if( plannedEvent instanceof NonSeatedPlannedEvent){
            plannedEvent = getPlannedEvent();
        }
        else if(plannedEvent instanceof SeatedPlannedEvent){
            displayTickets();
        }
    }

    private void displayTickets(){
        seatedPlannedEvent = (SeatedPlannedEvent) plannedEvent;
        for (int i = 0; i<seatedPlannedEvent.getTickets().size();i++){
            if(!seatedPlannedEvent.getTickets().get(i).getAvailable()){
                // TODO Format output in columns
                System.out.print("X   ,");
            }else{
                System.out.print(seatedPlannedEvent.getTickets().get(i).getSeatNumber() + "   ,");
            }
            // TODO Replace with row number
            if(i%30==0&&i!=0) System.out.println(" ");
        }

    }

    public boolean validateUserInput(String seatSelection){
        if(seatSelection.length()<1) return false;
        String[] strings = seatSelection.split(",");

        ArrayList<Integer> seats = parseInputForSeats(strings);
        if(seats == null){
            return false;
        }
        setupAorder(seats);
        return checkIfPositionIsTaken(seats);
    }

    public ArrayList<Integer> parseInputForSeats(String[] slots){
        ArrayList<Integer> seats = new ArrayList<Integer>();
        try{
            for (String slot : slots) {
                seats.add(Integer.parseInt(slot));
            }
        }catch (NumberFormatException e){
            return null;
        }
        return seats;
    }

    private void setupAorder(ArrayList<Integer> slots) {
        placeOrder = new Order(slots,seatedPlannedEvent);
    }

    private boolean checkIfPositionIsTaken(ArrayList<Integer> seats) {
        for (int i = 0; i < seats.size(); i++) {
            if (!(seatedPlannedEvent.getTickets().size() > seats.get(i))) {
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

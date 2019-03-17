package Controller;

import Dummy.Database;
import Model.NonSeatedPlannedEvent;
import Model.PlannedEvent;
import Model.SeatedPlannedEvent;

import java.util.ArrayList;

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

        ArrayList<Integer> seats = new ArrayList<Integer>();
        try{
            for(int i = 0;i<strings.length;i++ ){
                seats.add(Integer.parseInt(strings[i]));
            }
        }catch (NumberFormatException e){
            return false;
        }
        if(checkIfPositionIsTaken(seats)){
            System.out.println("Go to payment");
            return true;
        }
        else{

            return false;
        }

    }

    public boolean checkIfPositionIsTaken(ArrayList<Integer> seats){
        for(int i = 0; i< seats.size();i++){
            if(seatedPlannedEvent.getTickets().get((seats.get(i))).getAvailable() && seatedPlannedEvent.getTickets().size()>i){
                System.out.println("Seat is available");
                // TODO Place in method when order is processed
                seatedPlannedEvent.getTickets().get((seats.get(i))).setAvailable(false);
            }else{
                System.out.println("Seat: " + i + " is taken");
                return false;
            }
        }
        return true;
    }
}

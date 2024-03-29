package Model;

import Dummy.Database;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;


public class NonSeatedPlannedEvent extends PlannedEvent {
    private int freeSpace;
    private String meetUp;
    private Boolean freeEvent;
    private ArrayList<Customer> participents;
    private ArrayList<String> specialNotices;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    // TODO Modified constructor for different events:

    public NonSeatedPlannedEvent(String nameOfEvent, LocalDateTime dateOfEvent, int lengthOfEvent, int ageLimit, int freeSpace, String meetUp, Boolean freeEvent, int price) {
        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.freeSpace = freeSpace;
        this.meetUp = meetUp;
        this.freeEvent = freeEvent;
        participents = new ArrayList<Customer>();
        specialNotices = new ArrayList<String>();

        addTicketsToEvent(price);
    }

    private void addTicketsToEvent(int price){
        for(int i = 0; i < freeSpace; i++) {
            tickets.add(new Ticket("ID", this, price, i));
        }

    }

    public ArrayList<String> getSpecialNotices() {
        return specialNotices;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addParticipent(Customer customer){
        participents.add(customer);
    }
    public void removeParticipents(int index){
        participents.remove(index);
    }
    public ArrayList<Customer> getParticipents(){
        return participents;
    }

    public void addNotice(String notice){
        specialNotices.add(notice);
    }
    public void removeNotice(int index){
        specialNotices.remove(index);
    }
    public ArrayList<String> getNotices(){
        return specialNotices;
    }

    public int getFreeSpace() {
        return freeSpace;
    }
    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }
    public String getMeetUp() {
        return meetUp;
    }
    public void setMeetUp(String meetUp) {
        this.meetUp = meetUp;
    }
    public Boolean getFreeEvent() {
        return freeEvent;
    }
    public void setFreeEvent(Boolean freeEvent) {
        this.freeEvent = freeEvent;
    }


}

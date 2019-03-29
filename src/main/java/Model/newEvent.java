package Model;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class newEvent extends PlannedEvent{

    private int freeSpace;
    private String address;
    private Boolean freeEvent;
    private ArrayList<Customer> participents;
    private ArrayList<String> specialNotices;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private int soldtickets;
    private int price;

    private Room room = null;
    private Location location = null;
    private Organizer organizer;

    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer){
        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;

    }
    //SeatedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, int price, Room room, Location location){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.room = room;
        this.location = location;
        this.price = price;

        this.room.getEvents().add(this);

        setupTickets();

    }
    //NonSeatedPlannedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, int price){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.price = price;
        this.participents = new ArrayList<Customer>();

        setupTickets();

    }

    private void setupTickets(){
        if(isRoomNull()){
            tickets.clear();
            for(int i = 0; i < room.getMaxParticipents(); i++){
                tickets.add(new Ticket("ID", this, price, i));
            }
        }else{
            tickets.clear();
            for(int i = 0; i < freeSpace; i++){
                tickets.add(new Ticket("ID", this, price, i));
            }
        }
    }

    private Boolean isRoomNull(){
        return (room == null);
    }





}

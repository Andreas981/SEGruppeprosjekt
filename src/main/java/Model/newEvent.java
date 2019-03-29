package Model;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

public class newEvent extends PlannedEvent{

    private int amountOfFreeSpace;
    private String address;
    private Boolean freeEvent;
    private ArrayList<Customer> participents;
    private ArrayList<String> specialNotices = new ArrayList<String>();
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private int soldtickets = 0;
    private int price;
    private int eventNumber;

    private Room room;
    private Location location;
    private Organizer organizer;

    //Free PlannedSeatedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, Room room, Location location){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.room = room;
        this.location = location;
        this.price = 0;
        this.freeEvent = true;

        generateEventNumber();
        setupTickets();
    }
    //PlannedSeatedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, int price, Room room, Location location){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.room = room;
        this.location = location;
        this.price = price;
        this.freeEvent = true;

        generateEventNumber();
        setupTickets();

    }
    //Free NonSeatedPlannedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, int amountOfFreeSpace){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.price = 0;
        this.amountOfFreeSpace = amountOfFreeSpace;
        this.participents = new ArrayList<Customer>();
        this.room = null;
        this.location = null;

        generateEventNumber();
        setupTickets();

    }
    //NonSeatedPlannedEvent
    public newEvent(String nameOfEvent, LocalDateTime dateOfEvent, int ageLimit, int lengthOfEvent, String address, Organizer organizer, int price, int amountOfFreeSpace){

        super(nameOfEvent, dateOfEvent, lengthOfEvent, ageLimit);
        this.address = address;
        this.organizer = organizer;
        this.price = price;
        this.amountOfFreeSpace = amountOfFreeSpace;
        this.participents = new ArrayList<Customer>();
        this.room = null;
        this.location = null;

        generateEventNumber();
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
            for(int i = 0; i < amountOfFreeSpace; i++){
                tickets.add(new Ticket("ID", this, price, i));
            }
        }
    }

    private Boolean isRoomNull(){
        return (room == null);
    }
    private Boolean isLocationNull(){
        return (location == null);
    }

    // TODO: Add metohd for generating event number
    private void generateEventNumber() {

    }


    public void addParticipent(Customer participent){
        participents.add(participent);
    }
    public void addSpecialNotice(String notice){
        specialNotices.add(notice);
    }
    public void removeSpecialNotice(int noticeIndex){
        specialNotices.remove(noticeIndex);
    }

    public int getAmountOfFreeSpace() {
        return amountOfFreeSpace;
    }
    public void setAmountOfFreeSpace(int amountOfFreeSpace) {
        this.amountOfFreeSpace = amountOfFreeSpace;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getFreeEvent() {
        return freeEvent;
    }
    public void setFreeEvent(Boolean freeEvent) {
        this.freeEvent = freeEvent;
    }
    public ArrayList<Customer> getParticipents() {
        return participents;
    }
    public ArrayList<String> getSpecialNotices() {
        return specialNotices;
    }
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    public int getSoldtickets() {
        return soldtickets;
    }
    public void setSoldtickets(int soldtickets) {
        this.soldtickets = soldtickets;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getEventNumber() {
        return eventNumber;
    }
    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Organizer getOrganizer() {
        return organizer;
    }
    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString(){
        return getNameOfEvent() + "\n"
                + "\t Adress: " + getAddress()
                + "\t Price: " + price;
    }
}

package Model;

public class Ticket {
    private String idForTicket; // Ticket serial number used for seatedEvents
    private PlannedEvent validForEvent; // The event the ticket is valid for
    private int priceInNOK;
    private int seatNumber;
    private Boolean available;

    // Used for Non seated events
    public Ticket(PlannedEvent validForEvent, int priceInNOK) {
        this.validForEvent = validForEvent;
        this.priceInNOK = priceInNOK;
        this.available = true;
    }

    // Used for Seated events
    public Ticket(String idForTicket, PlannedEvent validForEvent, int priceInNOK, int seatNumber) {
        this(validForEvent, priceInNOK);
        this.idForTicket = idForTicket;
        this.seatNumber = seatNumber;
    }



    public String getIdForTicket() {
        return idForTicket;
    }

    public PlannedEvent getValidForEvent() {
        return validForEvent;
    }

    public int getPriceInNOK() {
        return priceInNOK;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

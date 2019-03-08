package Model;

public class Ticket {
    private String idForTicket; // Ticket serial number used for seatedEvents
    private PlannedEvent validForEvent; // The event the ticket is valid for
    private int priceInNOK;
    private String seatNumber;

    // Used for Non seated events
    public Ticket(PlannedEvent validForEvent, int priceInNOK) {
        this.validForEvent = validForEvent;
        this.priceInNOK = priceInNOK;
    }

    // Used for Seated events
    public Ticket(String idForTicket, PlannedEvent validForEvent, int priceInNOK, String seatNumber) {
        this.idForTicket = idForTicket;
        this.validForEvent = validForEvent;
        this.priceInNOK = priceInNOK;
        this.seatNumber = seatNumber;
    }
}

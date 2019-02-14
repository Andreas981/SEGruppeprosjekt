package Structure;

public abstract class ReservedEvent extends Event{
    private int seatedPlaces;
    private int standingPlaces;

    public ReservedEvent(String name, String organizer, String date, String time, String location, int seatedPlaces, int standingPlaces) {
        super(name, organizer, date, time, location);
        this.seatedPlaces = seatedPlaces;
        this.standingPlaces = standingPlaces;
    }

    public void setSeatedPlaces(int seatedPlaces) {
        this.seatedPlaces = seatedPlaces;
    }
    public void setStandingPlaces(int standingPlaces) {
        this.standingPlaces = standingPlaces;
    }
    public int getSeatedPlaces() {
        return seatedPlaces;
    }
    public int getStandingPlaces() {
        return standingPlaces;
    }
}

package Structure;

import java.util.Date;

public abstract class Event {
    private String name;
    private String organizer;
    private String date;
    private String time;
    private String location;

    public Event(String name, String organizer, String date, String time, String location) {
        this.name = name;
        this.organizer = organizer;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public String getOrganizer() {
        return organizer;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getLocation() {
        return location;
    }
}

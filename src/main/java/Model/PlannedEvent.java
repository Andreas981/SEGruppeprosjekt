package Model;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract class used for creating Events by Organizer's
 */
public abstract class PlannedEvent {
    private String nameOfEvent;
    // TODO Add organizer<organizer> field
    private String dateOfEventInput;
    private Date dateOfEvent;
    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    private Time timeOfEventStart;

    private int lengthOfEvent;
    private int ageLimit;
    // TODO Add tickets<Ticket>


    public PlannedEvent(String nameOfEvent, String dateOfEvent, Time timeOfEventStart, int lengthOfEvent, int ageLimit) {
        // If a valid date is entered:
        if(tryParseInputDate(dateOfEvent)){
            this.nameOfEvent = nameOfEvent;
            this.dateOfEventInput = dateOfEvent;
            this.dateOfEvent = parseInputDate(dateOfEvent);
            this.timeOfEventStart = timeOfEventStart;
            this.lengthOfEvent = lengthOfEvent;
            this.ageLimit = ageLimit;
        }else{
            System.out.println("Can not generate this event!");
        }
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public String getDateOfEventInput() {
        return dateOfEventInput;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public Time getTimeOfEventStart() {
        return timeOfEventStart;
    }

    public int getLengthOfEvent() {
        return lengthOfEvent;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    @Override
    public String toString(){
        return nameOfEvent ;
    }

    private boolean tryParseInputDate(String dateEntered){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateParsed = null;
        try {
            //Parsing the String
            dateParsed = dateFormat.parse(dateEntered);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date input! Should be Day-Month-Year");
            return false;
        }
        return true;
    }

    private Date parseInputDate(String dateEntered){
        SimpleDateFormat birthDay = new SimpleDateFormat("dd-MM-yyyy");
        Date dateParsed = null;
        try {
            //Parsing the String
            dateParsed = birthDay.parse(dateEntered);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date input! Should be Day-Month-Year");
            return null;
        }
        return dateParsed;
    }

    // TODO Buy ticket implementation
    // TODO Check that the ticket is valid for this show
}

package Controller;

import Dummy.Database;
import Model.Room;

public class RoomController {

    public void addRoom(int location, String name, int max) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().add(new Room(name, max, false));
    }

    public void removeRoom(int location, int room) {
        Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().remove(room);
    }

    public void editRoom(int location, int room, String name, String max) {
        if(!name.equals("."))
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setName(name);
        if(!max.equals(".") && Integer.parseInt(max) > 0)
            Database.currentLoggedInOrganizer.getLocations().get(location).getRooms().get(room).setMaxParticipents(Integer.parseInt(max));
    }
}

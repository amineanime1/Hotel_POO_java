package Hotel;

import java.util.HashMap;
import java.util.Map;

public class RoomManagement {
    private Map<Integer, Room> rooms;

    public RoomManagement() {
        rooms = new HashMap<>();
    }
    public void addRoom(Room room) {
        rooms.put(room.getNumber(), room);
    }
    public Map<Integer, Room> getRooms() {
        return rooms;
    }
    public void printRooms() {
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room room = entry.getValue();
            System.out.println("Room Number: " + room.getNumber() + ", Room Type: " + room.getType() + ", Room Price: " + room.getPrice());
        }
    // Other methods to add, remove, or modify rooms...
}
}

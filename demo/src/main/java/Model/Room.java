package Model;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private static HashMap<Integer, Room> rooms = new HashMap<>();
    private static int nextId = 0;
    private int id;
    private int number;
    private String type;
    private double price;
    private boolean isReserved;

    public Room(int number, String type, double price, boolean isReserved) {
        this.id = nextId++;
        this.number = number;
        this.type = type;
        this.price = price;
        this.isReserved = isReserved;

        rooms.put(number, this);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
    public static Room getRoomByNumber(int number) {
        return rooms.get(number);
    }
    public static HashMap<Integer, Room> getRooms() {
        return rooms;
    }
    void save() {
        // Add this room to the rooms HashMap
        rooms.put(this.number, this);
    }
    
    void update() {
        // Update this room in the rooms HashMap
        rooms.put(this.number, this);
    }
    
    void delete() {
        // Remove this room from the rooms HashMap
        rooms.remove(this.number);
    }
 
}
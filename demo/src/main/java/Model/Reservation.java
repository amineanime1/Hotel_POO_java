package Model;

import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private static HashMap<Integer, Reservation> reservations = new HashMap<>();
    private static int idCounter = 0;
    private int id;
    private Room room;
    private User client;
    private String username;
    private int roomId;
    private int userId;
    private String startDate;
    private String endDate;
    private String status;

    public Reservation(int userId, String username, int roomId, String startDate, String endDate, String status) {
        this.id = idCounter++;
        this.userId = userId;
        this.username = username;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        reservations.put(id, this);
    }

    public static Reservation getReservationById(int id) {
        return reservations.get(id);
    }

    public void accept() {
        this.status = "Accepted";
    }

    public void decline() {
        this.status = "Declined";
    }

    public static Reservation getReservationByUsername(String username) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getUsername().equals(username)) {
                return reservation;
            }
        }
        return null;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
  
}
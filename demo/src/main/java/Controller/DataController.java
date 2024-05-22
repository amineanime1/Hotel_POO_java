package Controller;

import Model.Application;

public class DataController {
    private Application model;

    public DataController(Application model) {
        this.model = model;
    }

    public void loadData() {
        // Appeler les méthodes pour charger les données depuis la base de données
        model.getUsersManagement().loadUsersFromDatabase();
        model.getRoomsManagement().loadRoomsFromDatabase();
        model.getReservationManagement().loadReservationsFromDatabase();
    }

    public void saveData() {
        // Appeler les méthodes pour sauvegarder les données dans la base de données
        model.getUsersManagement().saveUsersToDatabase();
        model.getRoomsManagement().saveRoomsToDatabase();
        model.getReservationManagement().saveReservationsToDatabase();
    }
}

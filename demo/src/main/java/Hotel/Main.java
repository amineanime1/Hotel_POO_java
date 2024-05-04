package Hotel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersManagement userManager = new UsersManagement();
        Application app = new Application();
        
        int choice = 0;
        while (choice != 6) {

            System.out.println("1. Register\n2. Login\n3. Check Users Hashmap\n4. Check Rooms Hashmap\n" + //
                                "5. Check Reservations Hashmap\n6. Quit");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine();

           if (choice == 1) {
                // Register
                boolean isRegistered = false;
                while (!isRegistered) {
                    System.out.println("Enter username:");
                    String username = scanner.next();
                    System.out.println("Enter password:");
                    String password = scanner.next();
                    System.out.println("Enter 1 for regular user, 2 for administrator:");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();
                    boolean isAdmin = (roleChoice == 2);
                    isRegistered =  app.register(username, password, isAdmin);
                    if (!isRegistered) {
                        System.out.println("Username already exists. Please enter another username.");
                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter username:");
                String username = scanner.next();
                System.out.println("Enter password:");
                String password = scanner.next();
                User user = app.login(username, password);
                if (user != null) {
                    int userChoice = 0;
                    if (user instanceof Client) {
                        while (userChoice != 3) {
                            System.out.println("1. Check reservation\n2. Reserve a room\n3. Quit");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input. Please enter a number.");
                                scanner.next(); // Consume the invalid input
                            }
                            userChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (userChoice == 1) {
                                ((Client) user).checkReservation(user.getUsername());
                            } else if (userChoice == 2) {
                                ((Client) user).reserveRoom(user.getUsername());
                            } else if (userChoice != 3) {
                                System.out.println("Invalid choice.");
                            }
                        }
                    } else if (user instanceof Administrator) {
    int adminChoice = 0;
    while (adminChoice != 3) {
        System.out.println("1. Manage reservations\n2. Manage rooms\n3. Quit");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume the invalid input
        }
        adminChoice = scanner.nextInt();
        scanner.nextLine();
        switch (adminChoice) {
            case 1:
                // Manage reservations
                ((Administrator) user).manageReservations();
                break;
            case 2:
                // Manage rooms
                int roomChoice = 0;
                while (roomChoice != 4) {
                    System.out.println("1. Create room\n2. Modify room\n3. Delete room\n4. Back to main menu");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Consume the invalid input
                    }
                    roomChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (roomChoice) {
                        case 1:
                            // Gather necessary information for room creation
                            System.out.println("Enter room number:");
                            int number = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter room type:");
                            String type = scanner.next();
                            System.out.println("Enter room price:");
                            double price = scanner.nextDouble();
                            ((Administrator) user).createRoom(number, type, price);
                            break;
                        case 2:
                            // Gather necessary information for room modification
                            System.out.println("Enter room number to modify:");
                            int numberToModify = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter new room type:");
                            String newType = scanner.next();
                            System.out.println("Enter new room price:");
                            double newPrice = scanner.nextDouble();
                            ((Administrator) user).modifyRoom(numberToModify, newType, newPrice);
                            break;
                        case 3:
                            // Gather necessary information for room deletion
                            System.out.println("Enter room number to delete:");
                            int numberToDelete = scanner.nextInt();
                            scanner.nextLine();
                            ((Administrator) user).deleteRoom(numberToDelete);
                            break;
                        default:
                            if (roomChoice != 4) {
                                System.out.println("Invalid choice.");
                            }
                            break;
                    }
                }
                break;
            default:
                if (adminChoice != 3) {
                    System.out.println("Invalid choice.");
                }
                break;
        }
    }
}
                } else {
                    System.out.println("Invalid username or password.");
                }
            } else if(choice == 3) {
                // Print users
                User.printUsers();
            } else if(choice == 4) {
                // Print rooms
                app.getRoomsManagement().printRooms();
            } else if(choice == 5) {
                // Print reservations
                app.getReservationManagement().printReservations();
            } else if (choice != 6) {
                // Invalid choice
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        
    }
}
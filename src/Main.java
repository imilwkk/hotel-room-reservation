import java.io.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        loadRoomsFromFile();
        loadReservationsFromFile();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Show all rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Cancel reservation");
            System.out.println("5. Save & Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showRooms();
                case 2 -> makeReservation();
                case 3 -> showReservations();
                case 4 -> cancelReservation();
                case 5 -> {
                    saveReservationsToFile();
                    System.out.println("Data saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void showRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            String status = room.isAvailable ? "Free" : "Booked";
            System.out.println(room.number + " - " + room.type + " - " + room.price + " som - " + status);
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room number to reserve: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        for (Room room : rooms) {
            if (room.number == number) {
                if (!room.isAvailable) {
                    System.out.println("Room is already booked.");
                    return;
                }
                room.isAvailable = false;
                reservations.add(new Reservation(name, number));
                System.out.println("Reservation successful!");
                return;
            }
        }
        System.out.println("Room not found.");
    }

    static void showReservations() {
        System.out.println("\nReservations:");
        for (Reservation res : reservations) {
            System.out.println(res.name + " reserved room " + res.roomNumber);
        }
    }

    static void cancelReservation() {
        System.out.print("Enter your name to cancel: ");
        String name = scanner.nextLine();
        boolean found = false;

        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation res = iterator.next();
            if (res.name.equalsIgnoreCase(name)) {
                for (Room room : rooms) {
                    if (room.number == res.roomNumber) {
                        room.isAvailable = true;
                    }
                }
                iterator.remove();
                found = true;
                System.out.println("Reservation cancelled.");
            }
        }
        if (!found) {
            System.out.println("No reservation found for that name.");
        }
    }

    static void loadRoomsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("rooms.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0]);
                String type = parts[1];
                int price = Integer.parseInt(parts[2]);
                boolean isAvailable = Boolean.parseBoolean(parts[3]);
                rooms.add(new Room(number, type, price, isAvailable));
            }
        } catch (IOException e) {
            rooms.add(new Room(101, "Single", 2000, true));
            rooms.add(new Room(102, "Double", 3500, true));
            rooms.add(new Room(103, "Suite", 5000, true));
        }
    }

    static void saveReservationsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.csv"))) {
            for (Reservation res : reservations) {
                writer.write(res.name + "," + res.roomNumber);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rooms.csv"))) {
            for (Room room : rooms) {
                writer.write(room.number + "," + room.type + "," + room.price + "," + room.isAvailable);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving rooms.");
        }
    }

    static void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservations.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                reservations.add(new Reservation(parts[0], Integer.parseInt(parts[1])));
                for (Room room : rooms) {
                    if (room.number == Integer.parseInt(parts[1])) {
                        room.isAvailable = false;
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    static class Room {
        int number;
        String type;
        int price;
        boolean isAvailable;

        public Room(int number, String type, int price, boolean isAvailable) {
            this.number = number;
            this.type = type;
            this.price = price;
            this.isAvailable = isAvailable;
        }
    }

    static class Reservation {
        String name;
        int roomNumber;

        public Reservation(String name, int roomNumber) {
            this.name = name;
            this.roomNumber = roomNumber;
        }
    }
}

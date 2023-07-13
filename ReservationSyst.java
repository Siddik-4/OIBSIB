import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationSyst {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        boolean isRunning = true;   

        while (isRunning) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using the Online Reservation System!");
    }

    private static void login() {
        System.out.print("Enter your login id: ");
        String loginId = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate the login credentials
        if (isValidUser(loginId, password)) {
            showReservationMenu();
        } else {
            System.out.println("Invalid login id or password. Please try again.");
        }
    }

    private static boolean isValidUser(String loginId, String password) {
        // Add your validation logic here
        // For simplicity, let's assume a single valid login id and password
        String validLoginId = "Anonymous";
        String validPassword = "Tesla";

        return loginId.equals(validLoginId) && password.equals(validPassword);
    }

    private static void showReservationMenu() {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n1. Make a reservation");
            System.out.println("2. View reservation details");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Logout");

            System.out.println(" ");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    viewReservationDetails();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    isLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter from (place): ");
        String fromPlace = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        // Generate a unique reservation ID
        String reservationId = generateReservationId();
       

        // Create a reservation object
        Reservation reservation = new Reservation(reservationId, name, trainNumber, classType, dateOfJourney, fromPlace, destination);

        // Save the reservation in the map
        reservations.put(reservationId, reservation);
        System.out.println(" ");
        System.out.println("reservation Id:" +reservationId);

        System.out.println("Reservation created successfully!");
    }

    private static void viewReservationDetails() {
        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();

        // Retrieve the reservation details based on the reservation ID
        Reservation reservation = reservations.get(reservationId);
        System.out.println("\n");
        
        if (reservation != null) {
            // Display the reservation details
            System.out.println("Reservation Details:");
            System.out.println("Reservation ID: " + reservation.getReservationId());
            System.out.println("Name: " + reservation.getName());
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Date of Journey: " + reservation.getDateOfJourney());
            System.out.println("From: " + reservation.getFromPlace());
            System.out.println("Destination: " + reservation.getDestination());
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();

        // Retrieve the reservation details based on the reservation ID
        Reservation reservation = reservations.get(reservationId);

        if (reservation != null) {
            // Display the reservation details
            System.out.println("Reservation Details:");
            System.out.println("Reservation ID: " + reservation.getReservationId());
            System.out.println("Name: " + reservation.getName());
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Date of Journey: " + reservation.getDateOfJourney());
            System.out.println("From: " + reservation.getFromPlace());
            System.out.println("Destination: " + reservation.getDestination());

            System.out.print("Confirm cancellation (OK/Cancel): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) {
                // Remove the reservation from the map
                reservations.remove(reservationId);

                System.out.println("Reservation canceled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private static String generateReservationId() {
        // Generate a random 6-digit alphanumeric reservation ID
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder reservationId = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * characters.length());
            reservationId.append(characters.charAt(index));
        }
        return reservationId.toString();
    }
}

class Reservation {
    private String reservationId;
    private String name;
    private String trainNumber;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String destination;

    public Reservation(String reservationId, String name, String trainNumber, String classType, String dateOfJourney, String fromPlace, String destination) {
        this.reservationId = reservationId;
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.destination = destination;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getName() {
        return name;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public String getDestination() {
        return destination;
    }
}

import java.util.Scanner;
public class OnlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();

        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginSystem.login(username, password)) {
            System.out.println("Login Successful!");
            System.out.println("\nReservation Form:");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();
            scanner.nextLine();  
            System.out.print("Enter train number: ");
            String trainNumber = scanner.nextLine();
            System.out.print("Enter train name: ");
            String trainName = scanner.nextLine();
            System.out.print("Enter class type: ");
            String classType = scanner.nextLine();
            System.out.print("Enter date of journey: ");
            String dateOfJourney = scanner.nextLine();
            System.out.print("Enter from place: ");
            String fromPlace = scanner.nextLine();
            System.out.print("Enter to place: ");
            String toPlace = scanner.nextLine();

            Reservation reservation = new Reservation(name, age, trainNumber, trainName, classType, dateOfJourney, fromPlace, toPlace);
            reservation.displayReservationDetails();

            System.out.println("\nDo you want to cancel this reservation? (yes/no)");
            String cancelChoice = scanner.nextLine();
            if (cancelChoice.equalsIgnoreCase("yes")) {
                Cancellation cancellation = new Cancellation(reservation.getPnrNumber());
                cancellation.cancelReservation();
            }
        } else {
            System.out.println("Login Failed! Please check your username and password.");
        }

        scanner.close();
    }
}


class LoginSystem {
    private String username = "user";
    private String password = "password";

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
class Reservation {
    private String name;
    private int age;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toPlace;
    private String pnrNumber;

    public Reservation(String name, int age, String trainNumber, String trainName,
                       String classType, String dateOfJourney, String fromPlace, String toPlace) {
        this.name = name;
        this.age = age;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.pnrNumber = generatePNR();
    }

    private String generatePNR() {
        return "PNR" + Math.random() * 10000;
    }

    public void displayReservationDetails() {
        System.out.println("Reservation Successful!");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Train: " + trainNumber + " - " + trainName);
        System.out.println("Class: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + fromPlace + " To: " + toPlace);
        System.out.println("PNR Number: " + pnrNumber);
    }

    public String getPnrNumber() {
        return pnrNumber;
    }
}
class Cancellation {
    private String pnrNumber;

    public Cancellation(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public void cancelReservation() {
        System.out.println("Reservation with PNR " + pnrNumber + " has been canceled.");
    }
}

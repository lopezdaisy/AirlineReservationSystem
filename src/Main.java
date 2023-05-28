
import java.util.Scanner;

public class Main {
    private static boolean[] seats = new boolean[11]; // Array to represent the seating chart

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please type 1 for First Class or 2 for Economy: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                assignSeat(true); // Assign a seat in the first-class section
            } else if (choice == 2) {
                assignSeat(false); // Assign a seat in the economy section
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.println();
            System.out.println("Boarding Pass");
            System.out.println("--------------");
            displayBoardingPass();

            if (isPlaneFull()) {
                System.out.println("The plane is now full.");
                break;
            }
        }

        scanner.close();
    }

    private static void assignSeat(boolean isFirstClass) {
        if (isFirstClass) {
            int seat = getFirstClassSeat();
            if (seat != -1) {
                seats[seat] = true;
            } else if (isEconomyAvailable()) {
                System.out.println("First Class is full. Would you like Economy? (Type yes or no)");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("yes")) {
                    assignSeat(false);
                } else {
                    System.out.println("Next flight leaves in 3 hours.");
                }
                scanner.close();
            } else {
                System.out.println("First Class and Economy are full. Next flight leaves in 3 hours.");
            }
        } else {
            int seat = getEconomySeat();
            if (seat != -1) {
                seats[seat] = true;
            } else if (isFirstClassAvailable()) {
                System.out.println("Economy is full. Would you like First Class? (Type yes or no)");
                Scanner scanner = new Scanner(System.in);
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("yes")) {
                    assignSeat(true);
                } else {
                    System.out.println("Next flight leaves in 3 hours.");
                }
                scanner.close();
            } else {
                System.out.println("First Class and Economy are full. Next flight leaves in 3 hours.");
            }
        }
    }

    private static int getFirstClassSeat() {
        for (int seat = 1; seat <= 5; seat++) {
            if (!seats[seat]) {
                return seat;
            }
        }
        return -1; // No available seats in first class
    }

    private static int getEconomySeat() {
        for (int seat = 6; seat <= 10; seat++) {
            if (!seats[seat]) {
                return seat;
            }
        }
        return -1; // No available seats in economy
    }

    private static boolean isFirstClassAvailable() {
        for (int seat = 1; seat <= 5; seat++) {
            if (!seats[seat]) {
                return true;
            }
        }
        return false; // First class is full
    }

    private static boolean isEconomyAvailable() {
        for (int seat = 6; seat <= 10; seat++) {
            if (!seats[seat]) {
                return true;
            }
        }
        return false; // Economy is full
    }

    private static void displayBoardingPass() {
        for (int seat = 1; seat <= 10; seat++) {
            String section = (seat <= 5) ? "First Class" : "Economy";
            if (seats[seat]) {
                System.out.println("Seat " + seat + ": " + section);
            }
        }
    }

    private static boolean isPlaneFull() {
        for (int seat = 1; seat <= 10; seat++) {
            if (!seats[seat]) {
                return false;
            }
        }
        return true; // Plane is full
    }
}
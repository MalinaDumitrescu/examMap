import java.util.Scanner;

public class ConsoleView {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor to initialize the ConsoleView with a controller.
     * @param controller The controller managing Produkt operations.
     */
    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    /**
     * Main menu loop to interact with the user.
     */
    public void run() {
        while (true) {
            System.out.println("""
                \n---- Sport Store Management ----
                1 - Add Produkt
                2 - View Produkt
                3 - Update Produkt
                4 - Delete Produkt
                0 - Exit
                """);
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> addProdukt();
                case "2" -> viewProdukt();
                case "3" -> updateProdukt();
                case "4" -> deleteProdukt();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Adds a new Produkt by taking input from the user.
     */
    private void addProdukt() {
        System.out.print("Enter Produkt name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Produkt price: ");
        double preis = getValidDouble();
        System.out.print("Enter Produkt origin region: ");
        String herkunftRegion = scanner.nextLine();

        Produkt produkt = new Produkt(0, name, preis, herkunftRegion);
        controller.addProdukt(produkt);
        System.out.println("Produkt added successfully!");
    }

    /**
     * Displays details of a Produkt by ID.
     */
    private void viewProdukt() {
        System.out.print("Enter Produkt ID: ");
        int id = getValidInt();
        Produkt produkt = controller.getProdukt(id);
        if (produkt != null) {
            System.out.println("Found: " + produkt);
        } else {
            System.out.println("Produkt not found.");
        }
    }

    /**
     * Updates an existing Produkt by ID with new user inputs.
     */
    private void updateProdukt() {
        System.out.print("Enter Produkt ID: ");
        int id = getValidInt();
        Produkt produkt = controller.getProdukt(id);

        if (produkt == null) {
            System.out.println("Produkt not found.");
            return;
        }

        System.out.print("Enter new Produkt name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Produkt price: ");
        double preis = getValidDouble();
        System.out.print("Enter new Produkt origin region: ");
        String herkunftRegion = scanner.nextLine();

        controller.updateProdukt(id, new Produkt(id, name, preis, herkunftRegion));
        System.out.println("Produkt updated successfully!");
    }

    /**
     * Deletes a Produkt by ID.
     */
    private void deleteProdukt() {
        System.out.print("Enter Produkt ID: ");
        int id = getValidInt();
        Produkt produkt = controller.getProdukt(id);
        if (produkt == null) {
            System.out.println("Produkt not found.");
            return;
        }
        controller.deleteProdukt(id);
        System.out.println("Produkt deleted successfully!");
    }

    /**
     * Ensures valid integer input from the user.
     * @return A valid integer entered by the user.
     */
    private int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number: ");
            }
        }
    }

    /**
     * Ensures valid double input from the user.
     * @return A valid double entered by the user.
     */
    private double getValidDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid price: ");
            }
        }
    }
}

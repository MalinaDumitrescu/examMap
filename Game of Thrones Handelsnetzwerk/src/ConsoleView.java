import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
                5 - Add Charakter
                6 - View Charakter
                7 - Update Charakter
                8 - Delete Charakter
                9 - Filter Charakter by Herkunftsort
                10 - Filter Charakter by Produkt Herkunftsregion
                11 - Sort Produkte for Charakter
                0 - Exit
                """);
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> addProdukt();
                case "2" -> viewProdukt();
                case "3" -> updateProdukt();
                case "4" -> deleteProdukt();
                case "5" -> addCharakter();
                case "6" -> viewCharakter();
                case "7" -> updateCharakter();
                case "8" -> deleteCharakter();
                case "9" -> filterCharakterByHerkunftsOrt();
                case "10" -> filterCharakterByProduktHerkunftsRegion();
                case "11" -> sortProdukteForCharakter();
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


    private void addCharakter() {
        System.out.print("Enter Charakter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Charakter origin: ");
        String herkunftsOrt = scanner.nextLine();

        List<Integer> produktIds = new ArrayList<>();
        while (true) {
            System.out.print("Enter Produkt ID (0 to stop): ");
            int produktId = getValidInt();
            if (produktId == 0) break;

            if (controller.getProdukt(produktId) != null) {
                produktIds.add(produktId);
            } else {
                System.out.println("Produkt ID " + produktId + " does not exist. Try again.");
            }
        }

        controller.addCharakter(new Charakter(0, name, herkunftsOrt, produktIds));
        System.out.println("Charakter added successfully!");
    }

    private void viewCharakter() {
        System.out.print("Enter Charakter ID: ");
        int id = getValidInt();
        Charakter charakter = controller.getCharakter(id);
        if (charakter != null) {
            System.out.println(" - " + charakter);
        } else {
            System.out.println("Charakter not found.");
        }
    }

    private void updateCharakter() {
        System.out.print("Enter Charakter ID: ");
        int id = getValidInt();
        Charakter charakter = controller.getCharakter(id);
        if (charakter == null) {
            System.out.println("Charakter not found.");
            return;
        }

        System.out.print("Enter new Charakter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Charakter origin: ");
        String herkunftsOrt = scanner.nextLine();
        List<Integer> produktIds = new ArrayList<>();
        while (true) {
            System.out.print("Enter Produkt ID (0 to stop): ");
            int produktId = getValidInt();
            if (produktId == 0) break;

            if (controller.getProdukt(produktId) != null) {
                produktIds.add(produktId);
            } else {
                System.out.println("Produkt ID " + produktId + " does not exist. Try again.");
            }
        }

        controller.updateCharakter(id, new Charakter(id, name, herkunftsOrt, produktIds));
        System.out.println("Charakter updated successfully!");
    }

    private void deleteCharakter() {
        System.out.print("Enter Charakter ID: ");
        int id = getValidInt();
        Charakter charakter = controller.getCharakter(id);
        if (charakter == null) {
            System.out.println("Charakter not found.");
            return;
        }
        controller.deleteCharakter(id);
        System.out.println("Charakter deleted successfully!");
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

    private void filterCharakterByHerkunftsOrt() {
        System.out.print("Enter Herkunftsort to filter: ");
        String herkunftsOrt = scanner.nextLine();
        List<Charakter> filteredCharakters = controller.filterCharakterByHerkunftsOrt(herkunftsOrt);
        if (filteredCharakters.isEmpty()) {
            System.out.println("No Charakter found from this Herkunftsort.");
        } else {
            System.out.println("Charakters from " + herkunftsOrt + ":");
            for (Charakter charakter : filteredCharakters) {
                System.out.println(" - " + charakter);
            }
        }
    }


    private void filterCharakterByProduktHerkunftsRegion() {
        System.out.print("Enter Produkt Herkunftsregion to filter by: ");
        String herkunftsRegion = scanner.nextLine();
        List<Charakter> filteredCharakters = controller.filterCharakterByProduktHerkunftsRegion(herkunftsRegion);
        if (filteredCharakters.isEmpty()) {
            System.out.println("No Charakter found who bought a Produkt from this region.");
        } else {
            System.out.println("Charakters who bought a Produkt from " + herkunftsRegion + ":");
            for (Charakter charakter : filteredCharakters) {
                System.out.println(" - " + charakter);
            }
        }
    }

    private void sortProdukteForCharakter() {
        System.out.print("Enter Charakter ID: ");
        int charakterId = getValidInt();
        Charakter charakter = controller.getCharakter(charakterId);
        if (charakter == null) {
            System.out.println("Charakter not found.");
            return;
        }

        System.out.print("Sort by price (asc/desc): ");
        String sortMode = scanner.nextLine().trim().toLowerCase();
        boolean ascending = sortMode.equals("asc");

        List<Produkt> sortedProdukte = controller.getSortedProdukteForCharakter(charakterId, ascending);
        if (sortedProdukte.isEmpty()) {
            System.out.println("No products found for this Charakter.");
        } else {
            System.out.println("Sorted Produkte for Charakter " + charakter.getName() + ":");
            for (Produkt produkt : sortedProdukte) {
                System.out.println(" - " + produkt);
            }
        }
    }

}

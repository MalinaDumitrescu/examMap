import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleView {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        while (true) {
            System.out.println("""
                \n---- Sport Store Management ----
                1 - Add Produkt
                2 - View Produkt
                3 - Update Produkt
                4 - Delete Produkt
                5 - Add Kunde
                6 - View Kunde
                7 - Update Kunde
                8 - Delete Kunde
                9 - Filter Kunden by Ort
                10 - Filter Kunden by Produkt Season
                11 - Sort Produkte by Kunde
                0 - Exit
                """);

            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> addProdukt();
                case "2" -> viewProdukt();
                case "3" -> updateProdukt();
                case "4" -> deleteProdukt();
                case "5" -> addKunde();
                case "6" -> viewKunde();
                case "7" -> updateKunde();
                case "8" -> deleteKunde();
                case "9" -> filterKundenByOrt();
                case "10" -> filterKundenByProduktSeason();
                case "11" -> sortProdukteByKunde();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addProdukt() {
        System.out.print("Enter Produkt name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Produkt price: ");
        double price = getValidDouble();
        System.out.print("Enter Produkt season: ");
        String season = scanner.nextLine();

        Produkt produkt = new Produkt(0, name, price, season);
        controller.addProdukt(produkt);
        System.out.println("Produkt added successfully!");
    }

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
        double price = getValidDouble();
        System.out.print("Enter new Produkt season: ");
        String season = scanner.nextLine();

        controller.updateProdukt(id, new Produkt(id, name, price, season));
        System.out.println("Produkt updated successfully!");
    }

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

    private void addKunde() {
        System.out.print("Enter Kunde name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Kunde ort: ");
        String ort = scanner.nextLine();

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

        controller.addKunde(new Kunde(0, name, ort, produktIds));
        System.out.println("Kunde added successfully!");
    }

    private void viewKunde() {
        System.out.print("Enter Kunde ID: ");
        int id = getValidInt();
        Kunde kunde = controller.getKunde(id);
        if (kunde != null) {
            System.out.println(" - " + kunde);
        } else {
            System.out.println("Kunde not found.");
        }
    }

    private void updateKunde() {
        System.out.print("Enter Kunde ID: ");
        int id = getValidInt();
        Kunde kunde = controller.getKunde(id);
        if (kunde == null) {
            System.out.println("Kunde not found.");
            return;
        }

        System.out.print("Enter new Kunde name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Kunde ort: ");
        String ort = scanner.nextLine();
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

        controller.updateKunde(id, new Kunde(id, name, ort, produktIds));
        System.out.println("Kunde updated successfully!");
    }

    private void deleteKunde() {
        System.out.print("Enter Kunde ID: ");
        int id = getValidInt();
        Kunde kunde = controller.getKunde(id);
        if (kunde == null) {
            System.out.println("Kunde not found.");
            return;
        }
        controller.deleteKunde(id);
        System.out.println("Kunde deleted successfully!");
    }

    private void filterKundenByOrt() {
        System.out.print("Enter Ort: ");
        String ort = scanner.nextLine();
        List<Kunde> kunden = controller.filterCustomersByPlace(ort);
        if (kunden.isEmpty()) {
            System.out.println("No Kunden found in " + ort);
        } else {
            kunden.forEach(System.out::println);
        }
    }

    private void filterKundenByProduktSeason() {
        System.out.print("Enter Produkt season: ");
        String season = scanner.nextLine();
        List<Kunde> kunden = controller.filterCustomersByProductSeason(season);
        if (kunden.isEmpty()) {
            System.out.println("No Kunden found with products in " + season);
        } else {
            kunden.forEach(System.out::println);
        }
    }

    private void sortProdukteByKunde() {
        System.out.print("Enter Kunde ID: ");
        int id = getValidInt();
        System.out.print("Sort in ascending order? (true/false): ");
        boolean ascending = Boolean.parseBoolean(scanner.nextLine());

        List<Produkt> produkte = controller.sortProductsByCustomer(id, ascending);
        if (produkte.isEmpty()) {
            System.out.println("No products found for Kunde ID " + id);
        } else {
            produkte.forEach(System.out::println);
        }
    }

    private int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number: ");
            }
        }
    }

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

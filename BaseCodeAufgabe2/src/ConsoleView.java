import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.function.Function;

/**
 * Generic ConsoleView for handling CRUD operations for any entity.
 */
public class ConsoleView<T extends BaseEntity> {
    private final Controller<T> controller;
    private final Scanner scanner = new Scanner(System.in);
    private final Supplier<T> createEntity;
    private final Function<Integer, T> getEntity;
    private final String entityName;

    public ConsoleView(Controller<T> controller, Supplier<T> createEntity, Function<Integer, T> getEntity, String entityName) {
        this.controller = controller;
        this.createEntity = createEntity;
        this.getEntity = getEntity;
        this.entityName = entityName;
    }

    public void run() {
        while (true) {
            System.out.println("\n---- " + entityName + " Management ----");
            System.out.println("1 - Add " + entityName);
            System.out.println("2 - View " + entityName);
            System.out.println("3 - Update " + entityName);
            System.out.println("4 - Delete " + entityName);
            System.out.println("5 - View All " + entityName + "s");
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1" -> addEntity();
                case "2" -> viewEntity();
                case "3" -> updateEntity();
                case "4" -> deleteEntity();
                case "5" -> viewAllEntities();
                case "0" -> {
                    System.out.println("Exiting " + entityName + " management...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addEntity() {
        T entity = createEntity.get();
        controller.add(entity);
        System.out.println(entityName + " added successfully!");
    }

    private void viewEntity() {
        System.out.print("Enter " + entityName + " ID: ");
        int id = getValidInt();
        T entity = getEntity.apply(id);
        if (entity != null) {
            System.out.println("Found: " + entity);
        } else {
            System.out.println(entityName + " not found.");
        }
    }

    private void updateEntity() {
        System.out.print("Enter " + entityName + " ID: ");
        int id = getValidInt();
        T entity = getEntity.apply(id);
        if (entity == null) {
            System.out.println(entityName + " not found.");
            return;
        }

        T updatedEntity = createEntity.get();
        controller.update(id, updatedEntity);
        System.out.println(entityName + " updated successfully!");
    }

    private void deleteEntity() {
        System.out.print("Enter " + entityName + " ID: ");
        int id = getValidInt();
        T entity = getEntity.apply(id);
        if (entity == null) {
            System.out.println(entityName + " not found.");
            return;
        }
        controller.delete(id);
        System.out.println(entityName + " deleted successfully!");
    }

    private void viewAllEntities() {
        List<T> entities = controller.getAll();
        if (entities.isEmpty()) {
            System.out.println("No " + entityName + "s found.");
        } else {
            entities.forEach(System.out::println);
        }
    }

    private int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Enter a valid number: ");
            }
        }
    }
}

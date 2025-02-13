public class Main {
    public static void main(String[] args) {
        Repository<Kunde> kundeRepo = new Repository<>();
        Repository<Produkt> produktRepo = new Repository<>();
        Service<Kunde> kundeService = new Service<>(kundeRepo);
        Service<Produkt> produktService = new Service<>(produktRepo);
        Controller controller = new Controller(kundeService, produktService);
        ConsoleView view = new ConsoleView(controller);
        view.run();
    }
}

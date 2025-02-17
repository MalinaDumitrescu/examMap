public class Main {

    public static void main(String[] args) {
        Repository<Produkt> produktRepo = new Repository<>();
        Service<Produkt> produktService = new Service<>(produktRepo);
        Controller controller = new Controller( produktService);
        ConsoleView view = new ConsoleView(controller);
        view.run();
    }
}



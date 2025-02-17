public class Main {

    public static void main(String[] args) {
        Repository<Produkt> produktRepo = new Repository<>();
        Repository<Charakter> charakterRepository = new Repository<>();
        Service<Produkt> produktService = new Service<>(produktRepo);
        Service<Charakter> charakterService = new Service<>(charakterRepository);
        Controller controller = new Controller(produktService, charakterService); ;
        ConsoleView view = new ConsoleView(controller);
        view.run();
    }
}
//todo mai intai produktele si apoi characterele service si repo


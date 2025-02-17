import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Service<Produkt> produktService;

    public Controller( Service<Produkt> produktService) {
        this.produktService = produktService;
    }

    public void addProdukt(Produkt produkt) {
        produktService.add(produkt);
    }

    public Produkt getProdukt(int id) {
        return produktService.get(id);
    }

    public void updateProdukt(int id, Produkt produkt) {
        produktService.update(id, produkt);
    }

    public void deleteProdukt(int id) {
        produktService.delete(id);
    }
}

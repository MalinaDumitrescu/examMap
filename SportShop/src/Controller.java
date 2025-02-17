import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Service<Kunde> kundeService;
    private final Service<Produkt> produktService;

    public Controller(Service<Kunde> kundeService, Service<Produkt> produktService) {
        this.kundeService = kundeService;
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

    public void addKunde(Kunde kunde) {
        kundeService.add(kunde);
    }

    public Kunde getKunde(int id) {
        return kundeService.get(id);
    }

    public void updateKunde(int id, Kunde kunde) {
        kundeService.update(id, kunde);
    }

    public void deleteKunde(int id) {
        kundeService.delete(id);
    }

    public List<Kunde> filterCustomersByPlace(String place) {
        return kundeService.filter(c -> c.getOrt().equalsIgnoreCase(place));
    }

    public List<Kunde> filterCustomersByProductSeason(String season) {
        return kundeService.filter(c -> c.getProduktIds().stream()
                .map(produktService::get)
                .anyMatch(p -> p.getSeason().equalsIgnoreCase(season)));
    }

    public List<Produkt> sortProductsByCustomer(int customerId, boolean ascending) {
        Kunde kunde = kundeService.get(customerId);
        return kunde.getProduktIds().stream()
                .map(produktService::get)
                .sorted((p1, p2) -> ascending ?
                        Double.compare(p1.getPrice(), p2.getPrice()) :
                        Double.compare(p2.getPrice(), p1.getPrice()))
                .collect(Collectors.toList());
    }
}

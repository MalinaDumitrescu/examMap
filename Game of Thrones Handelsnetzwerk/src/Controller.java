import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//TODO MAI INTAI Produkt si dupa charakter
public class Controller {
    private final Service<Produkt> produktService;
    private final Service<Charakter> charakterService;
    

    public Controller( Service<Produkt> produktService, Service<Charakter> charakterService) {
        this.produktService = produktService;
        this.charakterService = charakterService;
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

    //TODO STOP AICI COMMIT

    public void addCharakter(Charakter charakter) {
        charakterService.add(charakter);
    }

    public Charakter getCharakter(int id) {
        return charakterService.get(id);
    }

    public void updateCharakter(int id, Charakter charakter) {
        charakterService.update(id, charakter);
    }

    public void deleteCharakter(int id) {
        charakterService.delete(id);
    }


    //TODO STOP AICI COMMIT

    /**
     * Filters Charakter by their Herkunftsort.
     * @param herkunftsOrt The location to filter by.
     * @return List of Charakter from the given Herkunftsort.
     */
    public List<Charakter> filterCharakterByHerkunftsOrt(String herkunftsOrt) {
        return charakterService.getAll().stream()
                .filter(c -> c.getHerkunftsOrt().equalsIgnoreCase(herkunftsOrt))
                .collect(Collectors.toList());
    }


    //TODO STOP AICI COMMIT

    /**
     * Filters Charakter who have purchased a product from the given Herkunftsregion.
     * @param herkunftsRegion The product origin region to filter by.
     * @return List of Charakter who own a product from that region.
     */
    public List<Charakter> filterCharakterByProduktHerkunftsRegion(String herkunftsRegion) {
        return charakterService.getAll().stream()
                .filter(c -> c.getProduktIds().stream()
                        .map(produktService::get)
                        .anyMatch(p -> p != null && p.getHerkunftRegion().equalsIgnoreCase(herkunftsRegion)))
                .collect(Collectors.toList());
    }


    //TODO STOP AICI COMMIT


    /**
     * Returns a sorted list of products owned by a given Charakter.
     * @param charakterId The ID of the Charakter.
     * @param ascending If true, sorts by ascending price, otherwise sorts by descending price.
     * @return Sorted list of Produkte owned by the Charakter.
     */
    public List<Produkt> getSortedProdukteForCharakter(int charakterId, boolean ascending) {
        Charakter charakter = charakterService.get(charakterId);
        if (charakter == null) return List.of();

        Comparator<Produkt> comparator = Comparator.comparing(Produkt::getPreis);
        if (!ascending) comparator = comparator.reversed();

        return charakter.getProduktIds().stream()
                .map(produktService::get)
                .filter(p -> p != null)
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

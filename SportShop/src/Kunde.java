import java.util.List;

public class Kunde extends BaseEntity {
    private String name;
    private String ort;
    private List<Integer> produktIds; // Stores Product IDs instead of objects

    public Kunde(int id, String name, String ort, List<Integer> produktIds) {
        super(id);
        this.name = name;
        this.ort = ort;
        this.produktIds = produktIds;
    }

    public String getName() { return name; }
    public String getOrt() { return ort; }
    public List<Integer> getProduktIds() { return produktIds; }

    public void addProdukt(int productId) {
        produktIds.add(productId);
    }

    @Override
    public String toString() {
        return "Kunde{" + "id=" + id + ", name='" + name + '\'' + ", ort='" + ort + '\'' + ", produktIds=" + produktIds + '}';
    }
}

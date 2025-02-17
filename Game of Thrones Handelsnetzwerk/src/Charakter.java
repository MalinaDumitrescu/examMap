import java.util.List;

public class Charakter extends BaseEntity {
    private String name;
    private String herkunftsOrt;
    private List<Integer> produktIds;

    public Charakter(int id, String name, String herkunftsOrt, List<Integer> produktIds) {
        super(id);
        this.name = name;
        this.herkunftsOrt = herkunftsOrt;
        this.produktIds = produktIds;
    }

    public String getName() {
        return name;
    }

    public String getHerkunftsOrt() {
        return herkunftsOrt;
    }

    public List<Integer> getProduktIds() {
        return produktIds;
    }

    //todo IMPORTANT NU AM VOIE SA UIT!!!!!!! si nu uita de LISTA DE OBIECTE!!
    public void addProdukt(int produktId) {
        produktIds.add(produktId);
    }


    @Override
    public String toString() {
        return "Charakter{" + "id=" + id + ", name='" + name + '\'' + ", herkunftsOrt='" + herkunftsOrt + '\'' + ", produktIds=" + produktIds + '}';
    }
}
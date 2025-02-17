public class Produkt extends BaseEntity {
    private String name;
    private double preis;
    private String herkunftRegion;

    public Produkt(int id, String name, double preis, String herkunftRegion) {
        super(id);
        this.name = name;
        this.preis = preis;
        this.herkunftRegion = herkunftRegion;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public String getHerkunftRegion() {
        return herkunftRegion;
    }

    @Override
    public String toString() {
        return "Produkte{" + "id=" + id + ", name='" + name + '\'' + ", preis=" + preis + ", herkunftRegion='" + herkunftRegion + '\'' + '}';
    }

}

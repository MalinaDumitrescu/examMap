public class Produkt extends BaseEntity {
    private String name;
    private double price;
    private String season;

    public Produkt(int id, String name, double price, String season) {
        super(id);
        this.name = name;
        this.price = price;
        this.season = season;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSeason() { return season; }

    @Override
    public String toString() {
        return "Produkt{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", season='" + season + '\'' + '}';
    }
}

package trabalho.grafos;

public class City {
    private final String name;
    private final Point point;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.point = new Point(latitude, longitude);
    }

    public String getName() {
        return this.name;
    }

    public Point getPoint() {
        return this.point;
    }
}

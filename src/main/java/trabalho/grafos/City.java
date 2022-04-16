package trabalho.grafos;

public class City {
    private final String name;
    private final String state;
    private final Point point;

    public City(String name, String state, double latitude, double longitude) {
        this.name = name;
        this.state = state;
        this.point = new Point(latitude, longitude);
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public Point getPoint() {
        return this.point;
    }

    @Override
    public boolean equals(Object object) {
        var city = (City) object;
        return this.name.equals(city.name) && this.state.equals(city.state);
    }
}

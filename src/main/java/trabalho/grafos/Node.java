package trabalho.grafos;

public class Node {
    private final String value;
    private final double weight;

    public Node(String value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%.2fKm", this.weight / 1000);
    }
}

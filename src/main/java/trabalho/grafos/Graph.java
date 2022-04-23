package trabalho.grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Graph {
    private final int maxCities = 3;
    private final double initDistance = 2000;
    private final Map<String, List<Node>> map;

    public Graph() {
        this.map = new HashMap<>();
    }

    private void addEdge(String source, String destination, double weight) {
        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(new Node(destination, weight));
    }

    private void addVertex(String source) {
        map.put(source, new LinkedList<>());
    }

    private void matrizAdjacencia(ArrayList<City> adjacencyList) {
        int count = 0;
        double vetor[] = new double[3];

        for (var city : adjacencyList) {
            var compareList = new ArrayList<>(adjacencyList);
            compareList.sort(Comparator.comparing(x -> city.getPoint().distanceBetween(x.getPoint())));

            count = 0;
            for (var cityord : compareList) {
                if (city.equals(cityord)) {
                    continue;
                }

                if (!(count >= this.maxCities)) {
                    vetor[count] = city.getPoint().distanceBetween(cityord.getPoint()) / 1000;
                    count++;
                }
            }

            var compareLista = new ArrayList<>(adjacencyList);
            System.out.print(String.format("%s: [", city.getName()));
            for (var city2 : compareLista) {
                if (city.equals(city2)) {
                    continue;
                }
                for (int i = 0; i < 3; i++) {
                    if (city.getPoint().distanceBetween(city2.getPoint()) / 1000 == vetor[i]) {
                        System.out.printf(
                                String.format("%.2fKm ", city.getPoint().distanceBetween(city2.getPoint()) / 1000));
                    }
                }
                System.out.print(" 0 ");
            }
            System.out.print("] \n");

        }
    }

    public void load(String path) throws IOException {
        var stream = getClass().getClassLoader().getResourceAsStream(path);
        assert stream != null;

        var streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        var bufferedReader = new BufferedReader(streamReader);
        var cities = new ArrayList<City>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            var tokens = line.split(";");
            if (tokens[0].equals("city"))
                continue;

            var latitude = Double.parseDouble(tokens[1].replace(',', '.'));
            var longitude = Double.parseDouble(tokens[2].replace(',', '.'));
            cities.add(new City(tokens[0], tokens[5], latitude, longitude));
        }

        matrizAdjacencia(cities);

        for (var city : cities) {
            var compareList = new ArrayList<>(cities);
            compareList.sort(Comparator.comparing(x -> city.getPoint().distanceBetween(x.getPoint())));

            int count = 0;
            for (var city2 : compareList) {
                if (city.equals(city2)) {
                    continue;
                }

                if (!(count >= this.maxCities)) {
                    var distance = city.getPoint().distanceBetween(city2.getPoint());
                    var source = String.format("%s", city.getName());
                    var destination = String.format("%s", city2.getName());

                    addEdge(source, destination, distance);
                    count++;
                } else {
                    var distance = 0.0;
                    var source = String.format("%s", city.getName());
                    var destination = String.format("%s", city2.getName());

                    addEdge(source, destination, distance);

                }
            }
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (var key : map.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append(" [ ");

            for (var node : map.get(key)) {
                stringBuilder.append(node);
                stringBuilder.append("  ");
            }
            stringBuilder.append("]\n");
        }

        return stringBuilder.toString();
    }
}

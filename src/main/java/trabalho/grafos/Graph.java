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

        for (var city : cities) {
            var compareList = new ArrayList<>(cities);
            compareList.sort(Comparator.comparing(x -> city.getPoint().distanceBetween(x.getPoint())));

            int count = 0;
            for(var city2 : compareList) {
                if(city.equals(city2))
                    continue;

                if(count >= this.maxCities)
                    break;

                var distance = city.getPoint().distanceBetween(city2.getPoint());
                var source = String.format("%s (%s)", city.getName(), city.getState());
                var destination = String.format("%s (%s)", city2.getName(), city2.getState());

                addEdge(source, destination, distance);
                count++;
            }
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (var key : map.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append(": ");

            for (var node : map.get(key)) {
                stringBuilder.append(node);
                stringBuilder.append(", ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}

package trabalho.grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Graph {
    private final int maxCities = 3;
    private final double initDistance = 2000;
    private final Map<String, List<String>> map;

    public Graph() {
        this.map = new HashMap<>();
    }

    private void addEdge(String source, String destination) {
        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
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
        while((line = bufferedReader.readLine()) != null) {
            var tokens = line.split(";");
            if(tokens[0].equals("city"))
                continue;

            var latitude = Double.parseDouble(tokens[1].replace(',', '.'));
            var longitude = Double.parseDouble(tokens[2].replace(',', '.'));

            cities.add(new City(tokens[0], latitude, longitude));
        }

        for(var city : cities) {
            var lastDistance = this.initDistance;
            int count = 0;
            for (var value : cities) {
                if (count >= this.maxCities)
                    break;

                if(value.getName().equals(city.getName()))
                    continue;

                var distance = city.getPoint().distanceBetween(value.getPoint());
                if (distance < lastDistance) {
                    lastDistance = distance;
                    count++;
                    addEdge(city.getName(), value.getName());
                }
            }
        }
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append(": ");

            for (String vertices : map.get(key)) {
                stringBuilder.append(vertices);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}

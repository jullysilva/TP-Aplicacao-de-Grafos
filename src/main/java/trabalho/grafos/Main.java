package trabalho.grafos;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            var graph = new Graph();
            graph.load("br.csv");
            System.out.println(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

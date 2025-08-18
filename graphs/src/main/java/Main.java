package main.java;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode(0, 1,2,3);
        graph.addNode(1, 2);
        graph.addNode(2);
        graph.addNode(3, 0);
        System.out.println(graph);
    }
}

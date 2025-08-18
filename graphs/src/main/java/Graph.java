package main.java;

import java.util.Arrays;

public class Graph {
    private static final int MAX_SIZE = 10;

    int nodes[][] = new int[MAX_SIZE][2];
    int nodeCount = 0;
    int firstEdge[] = new int[MAX_SIZE];
    int endnodes[] = new int[MAX_SIZE];

    public Graph() {

    }

    public Graph(int key, int... endnodes) {
        addNode(key, endnodes);
    }

    public void addNode(int key, int... endnodes) {
        nodes[nodeCount][0] = key;
        nodes[nodeCount][1] = nodeCount;
        nodeCount++;
        firstEdge[nodeCount] = nodes[nodeCount - 1][1];
        for (int i = 0; i < endnodes.length; i++) {
            this.endnodes[nodeCount + i] = endnodes[i];
        }
    }

    @Override
    public String toString() {
        String out = "";
        for (int[] arr : nodes) {
            out += Arrays.toString(arr);
        }
        out += "\n" + Arrays.toString(firstEdge) + "\n" + Arrays.toString(endnodes);
        return out;
    }
}

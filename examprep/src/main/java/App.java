package main.java;

import java.util.Arrays;
import java.util.Random;

import main.java.search.BinarySearch;
import main.java.search.BoyerMoore;
import main.java.sorting.InsertionSort;
import main.java.trees.AVLTree;
import main.java.trees.Node;

public class App implements BinarySearch<Integer>, InsertionSort<Integer>, BoyerMoore {
    final static long SEED = Long.MAX_VALUE;
    final static int MAX_HEIGHT = 15;
    final static int ARRAY_MAX = 10;
    final static int MAX = 5 * ARRAY_MAX;

    public static void main(String[] args) {
        Integer arr[] = new Integer[ARRAY_MAX];
        Random gen = new Random();
        App app = new App();
        for (int i = 0; i < ARRAY_MAX; i++) {
            arr[i] = gen.nextInt(MAX);
        }
        System.out.println();
        System.out.println("Unsorted Array: ");
        System.out.println(Arrays.toString(arr));
        app.sort(arr);
        System.out.println("Sorted Array: ");
        System.out.println(Arrays.toString(arr));

        String pattern = "Hallo!";
        String str = "sdaoizfoiasfioHallo!osadfhoiahdsfo";

        System.out.println(app.indexOf(pattern, str));

        AVLTree<Integer, Integer> avl = new AVLTree<Integer, Integer>(new Node<Integer, Integer>(MAX / 2, MAX / 2));
        for (int i = 0; i < MAX; i++) {
            avl.insertNode(new Node<Integer, Integer>(i, i));
        }
        avl.printTree();
        System.out.println(avl.getNodeCount());
        System.out.println(avl.getTreeHeight());
    }
}

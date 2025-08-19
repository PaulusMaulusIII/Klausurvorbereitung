package main.java;

import java.util.Arrays;
import java.util.Random;

import main.java.search.BinarySearch;
import main.java.sorting.BubbleSort;

public class App implements BinarySearch<Integer>, BubbleSort<Integer> {
    final static long SEED = Long.MAX_VALUE;
    final static int MAX = 1000;
    final static int MAX_HEIGHT = 4;
    final static int ARRAY_MAX = 100;

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

        System.out.println(app.indexOf(gen.nextInt(MAX), arr));
    }
}

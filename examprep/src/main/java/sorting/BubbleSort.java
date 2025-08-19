package main.java.sorting;

public interface BubbleSort<K extends Comparable<K>> extends Sort<K> {
    @Override
    default void sort(K[] array) {
        for (int n = array.length; n > 1; n--)
            for (int i = 0; i < n - 1; i++)
                if (array[i].compareTo(array[i + 1]) > 0)
                    swap(i, i + 1, array);
    }
}

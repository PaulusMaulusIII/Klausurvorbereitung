package main.java.sorting;

public interface SelectionSort<K extends Comparable<K>> extends Sort<K> {
    @Override
    default void sort(K[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j].compareTo(array[jMin]) < 1)
                    jMin = j;
            if (jMin != i)
                swap(i, jMin, array);
        }
    }
}

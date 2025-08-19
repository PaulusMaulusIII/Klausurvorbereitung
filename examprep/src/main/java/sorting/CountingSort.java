package main.java.sorting;

import java.lang.reflect.Array;

public interface CountingSort<K extends Comparable<K>> extends Sort<K> {
    @Override
    default void sort(K[] array) {
        int k = arrayMax(array);
        int[] c = new int[k];
        for (int j = 0; j < array.length; j++)
            c[array[j].hashCode() % k] += 1;
        for (int m = 1; m < k; m++)
            c[m] += c[m - 1];

        @SuppressWarnings("unchecked")
        K[] b = (K[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        for (int j = array.length - 1; j >= 0; j--) {
            int idx = array[j].hashCode() % k;
            c[idx]--;
            b[c[idx]] = array[j];
        }

        System.arraycopy(b, 0, array, 0, array.length);
    }

    private int arrayMax(K[] array) {
        // int res = 0;
        // for (K k : array)
        // if (k.hashCode() > res)
        // res = k.hashCode();
        // return res + 1;
        return (5 * array.length) + 1;
    }
}

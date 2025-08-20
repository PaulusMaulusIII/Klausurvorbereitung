package main.java.sorting;

import java.lang.reflect.Array;

public interface CountingSort<K extends Comparable<K>> extends Sort<K> {

    // WAS PASSIERT HIER:
    // Array: [12, 14, 12, 3, 2 ,12]
    // V : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
    // c : [0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 1] Die Vorkommen
    // werden abgezählt, len(c) = max(array) + 1
    // c : [0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 6] Das Vorkommen
    // werden Akkumuliert
    // Wir gehen das Eingabe Array von R nach L durch
    // idx : 12 -> 5(--) -> 4 => b : [ 0, 0, 0, 0, 12 , 0]
    // idx : 2 -> 1(--) -> 0 => b : [ 2, 0, 0, 0, 12, 0]
    // idx : 3 -> 2(--) -> 1 => b : [ 2, 3, 0, 0, 12, 0]
    // idx : 12 -> 4(--) -> 3 => b : [ 2, 3, 0, 12, 12, 0]
    // idx : 14 -> 6(--) -> 5 => b : [ 2, 3, 0, 12, 12, 14]
    // idx : 12 -> 3(--) -> 2 => b : [ 2, 3, 12, 12, 12, 14]
    // Das Array ist sortiert!
    // Zuletzt array = b

    @Override
    default void sort(K[] array) {
        int k = arrayMax(array); // Höchster Wert, der im Datensatz vorkommen kann + 1 (Für array Länge)
        int[] c = new int[k]; // Zähler Array
        for (int j = 0; j < array.length; j++)
            c[array[j].hashCode() % k] += 1; // Zähle Vorkommen der Keys im Array
        for (int m = 1; m < k; m++)
            c[m] += c[m - 1]; // Akkumuliere

        @SuppressWarnings("unchecked")
        K[] b = (K[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        for (int j = array.length - 1; j >= 0; j--) {
            int idx = array[j].hashCode() % k; // Berechne Adresse +1
            c[idx]--; // Berechne Adresse
            b[c[idx]] = array[j]; // Platziere Wert in b an in c errechnete Adresse
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

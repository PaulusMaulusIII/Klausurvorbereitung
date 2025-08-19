package test.java;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import main.java.sorting.BubbleSort;

class BubbleSortTest implements SortTest<Integer>, BubbleSort<Integer> {

    private BubbleSortTest s;

    @BeforeEach
    void setUp() throws Exception {
        s = new BubbleSortTest();
    }

    @RepeatedTest(100)
    void testCorrectness() {
        int[] temp = InputGenerator.randomSequence(1000);
        Integer[] a = java.util.Arrays.stream(temp).boxed().toArray(Integer[]::new);
        s.sort(a);
        assertTrue(isSorted(a));
    }

}

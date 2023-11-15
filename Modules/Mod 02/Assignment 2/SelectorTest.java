package Selectors;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import static Selectors.Selector.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectorTest {

    @Test
    void minTest() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(7);
        coll.add(6);
        coll.add(1);
        coll.add(2);
        assertEquals(1, Selector.min(coll, ascendingInteger));


    }

    @Test
    void max() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(7);
        coll.add(6);
        coll.add(1);
        coll.add(2);
        assertEquals(7, Selector.max(coll, ascendingInteger));
    }

    @Test
    void kmin() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(2);
        coll.add(8);
        coll.add(7);
        coll.add(3);
        coll.add(4);
        int k = 1;
        assertEquals(2, Selector.kmin(coll, k, ascendingInteger));

        ArrayList<Integer> coll2 = new ArrayList<Integer>();
        coll2.add(5);
        coll2.add(9);
        coll2.add(1);
        coll2.add(7);
        coll2.add(3);
        int k2 = 3;
        assertEquals(5, Selector.kmin(coll2, k2, ascendingInteger));

        ArrayList<Integer> coll3 = new ArrayList<Integer>();
        coll3.add(5);
        coll3.add(7);
        int k3 = 2;
        assertEquals(7, Selector.kmin(coll3, k3, ascendingInteger));
    }

    @Test
    void kmax() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(2);
        coll.add(8);
        coll.add(7);
        coll.add(3);
        coll.add(4);
        int k = 1;
        assertEquals(8, Selector.kmax(coll, k, ascendingInteger));

        ArrayList<Integer> coll2 = new ArrayList<Integer>();
        coll2.add(5);
        coll2.add(9);
        coll2.add(1);
        coll2.add(7);
        coll2.add(3);
        int k2 = 3;
        assertEquals(5, Selector.kmax(coll2, k2, ascendingInteger));
    }

    @Test
    void range() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(7);
        coll.add(6);
        coll.add(1);
        coll.add(2);
        coll.add(9);
        coll.add(10);
        int low = 3;
        int high = 9;
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(7);
        expected.add(6);
        expected.add(9);
        assertEquals(expected, Selector.range(coll, low, high, ascendingInteger));

        ArrayList<Double> coll2 = new ArrayList<Double>();
        coll2.add(7.0);
        coll2.add(6.0);
        coll2.add(1.0);
        coll2.add(5.4);
        coll2.add(6.3);
        double low2 = 2.1;
        double high2 = 6.5;
        ArrayList<Double> expected2 = new ArrayList<Double>();
        expected2.add(6.0);
        expected2.add(5.4);
        expected2.add(6.3);
        assertEquals(expected2, Selector.range(coll2, low2, high2, ascendingDouble));
    }

    @Test
    void ceiling() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(7);
        coll.add(6);
        coll.add(1);
        coll.add(2);
        coll.add(9);
        coll.add(10);
        int key = 5;
        assertEquals(6, Selector.ceiling(coll, key, ascendingInteger));

        ArrayList<Double> coll2 = new ArrayList<Double>();
        coll2.add(7.0);
        coll2.add(6.0);
        coll2.add(1.0);
        coll2.add(5.4);
        double key2 = 5.5;
        assertEquals(6.0, Selector.ceiling(coll2, key2, ascendingDouble));
    }

    @Test
    void floor() {
        ArrayList<Integer> coll = new ArrayList<Integer>();
        coll.add(7);
        coll.add(6);
        coll.add(1);
        coll.add(2);
        coll.add(9);
        coll.add(10);
        int key = 5;
        assertEquals(2, Selector.floor(coll, key, ascendingInteger));

        ArrayList<Double> coll2 = new ArrayList<Double>();
        coll2.add(7.0);
        coll2.add(6.0);
        coll2.add(1.0);
        coll2.add(5.4);
        double key2 = 5.5;
        assertEquals(5.4, Selector.floor(coll2, key2, ascendingDouble));

        ArrayList<Integer> coll3 = new ArrayList<Integer>();
        coll3.add(9);
        coll3.add(7);
        int key3 = 7;
        assertEquals(7, Selector.floor(coll3, key3, ascendingInteger));
    }

    /**
     * Defines a total order on integers as ascending natural order.
     */
    static Comparator<Integer> ascendingInteger =
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1.compareTo(i2);
                }
            };

    static Comparator<Double> ascendingDouble =
            new Comparator<Double>() {
                public int compare(Double d1, Double d2) {
                    return d1.compareTo(d2);
                }
            };
}

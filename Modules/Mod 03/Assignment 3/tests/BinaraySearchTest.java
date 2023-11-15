import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {


    @Test
    void firstIndexOfTest() {
        Term[] terms = {new Term("azcei", 12),
                new Term("abct", 14),
                new Term("blae", 8),
                new Term("lx", 4),
                new Term("nalo", 13)};
        int result = BinarySearch.<Term>firstIndexOf(terms, new Term("a", 1), Term.byPrefixOrder(1));
        assertEquals(0, result);
    }

    @Test
    void lastIndexOfTest() {
        Term[] terms = {new Term("azcei", 12),
                new Term("abct", 14),
                new Term("blae", 8),
                new Term("lx", 4),
                new Term("blanket", 13)};
        int result = BinarySearch.<Term>lastIndexOf(terms, new Term("bla", 3), Term.byPrefixOrder(4));
        assertEquals(3, result);
    }


    static Comparator<Integer> ascendingInteger =
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1.compareTo(i2);
                }
            };

    public static Comparator<Term> byDescendingWeightOrder() {
        return null;
    }
}

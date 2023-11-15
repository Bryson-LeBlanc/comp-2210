import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 *
 */
public class Term implements Comparable<Term> {

    private final String query;
    private final long weight;

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }

        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return Long.compare(o2.weight, o1.weight);
            }
        };
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {

        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        return new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                // When prefix "ab" is compared to "abc", the length of "abc" is being set to 2. Why?
                // This is most likely the root of my issue...
                int length1 = o1.query.length();
                int length2 = o2.query.length();
                if (length1 >= length) {
                    length1 = length;
                }
                if (length2 >= length) {
                    length2 = length;
                }


                String query1 = o1.query.substring(0, length1);
                String query2 = o2.query.substring(0, length2);
                return query1.compareTo(query2);
            }
        };
    }

    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return query.compareTo(other.query); // consider changing this compareTo to look more familiar
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
        return query + "\t" + weight;
    }

    /**
     * Driver method.
     */
    public static void main(String[] args)  {
        Term t1 = new Term("azcei", 12);
        Term t2 = new Term("abct", 14);
        Term t3 = new Term("blae", 8);
        Term t4 = new Term("lx", 4);
        Term t5 = new Term("nalo", 13);
        ArrayList<Term> a = new ArrayList<>();
        a.add(t1);
        a.add(t2);
        a.add(t3);
        a.add(t4);
        a.add(t5);
        Collections.sort(a, byPrefixOrder(2));
        for (Term tmp : a)   {
            System.out.println(tmp.query);
        }

        System.out.println();

        Collections.sort(a, byDescendingWeightOrder());
        for (Term tmp : a)   {
            System.out.println(tmp);
        }
    }
}


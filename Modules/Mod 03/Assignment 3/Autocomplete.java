import java.util.Arrays;
import java.util.Iterator;

/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException();
        }
        for (Term term : terms) {
            if (term == null) {
                throw new NullPointerException();
            }
        }

        Arrays.sort(terms);
        this.terms = terms;

    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException();
        }

        Term prefixterm = new Term(prefix, 0);

        // Currently, when I call terms with array a and prefix "ab" in Autocomplete Tests,
        // my var last is 1 and first is 4, so when first - last + 1 is called,
        // I am exiting trying to index at a negative number
        int first = BinarySearch.firstIndexOf(terms, prefixterm, Term.byPrefixOrder(prefix.length()));
        int last = BinarySearch.lastIndexOf(terms, prefixterm, Term.byPrefixOrder(prefix.length()));
        Term[] matchArray = new Term[last - first + 1];
        System.arraycopy(terms, first, matchArray, 0, last - first + 1);
        Arrays.sort(matchArray, Term.byDescendingWeightOrder());

        return matchArray;
    }

}

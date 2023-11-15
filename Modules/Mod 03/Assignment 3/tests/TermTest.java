import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class TermTest {

    Term t1 = new Term("azcei", 12);
    Term t2 = new Term("abct", 14);
    Term t3 = new Term("blae", 8);
    Term t4 = new Term("lx", 4);
    Term t5 = new Term("nalo", 13);

    @Test
    void byDescendingWeightOrder() {
        Term[] terms = {t1, t2, t3, t4, t5};
        Arrays.sort(terms, Term.byDescendingWeightOrder());
        assertEquals(t2, terms[0]);
    };

    @Test
    void byPrefixOrder() {
    }

    @Test
    void compareTo() {
    }
}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutocompleteTest {

    @Test
    void allMatchesTest() {

//        Term[] terms = {new Term("azcei", 12),
//                        new Term("abct", 14),
//                        new Term("blae", 8),
//                        new Term("lx", 4),
//                        new Term("nalo", 13)};

        Term[] terms2 = {new Term("a",	4),
                new Term("ab", 8),
                new Term("abc", 10),
                new Term("abcd", 2),
                new Term("abcde", 6)};

//        Autocomplete autocomplete = new Autocomplete(terms);
//        Term[] matches = autocomplete.allMatches("a");
//        assertEquals(2, matches.length);
//        assertEquals(new Term("abct", 14), matches[0]);
//        assertEquals(new Term("azcei", 12), matches[1]);
        Autocomplete auto = new Autocomplete(terms2);
        Term[] matches = auto.allMatches("ab");
        assertEquals(3, matches.length);
    }
}

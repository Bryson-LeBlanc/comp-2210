import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int found = -1;
        int bottom = 0;
        int top = a.length - 1;

        while (bottom <= top) {
            int between = (bottom + top) / 2;
            int comp = comparator.compare(key, a[between]);
            if (comp >= 0) {
                bottom = between + 1;
                found = between;
            }
            else {
                top = between - 1;
            }


        }
        return found;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException();
        }

        int found = -1;
        int bottom = 0;
        int top = a.length - 1;

        while (bottom <= top) {
            int between = ((bottom + top) / 2);
            int comp = comparator.compare(key, a[between]);
            if (comp <= 0) {
                found = between;
                top = between - 1;
            }
            else {
                // may change to between - 1
                bottom = between + 1;
            }

        }
        // this may error out in test case of first index at 0
        return found;
    }

    public static Comparator<Integer> accendingorder() {
        class applePIE implements Comparator<Integer> {
            public int compare(Integer A, Integer B){
                if(B > A){
                    return -1;
                } else if(B < A) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return new applePIE();
    }

    public static void main(String[] args){
        Integer apples[] = {2, 2, 2};
        int i = firstIndexOf(apples, 2, accendingorder());
        System.out.println(i);
    }

}

package Selectors;
// only take lines 3 and below for vocareum //
import java.util.*;


/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Bryson LeBlanc (bwl0016@auburn.edu)
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        Iterator<T> itr = coll.iterator();
        T min = itr.next();
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(next, min) <= 0) {
                min = next;
            }
        }

        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        Iterator<T> itr = coll.iterator();
        T max = itr.next();
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(next, max) >= 0) {
                max = next;
            }
        }

        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        T kmin = min(coll,comp);
        int condition = k - 1;
        if (condition == 0) {
            return kmin;
        }

        Iterator<T> itr = coll.iterator();
        ArrayList<T> nodupes = new ArrayList<T>();
        while (itr.hasNext()) {
            T next = itr.next();
            if (!nodupes.contains(next)) {
                nodupes.add(next);
            }
        }
        if (nodupes.size() < k) {
            throw new NoSuchElementException();
        }

        boolean foundk = false;
        for (int i = 0; i < nodupes.size(); i++) {
            int count = 0;
            for (int j = 0; j < nodupes.size(); j++) {
                if (comp.compare(nodupes.get(i), nodupes.get(j)) > 0) {
                    count++;
                }
            }
            if (count == condition) {
                kmin = nodupes.get(i);
                foundk = true;
            }
        }

        if (!foundk) {
            throw new NoSuchElementException();
        }
        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        T kmax = max(coll,comp);
        int condition = k - 1;
        if (condition == 0) {
            return kmax;
        }

        Iterator<T> itr = coll.iterator();
        ArrayList<T> nodupes = new ArrayList<T>();
        while (itr.hasNext()) {
            T next = itr.next();
            if (!nodupes.contains(next)) {
                nodupes.add(next);
            }
        }
        if (nodupes.size() < k) {
            throw new NoSuchElementException();
        }

        boolean foundk = false;
        for (int i = 0; i < nodupes.size(); i++) {
            int count = 0;
            for (int j = 0; j < nodupes.size(); j++) {
                if (comp.compare(nodupes.get(i), nodupes.get(j)) < 0) {
                    count++;
                }
            }
            if (count == condition) {
                kmax = nodupes.get(i);
                foundk = true;
            }
        }
        if (!foundk) {
            throw new NoSuchElementException();
        }
        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        Collection<T> result = new ArrayList<T>();
        Iterator<T> itr = coll.iterator();
        boolean found1 = false;

        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(next, low) >= 0 && comp.compare(next, high) <= 0) {
                result.add(next);
                found1 = true;
            }
        }

        if (!found1) {
            throw new NoSuchElementException();
        }

        return result;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        Iterator<T> itr = coll.iterator();
        T ceiling = max(coll, comp);
        boolean found = false;
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(next, key) >= 0) {
                if (comp.compare(next, ceiling) <= 0) {
                    ceiling = next;
                    found = true;
                }
            }
        }
        // cases where ceiling is the max value in coll needs to be addressed
        if (!found) {
            throw new NoSuchElementException();
        }

        return ceiling;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        Iterator<T> itr = coll.iterator();
        T floor = min(coll, comp);
        boolean found = false;
        while (itr.hasNext()) {
            T next = itr.next();
            if (comp.compare(next, key) <= 0) {
                if (comp.compare(next, floor) >= 0) {
                    floor = next;
                    found = true;
                }
            }
        }
        // cases where floor is the min value in coll needs to be addressed
        if (!found) {
            throw new NoSuchElementException();
        }

        return floor;
    }
}


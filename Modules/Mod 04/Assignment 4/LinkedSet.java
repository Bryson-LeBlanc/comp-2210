import java.util.Iterator;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    /////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null || contains(element)) {
            return false;
        }
        Node Auburn = new Node(element);
        if (size == 0) {
            front = Auburn;
            rear = Auburn;
            size++;
            return true;
        }

        Node n = new Node(element);
        Node p = front;

        // if we have reached this point, element is not in this list, AND there are
        // other elements present.
        while (p != null) {
            // front case
            if (p.element.compareTo(n.element) > 0) {
                n.next = front;
                front.prev = n;
                front = n;
                // rear = p;
                size++;
                return true;
            }
            // back case
            if (p.element.compareTo(n.element) < 0 && p.next == null) {
                p.next = n;
                n.prev = p;
                rear = n;
                size++;
                return true;
            }
            // middle case
            if (p.element.compareTo(n.element) < 0
                    && p.next.element.compareTo(n.element) > 0) {
                n.next = p.next;
                p.next = n;
                n.prev = p;
                n.next.prev = n;
                size++;
                return true;
            }
            p = p.next;
        }

        return false;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        if (element == null || !contains(element)) {
            return false;
        }
        Node n = new Node(element);
        Node p = front;

        boolean firstN = true;

        if (size == 1) {
            front = null;
            rear = null;
            size--;
            return true;
        }

        while (p != null) {
            // front case
            if (p.element.compareTo(n.element) == 0 && firstN) {
                front = front.next;
                front.prev = null;
                size--;
                return true;
            }
            // back case
            if (p.element.compareTo(n.element) == 0
                    && p.next == null) {
                rear = rear.prev;
                rear.next = null;
                size--;
                return true;
            }
            // middle case
            if (element.compareTo(p.element) == 0 && !firstN) {
                p.next.prev = p.prev;
                p.prev.next = p.next;
                size--;
                return true;
            }
            p = p.next;
            firstN = false;
        }

        return false;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        Node current = front;
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        Node Auburn = front;

        while (Auburn != null) {
            if (!s.contains((T)Auburn.element)){
                return false;
            }
            Auburn = Auburn.next;
        }
        return true;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        Node Auburn = front;

        while (Auburn != null) {
            if (!s.contains((T)Auburn.element)){
                return false;
            }
            Auburn = Auburn.next;
        }
        return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        Set<T> result = new LinkedSet<>();
//        Node Auburn = front;

        if (s.isEmpty()) {
            return this;
        }
        if (this.isEmpty()) {
            return s;
        }

        for (T element: s) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        for (T element : this) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        LinkedSet<T> result = new LinkedSet<>();
        Node Auburn = front;

        if (s.isEmpty()) {
            return this;
        }
        if (this.isEmpty()) {
            return s;
        }
        Node Opelika = s.front;

        while (Auburn != null) {
            if (!result.contains((T)Auburn.element)){
                result.add((T)Auburn.element);
            }
            if (!result.contains((T)Opelika.element)){
                result.add((T)Opelika.element);
            }
            Auburn = Auburn.next;
            Opelika = Opelika.next;
        }
        return result;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node Auburn = front;

        if (s.isEmpty()) {
            return s;
        }
        if (this.isEmpty()) {
            return this;
        }
        while (Auburn != null) {
            if (this.contains((T)Auburn.element)
                    && s.contains((T)Auburn.element)) {
                result.add((T)Auburn.element);
            }
            Auburn = Auburn.next;
        }
        return result;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node Auburn = front;

        if (s.isEmpty()) {
            return s;
        }
        if (this.isEmpty()) {
            return this;
        }
        Node Opelika = s.front;

        while (Auburn != null) {
            if (this.contains((T)Auburn.element)
                    && s.contains((T)Auburn.element)) {
                result.add((T)Auburn.element);
            }
            Auburn = Auburn.next;
            Opelika = Opelika.next;
        }
        return result;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node Auburn = front;

        while (Auburn != null) {
            if (!s.contains((T)Auburn.element)){
                result.add((T)Auburn.element);
            }
            Auburn = Auburn.next;
        }
        return result;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node Auburn = front;

        while (Auburn != null) {
            if (!s.contains((T)Auburn.element)){
                result.add((T)Auburn.element);
            }
            Auburn = Auburn.next;
        }
        return result;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new DescendingLinkedIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {

        Iterator<Set<T>> powa = new Iterator<Set<T>>() {
            int index = 0;
            Node back = rear;
            Node current = back;
            LinkedSet<T> set;
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean hasNext() {
                return index < Math.pow(2, size);
            }

            @Override
            public LinkedSet<T> next() {
                LinkedSet<T> set = new LinkedSet<T>();
                String bi = Integer.toBinaryString(index);
                back = rear;
                for (int i = 0; i < bi.length(); i++) {
                    int len = bi.length();
                    if (binaryStringIndexComparison(bi, len, i) == 1) {
                        set.add(back.element);
                    }
                    current = back;
                    if (back != null) {
                        back = back.prev;
                    }
                }
                index++;
                return set;
            }

        };
        return powa;
    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////
    private int binaryStringIndexComparison(String binary, int len, int i) {
        return Character.getNumericValue(binary.charAt(len - 1 - i));
    }
    // Feel free to add as many private methods as you need.

    ////////////////////
    // Nested classes //
    ////////////////////
    private class LinkedIterator implements Iterator<T> {
        private Node current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T result = current.element;
            current = current.next;
            return result;
        }
    }

    private class DescendingLinkedIterator implements Iterator<T> {
        private Node current = rear;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T result = current.element;
            current = current.prev;
            return result;
        }
    }



    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}

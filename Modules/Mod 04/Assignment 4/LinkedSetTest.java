import org.junit.jupiter.api.Test;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedSetTest {

    @org.junit.jupiter.api.Test
    void addSizeZero() {
        LinkedSet<Integer> list = new LinkedSet<>();
        assertTrue(list.add(1));}

    @org.junit.jupiter.api.Test
    void addVocTest() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(2);
        assertEquals(5, list.size());
    }

    @org.junit.jupiter.api.Test
    void addFront() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        assertTrue(list.add(1));
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void addBack() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        assertTrue(list.add(5));
        assertEquals(5, (int) list.front.next.element);
    }

    @org.junit.jupiter.api.Test
    void addMiddle() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        list.add(1);
        assertTrue(list.add(2));
        assertTrue(list.add(3));
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void addAndRemove() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(1);
        list.add(2);
        list.remove(1);
        assertEquals(1, list.size());
        System.out.println(list);
        assertNull(list.front.prev);
    }

    @org.junit.jupiter.api.Test
    void addAndRemove2() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(2);
        list.remove(4);
        list.remove(1);
        list.remove(5);
        assertEquals(2, list.size());
        System.out.println(list);
        assertNull(list.front.prev);
    }

    @org.junit.jupiter.api.Test
    void removeSize1() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        assertTrue(list.remove(4));
        System.out.println(list);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(4);
        list.add(1);
        assertTrue(list.remove(4));
        assertTrue(list.remove(1));
        System.out.println(list);
    }

    @org.junit.jupiter.api.Test
    void contains() {
        LinkedSet<Integer> list = new LinkedSet<>();
        list.add(1);
        assertTrue(list.contains(1));
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        LinkedSet<Integer> t = new LinkedSet<>();
        LinkedSet<Integer> s = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        s.add(2);
        s.add(3);
        s.add(1);
        assertTrue(t.equals(s));
    }

    @org.junit.jupiter.api.Test
    void testEquals1() {
        LinkedSet<Integer> t = new LinkedSet<>();
        LinkedSet<Integer> s = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        s.add(2);
        s.add(4);
        s.add(1);
        assertFalse(t.equals(s));
    }

    @org.junit.jupiter.api.Test
    void union() {
        LinkedSet<Integer> t = new LinkedSet<>();
        LinkedSet<Integer> s = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        s.add(2);
        s.add(4);
        s.add(1);
        Set<Integer> test = t.union(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void union2() {
        LinkedSet<Integer> t = new LinkedSet<>();
        LinkedSet<Integer> s = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
//        s.addNoOrder(3);
//        s.addNoOrder(4);
//        s.addNoOrder(2);
        Set<Integer> test = t.union(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void union3() {
        LinkedSet<Integer> linked = new LinkedSet<>();
        Set<Integer> set = new LinkedSet<>();
        linked.add(2);
        linked.add(3);
        linked.add(1);
        set.add(1);
        set.add(2);
        set.add(3);
        Set<Integer> union = linked.union(set);
        Integer[] output = {1,2,3};
        int i = 0;
        for (Integer element : union) {
            assertEquals(output[i++], element);
        }

    }

    @org.junit.jupiter.api.Test
    void testUnion() {
        LinkedSet<Integer> t = new LinkedSet<>();
        LinkedSet<Integer> s = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        Set<Integer> test = t.union(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void intersection() {
        LinkedSet<Integer> t = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        LinkedSet<Integer> s = new LinkedSet<>();
        s.add(1);
        s.add(2);
        s.add(3);
        Set<Integer> test = t.intersection(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void intersectionEmpty() {
        LinkedSet<Integer> t = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        LinkedSet<Integer> s = new LinkedSet<>();
        Set<Integer> test = t.intersection(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void testIntersection() {
        LinkedSet<Integer> t = new LinkedSet<>();
        t.add(1);
        t.add(2);
        t.add(3);
        LinkedSet<Integer> s = new LinkedSet<>();
        s.add(2);
        s.add(3);
        s.add(4);
        Set<Integer> test = t.intersection(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void testIntersection2() {
        LinkedSet<Integer> t = new LinkedSet<>();
        t.add(2);
        t.add(3);
        t.add(4);
        LinkedSet<Integer> s = new LinkedSet<>();
        s.add(1);
        s.add(2);
        s.add(3);
        Set<Integer> test = t.intersection(s);
        System.out.println(test);
    }

    @org.junit.jupiter.api.Test
    void complement() {
        LinkedSet<Integer> s = new LinkedSet<>();
        s.add(1);
        s.add(2);
        s.add(3);
        LinkedSet<Integer> Auburn = new LinkedSet<>();
        Auburn.add(1);
        Auburn.add(2);
        Auburn.add(3);
        Set<Integer> test = Auburn.complement(s);
        System.out.println(test);

    }

    @org.junit.jupiter.api.Test
    void testComplement() {
    }

    @Test
    void iterator() {
        LinkedSet<Integer> test = new LinkedSet<>();
        test.add(1);
        test.add(3);
        test.add(2);
        Iterator<Integer> itr = test.iterator();
        int expSize = 3;
        int actSize = 0;
        Integer[] order = {1, 2, 3};
        int i = 0;
        int j;
        while (itr.hasNext()) {
            actSize++;
            j = itr.next();
            assertEquals(order[i++], j);
        }
        assertEquals(expSize, actSize);
    }

    @org.junit.jupiter.api.Test
    void descendingIterator() {
        LinkedSet<Integer> test = new LinkedSet<>();
        test.add(1);
        test.add(3);
        test.add(2);
        Iterator<Integer> itr = test.descendingIterator();
        int expSize = 3;
        int actSize = 0;
        Integer[] order = {1, 2, 3};
        int i = order.length - 1;
        int j;
        while (itr.hasNext()) {
            actSize++;
            j = itr.next();
            assertEquals(order[i--], j);
        }
        assertEquals(expSize, actSize);
    }

    @org.junit.jupiter.api.Test
    void powerSetIterator() {
        LinkedSet<Integer> test = new LinkedSet<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        Iterator<Set<Integer>> itr = test.powerSetIterator();
        int expSize = 32;
        int actSize = 0;
        Object[] order = new Object[expSize];
        while (itr.hasNext()) {
            Set<Integer> ps = itr.next();
            assertFalse(ps == null);
            order[actSize++] = ps;
        }
        assertEquals(expSize, actSize);
        for (int i = 0; i < expSize; i++) {
            for (int j = 0; j < expSize; j++) {
                if (i != j) {
                    assertFalse(order[i].equals(order[j]));
                }
            }
    }
    }
}

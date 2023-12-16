package org.arcad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private IntegerListImpl testList;
    private long start;
    private long finish;
    private int MAX_SIZE = 200;

    @BeforeEach
    void setUp() throws Exception {
        testList = new IntegerListImpl();
        testList.add(100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addNumber() throws Exception {
        assertEquals(100, testList.get(0));
    }

    @Test
    void sortBobble() throws Exception {
        IntegerListImpl birInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            birInt.add((int) (random()*100));
        }
        start = System.currentTimeMillis();
        birInt.sortBobble();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Bobbly = "+finish);
    }

    @Test
    void sortSelection() throws Exception {
        IntegerListImpl birInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            birInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        birInt.sortSelection();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Selection = "+finish);
    }

    @Test
    void sortInsertion() throws Exception {
        IntegerListImpl birInt = new IntegerListImpl(100000);
        for (int i = 1; i < 100000; i++) {
            birInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        birInt.sortInsertion();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Insertion = "+finish);
    }

    @Test
    void allSort() throws Exception {
        start = System.currentTimeMillis();
        IntegerListImpl bigInt1 = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            bigInt1.add((int) (random() * 100));
        }
        IntegerListImpl bigInt2 = new IntegerListImpl(bigInt1);
        IntegerListImpl bigInt3 = new IntegerListImpl(bigInt2);
        IntegerListImpl bigInt4 = new IntegerListImpl(bigInt3);
        finish = System.currentTimeMillis() - start;
        System.out.println("Initial List = " + finish);

        System.out.println("bigInt1 = " + bigInt1.toString());
        start = System.currentTimeMillis();
        bigInt1.sortBobble();
        finish = System.currentTimeMillis() - start;
        System.out.println("bigInt1 = " + bigInt1.toString());
        System.out.println("Sort Bobbly = "+finish);

        System.out.println("bigInt2 = " + bigInt2.toString());
        start = System.currentTimeMillis();
        bigInt2.sortSelection();
        finish = System.currentTimeMillis() - start;
        System.out.println("bigInt2 = " + bigInt2.toString());
        System.out.println("Sort Selection = "+finish);

        System.out.println("bigInt3 = " + bigInt3.toString());
        start = System.currentTimeMillis();
        bigInt3.sortInsertion();
        finish = System.currentTimeMillis() - start;
        System.out.println("bigInt3 = " + bigInt3.toString());
        System.out.println("Sort Insertion = "+finish);

        System.out.println("bigInt4 = " + bigInt4.toString());
        start = System.currentTimeMillis();
        bigInt4.sortArreys();
        finish = System.currentTimeMillis() - start;
        System.out.println("bigInt4 = " + bigInt4.toString());
        System.out.println("Sort Arreys = "+finish);


    }

    @Test
    void addIndexNumber() throws Exception {
        testList.add(0, 50);
        assertEquals(50, testList.get(0));
        assertEquals(100, testList.get(1));
    }

    @Test
    void set() throws Exception {
        assertEquals(100, testList.get(0));
        testList.set(0, 75);
        assertEquals(75, testList.get(0));
    }

    @Test
    void removeNum() throws ItemNotFoundException, Exception {
        testList.add(0, 50);
        testList.add(1, 75);
        testList.removeNum(50);
        assertEquals(75, testList.get(0));
        assertEquals(100, testList.get(1));
    }

    @Test
    void removeIndex() throws Exception {
        testList.add(0, 50);
        testList.add(1, 75);
        testList.removeIndex(0);
        assertEquals(75, testList.get(0));
        assertEquals(100, testList.get(1));
    }

    @Test
    void contains() {
        assertTrue(testList.contains(100));
        assertFalse(testList.contains(200));
    }

    @Test
    void indexOf() {
        assertEquals(0, testList.indexOf(100));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, testList.lastIndexOf(200));
    }

    @Test
    void get() throws Exception {
        assertEquals(100, testList.get(0));
    }

    @Test
    void testEquals() {
        assertThrows(Exception.class, () -> testList.equals(null));
    }

    @Test
    void size() {
        assertEquals(1, testList.size());
    }

    @Test
    void isEmpty() {
        assertFalse(testList.isEmpty());
    }

    @Test
    void clear() {
        testList.clear();
        assertTrue(testList.isEmpty());
    }

    @Test
    void toArray() {
        Integer[] expectedArray = {100};
        Integer[] actualArray = testList.toArray();
        assertArrayEquals(expectedArray, actualArray);
    }
}
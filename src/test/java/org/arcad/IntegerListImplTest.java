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
    private final int MAX_SIZE = 100_000;

    @BeforeEach
    void setUp() throws Exception {
        testList = new IntegerListImpl();
        testList.add(100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void grow() throws Exception {
        assertEquals(testList.size(), 1);
        for (int i = 1; i <= 8; i++) {
            testList.add((int) (random() * 100));
        }
        assertEquals(9, testList.size());
        testList.add((int) (random() * 100));
        assertEquals(101, (int) testList.add(101));
        assertEquals(11, testList.size());

    }
    @Test
    void addNumber() throws Exception {
        assertEquals(100, testList.get(0));
    }

    @Test
    void sortBobble() throws Exception {
        IntegerListImpl bigInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            bigInt.add((int) (random()*100));
        }
        start = System.currentTimeMillis();
        bigInt.sortBobble();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Bobble = "+finish+"ms");
    }

    @Test
    void sortSelection() throws Exception {
        IntegerListImpl bigInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            bigInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        bigInt.sortSelection();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Selection = "+finish+"ms");
    }

    @Test
    void sortInsertion() throws Exception {
        IntegerListImpl bigInt = new IntegerListImpl(100000);
        for (int i = 1; i < 100000; i++) {
            bigInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        bigInt.sortInsertion();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Insertion = "+finish+"ms");
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
        IntegerListImpl bigInt5 = new IntegerListImpl(bigInt4);
        IntegerListImpl bigInt6 = new IntegerListImpl(bigInt5);
        finish = System.currentTimeMillis() - start;
        System.out.println("Initial List "+MAX_SIZE+": = " + finish +"ms");

//        System.out.println("bigInt1 = " + bigInt1);
        start = System.currentTimeMillis();
        bigInt1.sortBobble();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt1 = " + bigInt1);
        System.out.println("Sort Bobbly = "+finish+"ms");

//        System.out.println("bigInt2 = " + bigInt2);
        start = System.currentTimeMillis();
        bigInt2.sortSelection();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt2 = " + bigInt2);
        System.out.println("Sort Selection = "+finish+"ms");

//        System.out.println("bigInt3 = " + bigInt3);
        start = System.currentTimeMillis();
        bigInt3.sortInsertion();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt3 = " + bigInt3);
        System.out.println("Sort Insertion = "+finish+"ms");

//        System.out.println("bigInt4 = " + bigInt4);
        start = System.currentTimeMillis();
        bigInt4.sortMerge();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt4 = " + bigInt4);
        System.out.println("Sort Merge = "+finish+"ms");

//        System.out.println("bigInt5 = " + bigInt5);
        start = System.currentTimeMillis();
        bigInt5.sortRecurse();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt5 = " + bigInt5);
        System.out.println("Sort Recurse = "+finish+"ms");

//        System.out.println("bigInt6 = " + bigInt6);
        start = System.currentTimeMillis();
        bigInt6.sortArrays();
        finish = System.currentTimeMillis() - start;
//        System.out.println("bigInt6 = " + bigInt6);
        System.out.println("Sort Arrays = "+finish+"ms");

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
    @Test
    void Reverse() throws Exception {
        IntegerListImpl integerList = new IntegerListImpl(5);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        integerList.reverse();

        assertEquals(5, integerList.get(0));
        assertEquals(4, integerList.get(1));
        assertEquals(3, integerList.get(2));
        assertEquals(2, integerList.get(3));
        assertEquals(1, integerList.get(4));
    }

    // Test for the reverse method in the IntegerListImpl class with empty list
    @Test
    void testReverseEmptyList() {
        IntegerListImpl integerList = new IntegerListImpl(0);
        assertDoesNotThrow(integerList::reverse);
    }

    @Test
    void add() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testEquals1() {
    }

    @Test
    void sortArrays() throws Exception {
        IntegerListImpl bigInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            bigInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        bigInt.sortArrays();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Arreys = " + finish +"ms");
    }

    @Test
    void sortRecurse() throws Exception {
        IntegerListImpl bigInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= 10; i++) {
            bigInt.add((int) (random() * 100));
        }
        start = System.currentTimeMillis();
        bigInt.sortRecurse();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Recurse = " + finish +"ms");
    }

    @Test
    void sortMerge() {
        IntegerListImpl bigInt = new IntegerListImpl(MAX_SIZE);
        for (int i = 1; i <= MAX_SIZE; i++) {
            try {
                bigInt.add((int) (random() * 100));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        start = System.currentTimeMillis();
        bigInt.sortMerge();
        finish = System.currentTimeMillis() - start;
        System.out.println("Sort Merge = " + finish +"ms");
    }

}
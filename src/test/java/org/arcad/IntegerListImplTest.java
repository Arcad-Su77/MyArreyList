package org.arcad;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private IntegerListImpl testList;

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
package org.arcad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class StringListImplTest {

    /*
    This class tests the implementation of StringListImpl class
    
    */

    @Test
    void validateIndex() {
        //Testing if the validateIndex method throws IndexOutOfBoundsException when index is greater than size
        StringListImpl testList = new StringListImpl(5);
        assertThrows(IndexOutOfBoundsException.class, () -> testList.validateIndex(6));

        //Testing if the validateIndex method throws IndexOutOfBoundsException when index is negative
        StringListImpl testList2 = new StringListImpl(5);
        assertThrows(IndexOutOfBoundsException.class, () -> testList2.validateIndex(-1));
    }

    @Test
    void validationSize() {
        //Testing if the validationSize method throws IllegalArgumentException when size exceeds the capacity
        StringListImpl testList = new StringListImpl(4);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            for (int i = 0; i < 6; i++) {
                testList.add("Item" + i);
                System.out.println("testList.toString() = " + testList);
            }
        });
    }

    @Test
    void addItem() throws SizeArreOutException {
        //Testing adding items to the list
        StringListImpl testList = new StringListImpl();
        testList.add("Item1");
        assertEquals("Item1", testList.get(0));

        //Testing adding items at a certain index 
        testList.add(0, "Item0");
        assertEquals("Item0", testList.get(0));
        assertEquals("Item1", testList.get(1));
    }

    @Test
    void setItem() throws SizeArreOutException {
        //Testing setting items to the list
        StringListImpl testList = new StringListImpl();
        testList.add("Item1");
        testList.set(0,"Item0");
        assertEquals("Item0", testList.get(0));
    }

    @Test
    void removeItem() throws ItemNotFoundException, SizeArreOutException {
        //Testing removing items from the list by item
        StringListImpl testList = new StringListImpl();
        testList.add("Item1");
        testList.remove("Item1");
        assertTrue(testList.isEmpty());

        //Testing removing items from the list by index 
        StringListImpl testList2 = new StringListImpl();
        testList2.add("Item1");
        testList2.remove(0);
        assertTrue(testList2.isEmpty());
    }

    @Test
    void containsItem() throws SizeArreOutException {
        //Testing if the contains method works correctly
        StringListImpl testList = new StringListImpl();
        testList.add("Item1");
        assertTrue(testList.contains("Item1"));
    }
}
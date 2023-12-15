package org.arcad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntegerListImpl implements IntegerList {

    private Integer[] intList;
    private int size;

    private void validationSize() throws SizeArreOutException {
        if (size == intList.length) {
            throw new SizeArreOutException("Array is full");
        }
    }

    private void validationIndex(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Invalid index");
        }
    }

    private void validateNumber(Integer number) throws Exception {
        if (number == null) {
            throw new Exception("Invalid number: null");
        }
    }

    @Override
    public Integer add(Integer number) throws Exception {
        validationSize();
        validateNumber(number);
        intList[size] = number;
        size++;
        return number;
    }

    @Override
    public Integer add(int index, Integer number) throws Exception, SizeArreOutException {
        validationSize();
        validateNumber(number);
        validationIndex(index);
        for (int i = size; i > index; i--) {
            intList[i] = intList[i - 1];
        }
        intList[index] = number;
        size++;
        return number;
    }

    @Override
    public Integer set(int index, Integer number) throws Exception {
        validationIndex(index);
        validateNumber(number);
        int oldValue = intList[index];
        intList[index] = number;
        return oldValue;
    }

    @Override
    public Integer remove(Integer number) throws Exception, ItemNotFoundException {
        validateNumber(number);
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (intList[i].equals(number)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ItemNotFoundException("Item not found");
        }
        IntStream.range(index, size - 1).forEach(i -> intList[i] = intList[i + 1]);
        size--;
        return number;
    }

    @Override
    public Integer remove(int index) {
        Integer num = intList[index];
        IntStream.range(index + 1, size).forEach(i -> intList[i - 1] = intList[i]);
        return num;
    }

    @Override
    public boolean contains(Integer number) {
        return IntStream.range(0, size).anyMatch(i -> intList[i].equals(number));
    }

    @Override
    public int indexOf(Integer number) {
        return IntStream.range(0, size).filter(i -> intList[i].equals(number)).findFirst().orElse(-1);
    }

    @Override
    public int lastIndexOf(Integer number) {
        return IntStream.range(0, size)
                .filter(i -> intList[i].equals(number))
                .reduce((first, second) -> second)
                .orElse(-1);
    }

    @Override
    public Integer get(int index) throws Exception {
        validationIndex(index);
        return intList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) throws Exception {
        if (otherList == null) {
            throw new Exception("Other list is null");
        }
        return Arrays.equals(intList, otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        intList = new Integer[intList.length];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return intList.clone();
    }
}
package org.arcad;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntegerListImpl implements IntegerList {

    private Integer[] intList;
    private int size;

    public IntegerListImpl() {
        this.intList = new Integer[10];
        this.size = 0;
    }

    public IntegerListImpl(int size) {
        this.size = 0;
        this.intList = new Integer[size];
    }

    public IntegerListImpl(IntegerListImpl IntList) {
        this.intList = Arrays.copyOf(IntList.intList, IntList.intList.length);
        this.size = IntList.size;
    }
    private void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
    private void validationSize() throws SizeArreOutException {
        if (size >= intList.length*0.9 && size < intList.length) grow();
        if (size == intList.length) {
            throw new SizeArreOutException("Массив полный "+size+" : " +intList.length);
        }
    }
    private void validationIndex(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Индекс не верный");
        }
    }

    private void validateNumber(Integer number) throws Exception {
        if (number == null) {
            throw new Exception("Invalid number: null");
        }
    }

    private void grow() {
        intList = Arrays.copyOf(intList, (int) (size * 1.5));
    }

    public void sortBobble() {
        for (int i = 0; i < intList.length - 1; i++) {
            for (int j = 0; j < intList.length - 1 - i; j++) {
                if (intList[j] != null && intList[j+1] != null)
                    if (intList[j] > intList[j + 1]) {
                        swapElements(intList, j, j + 1);
                    }
            }
        }
    }

    public void sortSelection() {
        for (int i = 0; i < size-1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (intList[j] != null)
                    if (intList[j] < intList[minElementIndex]) {
                        minElementIndex = j;
                    }
            }
            swapElements(intList, i, minElementIndex);
        }
    }

    public void sortInsertion() {
        for (int i = 1; i < size; i++) {
            int key = intList[i];
            int j = i - 1;
            while (j >= 0 && intList[j] > key) {
                intList[j + 1] = intList[j];
                j = j - 1;
            }
            intList[j + 1] = key;
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
    public Integer add(int index, Integer number) throws Exception {
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
    public Integer removeNum(Integer number) throws Exception, ItemNotFoundException {
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
    public Integer removeIndex(int index) {
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
        return Arrays.copyOf(intList, size);
    }

    @Override
    public void sortArrays() {
        Arrays.sort(intList, 0, size);
    }

    @Override
    public void sortRecurse() {
        sortRecurseStepOne(intList, 0, size - 1);
    }

    private void sortRecurseStepOne(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int mid = partition(arr, begin, end);
            sortRecurseStepOne(arr, begin, mid-1);
            sortRecurseStepOne(arr, mid + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    @Override
    public void sortMerge() {
        mergeStepOne(intList);
    }

    private void mergeStepOne(Integer[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        Integer[] left = Arrays.copyOfRange(arr, 0, mid);
        Integer[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeStepOne(left);
        mergeStepOne(right);
        mergeStepTwo(arr, left, right);
    }

    private void mergeStepTwo(Integer[] arr, Integer[] left, Integer[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] != null && right[j] != null) {
                if (left[i] <= right[j]) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            } else {
                if (left[i] == null) i++;
                else j++;
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    @Override
    public void reverse() {
        int start = 0;
        int end = size - 1;
        while (start < end) {
            int temp = intList[start];
            intList[start] = intList[end];
            intList[end] = temp;
            start++;
            end--;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(intList);
    }
}
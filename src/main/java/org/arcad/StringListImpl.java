package org.arcad;

import java.util.Arrays;


public class StringListImpl implements StringList {
    private String[] strList;
    private int size;

    public StringListImpl() {
        this.strList = new String[10];
    }

    public StringListImpl(int initSize) {
        this.strList = new String[initSize];
        this.size = strList.length;
    }

    void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index > size() || index < 0)
            throw new IndexOutOfBoundsException("Индекс вне диапазона массива");
    }
    void validationSize() throws ArrayIndexOutOfBoundsException {
        if (size > strList.length) throw new ArrayIndexOutOfBoundsException("Размер списка должен быть положительным числом");
    }
    void validationItem(String item) throws ItemIsNullException {
        if (item.isEmpty()) throw new ItemIsNullException( "Строка в переменной item отсутствует.");
    }

    @Override
    public String add(String item) throws ArrayIndexOutOfBoundsException {
        validationSize();
        validationItem(item);
        strList[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) throws SizeArreOutException {
        validationItem(item);
        validateIndex(index);
        validationSize();
        if (index == size()) strList[size++] = item;
        else {
            System.arraycopy(strList, index, strList, index + 1, size() - index);
            strList[index] = item;
            size++;
        }
        return item;
    }

    @Override
    public String set(int index, String item) {
        validationItem(item);
        validateIndex(index);
        strList[index] = item;
        return item;
    }

    @Override
    public String remove(String item) throws ItemNotFoundException {
        validationItem(item);
        int indexToRemove = indexOf(item);

        if (indexToRemove == -1) throw new ItemNotFoundException("Элемент отсутствует в списке");
        System.arraycopy(strList, indexToRemove + 1, strList, indexToRemove, size() - indexToRemove - 1);
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String remove = strList[index];
        System.arraycopy(strList, index + 1, strList, index, size() - index - 1);
        size--;
        return remove;
    }

    @Override
    public boolean contains(String item) {
        for (String s : strList) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < strList.length; i++) {
            String str = strList[i];
            if (str.equals(item)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = strList.length-1; i >= 0; i--) {
            String str = strList[i];

            if (str.equals(item)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return strList[index];
    }

    @Override
    public boolean equals(StringList otherList) throws Exception {
        if (otherList == null) throw new IllegalArgumentException("Передан null");
        if (this.size() != otherList.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
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
        size = 0;
        strList = new String[size];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(strList, size);
    }
}

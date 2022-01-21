package ru.lesson2;

import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private T [] elements;

    public MyArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.elements = (T[]) new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.elements = (T[]) new Object[capacity];
    }

    private void checkFull() {
        if (size == capacity) {
            capacity = (int) (capacity * 1.5);
            T[] elements = (T[]) new Object[capacity];
            System.arraycopy(this.elements, 0, elements, 0, size);
            this.elements = elements;
        }
    }

    public void add(T value) {
        checkFull();
        elements[size] = value;
        size++;
    }

    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("index");
        }
        checkFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("index");
        }
        return elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("index");
        }
        elements[index] = value;
    }

    public void remove(T value) {
        for(int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
            }
        }
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new IllegalArgumentException("index");
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elements[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index != size - 1;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T element = elements[index];
                    index++;
                    return element;
                } else {
                    return null;
                }
            }
        };
    }
}

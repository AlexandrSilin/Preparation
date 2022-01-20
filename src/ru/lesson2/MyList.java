package ru.lesson2;

public interface MyList<T> extends Iterable<T> {
    void add(T value);

    void add(T value, int index);

    void remove(T value);

    boolean contains(T value);

    void set(T value, int index);

    void remove(int index);

    T get(int index);

    int size();
}

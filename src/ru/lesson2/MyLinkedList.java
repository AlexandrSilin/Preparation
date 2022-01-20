package ru.lesson2;

import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private final Node<T> head;
    private final Node<T> tail;

    public MyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void add(T value) {
        if (value == null) {
            return;
        }
        Node<T> node = new Node<>(value);
        node.next = tail;
        tail.prev = node;
        if (isEmpty()) {
            head.next = node;
            node.prev = head;
        } else {
            Node<T> tmp = head;
            while (tmp.next != tail) {
                System.out.println(tmp);
                tmp = tmp.next;
                System.out.println(tmp);
                System.out.println();
            }
            tmp.next = node;
            node.prev = tmp;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size() - 1 || index < 0) {
            throw new IllegalArgumentException("index");
        }
        if (value == null) {
            throw new IllegalArgumentException("value");
        }
        Node<T> node = new Node<>(value);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.next.prev = node;
        node.next = current.next;
        node.prev = current;
        current.next = node;
    }

    public void addFirst(T value) {
        if (value == null) {
            return;
        }
        if (head.next == null) {
            add(value);
        } else {
            Node<T> node = new Node<>(value);
            Node<T> tmp = head.next;
            head.next = node;
            node.prev = head;
            node.next = tmp;
            tmp.prev = node;
        }
    }

    @Override
    public T get(int index) {
        if (isEmpty() || size() - 1 < index) {
            return null;
        }
        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public T getFirst() {
        return head.next.value;
    }

    public T getLast() {
        return tail.prev.value;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        int size = 0;
        Node<T> tmp = head;
        while (tmp.next != tail) {
            tmp = tmp.next;
            size++;
        }
        return size;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        if (isEmpty() || value == null) {
            return -1;
        }
        Node<T> current = head;
        int index = 0;
        while (current.next != tail) {
            current = current.next;
            if (current.value.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void set(T value, int index) {
        if (isEmpty() || size() - 1 < index || index < 0 || value == null) {
            return;
        }
        Node<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = value;
    }

    @Override
    public void remove(int index) {
        if (isEmpty() || size() - 1 < index || index < 0) {
            return;
        }
        Node<T> tmp = head.next;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
    }

    @Override
    public void remove(T value) {
        remove(indexOf(value));
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next()).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current.next != tail;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    return null;
                }
                current = current.next;
                return current.value;
            }

            @Override
            public void remove() {
                if (current == head || current == tail) {
                    return;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
        };
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
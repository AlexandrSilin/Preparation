package ru.lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int count;
    private final Lock lock;

    public Counter() {
        this.lock = new ReentrantLock();
    }

    public int incrementAndGet() {
        lock.lock();
        count++;
        lock.unlock();
        return count;
    }
}

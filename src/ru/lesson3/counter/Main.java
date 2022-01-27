package ru.lesson3.counter;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(() -> {
            int count = 0;
            while (count < 9) {
                count = counter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " count = " + count);
            }
        }).start();
        new Thread(() -> {
            int count = 0;
            while (count < 9) {
                count = counter.incrementAndGet();
                System.out.println(Thread.currentThread().getName() + " count = " + count);
            }
        }).start();
    }
}

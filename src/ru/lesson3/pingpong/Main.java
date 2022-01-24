package ru.lesson3.pingpong;

public class Main {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong(Message.PING);
        new Thread(() -> {
            int count = 0;
            while (count < 10) {
                pingPong.ping();
                count++;
            }
        }).start();
        new Thread(() -> {
            int count = 0;
            while (count < 10) {
                pingPong.pong();
                count++;
            }
        }).start();
    }
}
package ru.lesson3.pingpong;

public class PingPong {
    private Message message;

    public PingPong(Message message) {
        this.message = message;
    }

    public synchronized void ping() {
        while (!message.equals(Message.PING)) {
            waitWithTryCatch();
        }
        System.out.println(message);
        message = Message.PONG;
        notifyAll();
    }

    public synchronized void pong() {
        while (!message.equals(Message.PONG)) {
            waitWithTryCatch();
        }
        System.out.println(message);
        message = Message.PING;
        notifyAll();
    }

    private synchronized void waitWithTryCatch() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

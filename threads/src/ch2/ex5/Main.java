package ch2.ex5;

public class Main {
    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100, 10);

        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(fileMock, buffer);
        Thread producerThread = new Thread(producer, "Producer");

        Consumer[] consumers = new Consumer[3];
        Thread[] consumersThread = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            consumersThread[i] = new Thread(consumers[i], "Consumer " + i);
        }

        producerThread.start();
        for (Thread thread : consumersThread) {
            thread.start();
        }
    }
}

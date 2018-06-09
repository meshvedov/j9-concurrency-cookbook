package ch3_synchronization.semaphore;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[12];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        Arrays.stream(threads).forEach(Thread::start);
    }
}

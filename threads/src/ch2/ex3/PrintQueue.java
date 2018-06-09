package ch2.ex3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private Lock queueLock;

    public PrintQueue(boolean fairMode) {
        this.queueLock = new ReentrantLock(fairMode);
    }

    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long)(Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + " :1_PrintQueue: Printing a Job during " + (duration/1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

        queueLock.lock();
        try {
            Long duration = (long)(Math.random() * 10_000);
            System.out.println(Thread.currentThread().getName() + ":2_PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}

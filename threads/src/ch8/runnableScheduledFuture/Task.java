package ch8.runnableScheduledFuture;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    @Override
    public void run() {
        System.out.print("Task: Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Task: End.\n");
    }
}

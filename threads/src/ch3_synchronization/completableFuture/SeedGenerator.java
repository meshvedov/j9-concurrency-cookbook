package ch3_synchronization.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SeedGenerator implements Runnable {

    private CompletableFuture<Integer> resultCommunicator;

    public SeedGenerator(CompletableFuture<Integer> completableFuture) {
        this.resultCommunicator = completableFuture;
    }

    @Override
    public void run() {
        System.out.printf("Seed Generator: Generating seed...\n");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int seed = (int) Math.rint(Math.random() * 10);
        System.out.printf("Seed Generator: Seed generated: %d\n", seed);
        resultCommunicator.complete(seed);
    }
}

package ch8.custom_recursiveTask_in_forkJoin;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[10_000];
        ForkJoinPool pool = new ForkJoinPool();
        Task task = new Task("Task", array, 0, array.length);
        pool.invoke(task);
        pool.shutdown();
        System.out.printf("Main: End of the program.\n");
    }
}

package ch1.ex3;

public class Main {
    public static void main(String[] args) {
        FileSearch search = new FileSearch("/home/mic/BooksCode/concurrency_cookbook/threads/data", "log.txt");
        Thread thread = new Thread(search);
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

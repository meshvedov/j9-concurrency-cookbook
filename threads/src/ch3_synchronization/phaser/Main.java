package ch3_synchronization.phaser;

import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("/home/mic/BooksCode", "log", phaser);
        FileSearch apps = new FileSearch("/var/log", "log", phaser);
        FileSearch documents = new FileSearch("/var/log", "log", phaser);

        Thread systemThread = new Thread(system, "SYSTEM");
        systemThread.start();
        Thread appsThread = new Thread(apps, "APPS");
        appsThread.start();
        Thread documentsThread = new Thread(documents, "DOCUMENTS");
        documentsThread.start();

        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminated: " + phaser.isTerminated());
    }
}

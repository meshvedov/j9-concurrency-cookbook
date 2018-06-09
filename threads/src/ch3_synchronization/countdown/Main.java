package ch3_synchronization.countdown;

public class Main {

    public static void main(String[] args) {
        Videoconference videoconference = new Videoconference(10);
        Thread thread = new Thread(videoconference);
        thread.start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Participant(videoconference, "Participant " + i)).start();
        }
    }
}

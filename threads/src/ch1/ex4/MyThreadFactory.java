package ch1.ex4;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private int counter;
    private String name;
    private List<String> stats;

    MyThreadFactory(String name) {
        this.name = name;
        counter = 0;
        stats = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread-" + counter);
        counter++;
        stats.add(String.format("Created thread with ID: %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
        return t;
    }

    String getStats() {

        StringBuffer buffer = new StringBuffer();

        for (String stat : stats) {
            buffer.append(stat);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}

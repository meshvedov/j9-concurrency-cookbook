package ch8.custom_recursiveTask_in_forkJoin;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

public abstract class MyWokerTask extends ForkJoinTask<Void> {
    private String name;

    public MyWokerTask(String name) {
        this.name = name;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s: %d milliseconds to complete.\n", name, diff);
        return true;
    }

    public String getName() {
        return name;
    }

    protected abstract void compute();
}

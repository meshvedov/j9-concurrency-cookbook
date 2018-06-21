package ch8.custom_thread_pool_executor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyExecutor extends ThreadPoolExecutor {

    private final ConcurrentHashMap<Runnable, Date> startTime;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueu) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueu);
        startTime = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.printf("MyExecutor: Going to shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("MyExecutor: Going to immediately shutdown.\n");
        System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor: A task is beginning: %s : hash-> %s\n", t.getName(), r.hashCode());
        startTime.put(r, new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;

        try {
            System.out.print("***************************\n");
            System.out.printf("MyExecutor: A task is finishing. hash-> %s\n", r.hashCode());
            System.out.printf("MyExecutor: Result: %s\n", result.get());
            Date startDate = startTime.remove(r);
            Date finishDate = new Date();
            long diff = finishDate. getTime() - startDate.getTime();
            System.out.printf("MyExecutor: Duration: %d\n", diff);
            System.out.print("***************************\n");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

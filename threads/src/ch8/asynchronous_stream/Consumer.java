package ch8.asynchronous_stream;

import java.util.concurrent.Flow;

public class Consumer implements Flow.Subscriber<News> {

    private Flow.Subscription subscription;
    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
        System.out.printf("%s: Consumer - Subscription\n", Thread.currentThread().getName());
    }

    @Override
    public void onNext(News item) {
        System.out.printf("%s - %s: Consumer - News\n", name, Thread.currentThread().getName());
        System.out.printf("%s - %s: Title: %s\n", name, Thread.currentThread().getName(), item.getTitle());
        System.out.printf("%s - %s: Content: %s\n", name, Thread.currentThread().getName(), item.getContent());
        System.out.printf("%s - %s: Date: %s\n", name, Thread.currentThread().getName(), item.getDate());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("%s - %s: Consumer - Error: %s\n", name, Thread.currentThread().getName(), throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.printf("%s - %s: Consumer - Completed\n", name, Thread.currentThread().getName());
    }
}

package ch6_stream.reactive;

import java.util.concurrent.Flow;

public class Consumer2 implements Flow.Subscriber<Item> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("%s: Consumer2: Subscription received\n", Thread.currentThread().getName());
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Item item) {
        System.out.printf("%s: Consumer2: Item received\n", Thread.currentThread().getName());
        System.out.printf("%s: Consumer2: %s\n", Thread.currentThread().getName(), item.getTitle());
        System.out.printf("%s: Consumer2: %s\n", Thread.currentThread().getName(), item.getContent());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("%s: Consumer2: Error\n", Thread.currentThread().getName());
        throwable.printStackTrace(System.err);
    }

    @Override
    public void onComplete() {
        System.out.printf("%s: Consumer2: Completed\n", Thread.currentThread().getName());
    }
}

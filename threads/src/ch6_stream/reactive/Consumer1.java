package ch6_stream.reactive;

import java.util.concurrent.Flow;

public class Consumer1 implements Flow.Subscriber<Item> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("%s: Consumer1: Subscription received\n", Thread.currentThread().getName());
        System.out.printf("%s: Consumer1: No Item requested\n", Thread.currentThread().getName());
    }

    @Override
    public void onNext(Item item) {
        System.out.printf("%s: Consumer1: Item received\n", Thread.currentThread().getName());
        System.out.printf("%s: Consumer1: %s\n", Thread.currentThread().getName(), item.getTitle());
        System.out.printf("%s: Consumer1: %s\n", Thread.currentThread().getName(), item.getContent());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("%s: Consumer1: Error\n", Thread.currentThread().getName());
        throwable.printStackTrace(System.err);
    }

    @Override
    public void onComplete() {
        System.out.printf("%s: Consumer1: Completed\n", Thread.currentThread().getName());
    }
}

package ch6_stream.reactive;

import java.util.concurrent.Flow;

public class Consumer3 implements Flow.Subscriber<Item> {

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("%s: Consumer3: Error\n", Thread.currentThread().getName());
        throwable.printStackTrace(System.err);
    }

    @Override
    public void onComplete() {
        System.out.printf("%s: Consumer3: Completed\n", Thread.currentThread().getName());
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("%s: Consumer 3: Subscription received\n",
                Thread.currentThread().getName());
        System.out.printf("%s: Consumer 3: Requested three items\n",
                Thread.currentThread().getName());
        subscription.request(3);
    }

    @Override
    public void onNext(Item item) {
        System.out.printf("onNext -> %s: Consumer3: Item received\n", Thread.currentThread().getName());
        System.out.printf("%s: Consumer3: %s\n", Thread.currentThread().getName(), item.getTitle());
        System.out.printf("%s: Consumer3: %s <- onNext\n", Thread.currentThread().getName(), item.getContent());
    }
}

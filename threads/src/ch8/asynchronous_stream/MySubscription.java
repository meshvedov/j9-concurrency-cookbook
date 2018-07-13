package ch8.asynchronous_stream;

import java.util.concurrent.Flow;

public class MySubscription implements Flow.Subscription {
    private boolean canceled = false;
    private long requested = 0;

    @Override
    public void request(long n) {
        requested += n;
    }

    @Override
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public long getRequested() {
        return requested;
    }

    public void decreaseRequested() {
        requested--;
    }
}

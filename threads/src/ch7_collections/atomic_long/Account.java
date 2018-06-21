package ch7_collections.atomic_long;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Account {
    private final AtomicLong balance;
    private final LongAdder operations;
    private final DoubleAccumulator commission;

    public AtomicLong getBalance() {
        return balance;
    }

    public LongAdder getOperations() {
        return operations;
    }

    public DoubleAccumulator getCommission() {
        return commission;
    }

    public Account() {
        balance = new AtomicLong();
        operations = new LongAdder();
        commission = new DoubleAccumulator((x, y) -> x + y * 0.2, 0);
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
        operations.reset();
        commission.reset();
    }

    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
        this.operations.increment();
        this.commission.accumulate(amount);
    }

    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
        this.operations.increment();
        this.commission.accumulate(amount);
    }
}

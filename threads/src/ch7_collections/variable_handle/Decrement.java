package ch7_collections.variable_handle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class Decrement implements Runnable {
    private Account account;

    public Decrement(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        VarHandle handler;

        try {
            handler = MethodHandles.lookup().in(Account.class).findVarHandle(Account.class, "amount", double.class);
            for (int i = 0; i < 10000; i++) {
                handler.getAndAdd(account, -100);
                account.unsafeAmount -= 100;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

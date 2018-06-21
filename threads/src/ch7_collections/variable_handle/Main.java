package ch7_collections.variable_handle;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        Thread threadIncrement = new Thread(new Incrementer(account));
        Thread threadDecrement = new Thread(new Decrement(account));
        threadIncrement.start();
        threadDecrement.start();

        try {
            threadIncrement.join();
            threadDecrement.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Safe amount: %f\n", account.amount);
        System.out.printf("Unsafe amount: %f\n", account.unsafeAmount);
    }
}

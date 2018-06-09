package ch2.ex4;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reader implements Runnable{

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.printf("%s : %s: Price 1: %f\n", LocalTime.now(), Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s : %s: Price 2: %f\n", LocalTime.now(), Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}

package concurrencyexampletutorial;

import java.util.concurrent.CountDownLatch;

public class Service implements Runnable {
    private final String name;
    private final int timeToStart;
    private final CountDownLatch latch;

    public Service(String name, int timeToStart, CountDownLatch latch) {
        this.name = name;
        this.timeToStart = timeToStart;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeToStart);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is Up");
        latch.countDown(); // CountDownLatch를 1씩 감소시킴.
    }
}

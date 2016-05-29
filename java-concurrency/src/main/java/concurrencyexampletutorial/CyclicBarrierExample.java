package concurrencyexampletutorial;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static class Task implements Runnable {

        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                final int sleepMillis = new Random().nextInt(10000);
                System.out.println(Thread.currentThread().getName() + " is going to sleep for " + sleepMillis);

                Thread.sleep(sleepMillis);
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");

                barrier.await(); // 모든 쓰레드가 barrier.await()을 호출할 때까지 기다림

                // 모든 쓰레드가 barrier.await()을 호출하면 그 때 처리 계속 진행
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier");

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - All parties are arrived at barrier, let's play!");
            }
        });


        Thread t1 = new Thread(new Task(cb), "Thread 1");
        Thread t2 = new Thread(new Task(cb), "Thread 2");
        Thread t3 = new Thread(new Task(cb), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

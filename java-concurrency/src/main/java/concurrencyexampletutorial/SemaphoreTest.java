package concurrencyexampletutorial;

import java.util.concurrent.Semaphore;

/**
 * <a href="http://javarevisited.blogspot.kr/2012/05/counting-semaphore-example-in-java-5.html">Counting Semaphore Example in Java 5</a>
 */
public class SemaphoreTest {
    Semaphore binary = new Semaphore(1);

    public static void main(String[] args) {
        final SemaphoreTest test = new SemaphoreTest();

        new Thread() {
            @Override
            public void run() {
                test.mutualExclusion();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.mutualExclusion();
            }
        }.start();
    }

    private void mutualExclusion() {
        try {
            binary.acquire();

            // mutual exclusive region
            System.out.println((Thread.currentThread().getName() + " inside mutual exclusive region"));
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
             binary.release();
            System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
        }
    }
}

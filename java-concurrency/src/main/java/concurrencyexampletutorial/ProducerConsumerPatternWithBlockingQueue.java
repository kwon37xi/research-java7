package concurrencyexampletutorial;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <a href="http://javarevisited.blogspot.kr/2012/02/producer-consumer-design-pattern-with.html">Producer/Consumer with BlockingQueue</a>
 */
public class ProducerConsumerPatternWithBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();

        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        prodThread.start();
        consThread.start();
    }
}

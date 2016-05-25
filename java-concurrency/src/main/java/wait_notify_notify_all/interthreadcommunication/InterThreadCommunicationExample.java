package wait_notify_notify_all.interthreadcommunication;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="http://javarevisited.blogspot.kr/2013/12/inter-thread-communication-in-java-wait-notify-example.html">Inter Thread Communication in Java using Wait Notify Example</a>
 */
public class InterThreadCommunicationExample {
    public static void main(String[] args) {
        final Queue<Integer> sharedQueue = new LinkedList<>();

        Thread producer = new Producer(sharedQueue);
        Thread consumer = new Consumer(sharedQueue);

        producer.start();
        consumer.start();
    }
}

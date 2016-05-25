package wait_notify_notify_all.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="http://javarevisited.blogspot.kr/2015/07/how-to-use-wait-notify-and-notifyall-in.html">How to use wait, notify and notifyAll in Java - Producer Consumer Example</a>
 */
public class ProducerConsumerInJava {
    public static void main(String[] args) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumer Problem");

        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;

        new Producer(buffer, maxSize, "PRODUCER").start();
        new Consumer(buffer, maxSize, "CONSUMER 1").start();
        new Consumer(buffer, maxSize, "CONSUMER 2").start();

    }
}

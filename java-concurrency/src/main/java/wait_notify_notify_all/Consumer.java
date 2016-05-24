package wait_notify_notify_all;

import java.util.Queue;

public class Consumer extends Thread {
    private final Queue<Integer> sharedQueue;

    public Consumer(Queue<Integer> sharedQueue) {
        super("Consumer");
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQueue) {
                while (sharedQueue.size() == 0) { // while Loop 로 조건을 검사하라.
                    try {
                        System.out.println("Queue is empty, waiting");
                        sharedQueue.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                int number = sharedQueue.poll();
                System.out.println("Consuming : " + number);
                sharedQueue.notify();

                // 탈출 조건
                if (number == 3) {
                    break;
                }
            }
        }
    }
}

package wait_notify_notify_all.interthreadcommunication;

import java.util.Queue;

public class Producer extends Thread {
    private final Queue<Integer> sharedQueue;

    public Producer(Queue<Integer> sharedQueue) {
        super("Producer");
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            synchronized (sharedQueue) {
                while (sharedQueue.size() >= 1) { // while Loop로 조건을 검사하라
                    try {
                        System.out.println("Queue is full, waiting");
                        sharedQueue.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("Producing : " + i);
                sharedQueue.add(i);
                sharedQueue.notify();
            }
        }
    }
}

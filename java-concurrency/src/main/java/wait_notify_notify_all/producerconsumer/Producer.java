package wait_notify_notify_all.producerconsumer;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> buffer, int maxSize, String name) {
        super(name);
        this.queue = buffer;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                // waiting 조건 체크
                while (queue.size() == maxSize) {
                    // 조건을 만족하면 계속 wait 한다.
                    try {
                        System.out.println("Queue is full, "
                            + "Producer thread waiting for "
                            + "consumer to take something from queue.");
                        queue.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Producing value : " + i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}

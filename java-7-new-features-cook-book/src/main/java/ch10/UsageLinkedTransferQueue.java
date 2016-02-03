package ch10;

import java.util.concurrent.LinkedTransferQueue;


/**
 * {@link java.util.concurrent.LinkedTransferQueue}는 경계가 없는 FIFO 방식의 Queue. 원소를 가져오는 메소드를
 * 블록킹 방식과 논블록킹 방식 둘 다 제공하며 멀티스레드 환경에서 큐에 동시에 접근하는 상황에 사용하기 적합.
 */
public class UsageLinkedTransferQueue {
    private static LinkedTransferQueue<Item> linkeTransQ = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        new Thread(new ItemProducer()).start();
        new Thread(new ItemConsumer()).start();
    }

    private static class Item {
        public final String description;
        public final int itemId;

        public Item() {
            this("Default Item", 0);
        }

        public Item(String description, int itemId) {
            this.description = description;
            this.itemId = itemId;
        }
    }

    static class ItemProducer implements Runnable {
        @Override
        public void run() {
            try {
                for (int x = 1; x < 8; x++) {
                    String itemName = "Item" + x;
                    int itemId = x;
                    linkeTransQ.offer(new Item(itemName, itemId));
                    System.out.println("New Item Added: " + itemName + " " + itemId);
                    Thread.currentThread().sleep(250);

                    if (linkeTransQ.hasWaitingConsumer()) {
                        System.out.println("Hurry up!");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ItemConsumer implements Runnable {
        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    generateOrder(linkeTransQ.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void generateOrder(Item item) {
            System.out.println("Part Order");
            System.out.println("Item description: " + item.description);
            System.out.println("Item ID #" + item.itemId);
            System.out.println();
        }
    }
}

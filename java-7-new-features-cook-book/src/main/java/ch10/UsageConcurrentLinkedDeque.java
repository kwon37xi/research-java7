package ch10;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RunnableFuture;

/**
 * {@link java.util.concurrent.ConcurrentLinkedDeque}는 여러 쓰레드가 특정한 데이터에
 * 동시에 접근할 때 안전하게 처리.
 * double-ended queue를 사용하여 큐의 양쪽에 원소를 추가하거나 삭제할 수 있다.
 * head-tail linked list라고도 부른다.
 * null 원소 사용불가.
 */
public class UsageConcurrentLinkedDeque {
    private static ConcurrentLinkedDeque<Item> deque = new ConcurrentLinkedDeque<>();

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
                    deque.add(new Item(itemName, itemId));
                    System.out.println("New Item Added: " + itemName + " " + itemId);
                    Thread.currentThread().sleep(250);
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

            Item item;
            while ((item = deque.pollFirst()) != null) {
                generateOrder(item);
            }
        }

        private void generateOrder(Item item) {
            System.out.println("Part Order");
            System.out.println("Item description: " + item.description);
            System.out.println("Item ID #" + item.itemId);
            System.out.println();

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

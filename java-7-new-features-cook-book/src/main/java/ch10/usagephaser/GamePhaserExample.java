package ch10.usagephaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link java.util.concurrent.Phaser}는 단계별로 순환하는 방식으로 상호 작용하는 쓰레드에 대한 동기화 제공.
 * {@link java.util.concurrent.CountDownLatch}와 {@link java.util.concurrent.CyclicBarrier}의 기능을 혼합하여
 * 동시성에 대한 추상화를 제공하고, 쓰레드의 수도 동적으로 조절할 수 있다.
 */
public class GamePhaserExample {
    public static abstract class Entity implements Runnable {
        public abstract void run();
    }

    public static class Player extends Entity {
        private final static AtomicInteger ID_SOURCE = new AtomicInteger();

        private final int id = ID_SOURCE.incrementAndGet();

        @Override
        public void run() {
            System.out.println(toString() + " started");
            try {
                Thread.currentThread().sleep(ThreadLocalRandom.current().nextInt(200, 600));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(toString() + " stopped");
        }

        @Override
        public String toString() {
            return "Player #" + id;
        }
    }

    public static class Zombie extends Entity {
        private final static AtomicInteger ID_SOURCE = new AtomicInteger();
        private final int id = ID_SOURCE.incrementAndGet();

        @Override
        public void run() {
            System.out.println(toString() + " started");
            try {
                Thread.currentThread().sleep(ThreadLocalRandom.current().nextInt(400, 800));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(toString() + " stopped");
        }

        @Override
        public String toString() {
            return "Zombie #" + id;
        }
    }

    public static void main(String[] args) {
        new GamePhaserExample().execute();
    }

    private void execute() {
        List<Entity> entities = new ArrayList<>();
        entities.add(new Player());
        entities.add(new Zombie());
        entities.add(new Zombie());
        entities.add(new Zombie());

        gameEntine(entities);

    }

    private void gameEntine(final List<Entity> entities) {
        // Phaser의 인자를 0으로 하면 register()한 뒤에 arriveAndAwaitAdvance() 호출시 이미 모든 파티원이 register() 상태가 되기 때문에 기다리지 않고 실행되버린다.
        final Phaser phaser = new Phaser(1);

        for (final Entity entity : entities) {
            final String member = entity.toString();
            System.out.println(member + " joined the game");
            phaser.register(); // 새로운 unarrived party 등록. 이미 맨우에 1개가 등록돼 있기 때문에 최초 이메소드 호출시 2개가 등록됨.

            new Thread() {
                @Override
                public void run() {
                    System.out.println(member + " waiting for the remaining participants");

                    // 자기 쓰레드를 arrive시키고 register 갯수만큼의 다른 모두가 arrive할 때까지 기다린다.
                    // 매번 실제갯수보다 1개씩 적게 등록되므로 arriveAndDeregister()가 호출될때까지 계속 기다린다.
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(member + " starting run");
                    entity.run();
                }
            }.start();

        }
        phaser.arriveAndDeregister(); // 모두 arrive했고,
        System.out.println("Phaser continuing");
    }
}

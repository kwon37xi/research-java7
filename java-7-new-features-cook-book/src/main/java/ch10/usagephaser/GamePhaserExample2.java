package ch10.usagephaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link Phaser}는 단계별로 순환하는 방식으로 상호 작용하는 쓰레드에 대한 동기화 제공.
 * {@link java.util.concurrent.CountDownLatch}와 {@link java.util.concurrent.CyclicBarrier}의 기능을 혼합하여
 * 동시성에 대한 추상화를 제공하고, 쓰레드의 수도 동적으로 조절할 수 있다.
 */
public class GamePhaserExample2 {
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
        new GamePhaserExample2().execute();
    }

    private void execute() {
        List<Entity> entities = new ArrayList<>();
        entities.add(new Player());
        entities.add(new Zombie());
        gameEntine(entities);
    }

    private void gameEntine(final List<Entity> entities) {
        final int iterations = 3;

        // Phaser의 인자를 0으로 하면 register()한 뒤에 arriveAndAwaitAdvance() 호출시 이미 모든 파티원이 register() 상태가 되기 때문에 기다리지 않고 실행되버린다.
        final Phaser phaser = new Phaser(1) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                // phase는 0 base. 현재 단계 번호를 뜻함.
                // registeredParties : 현재 참여자의 숫자
                System.out.println("Phase number " + phase + " [" + registeredParties + "] completed\n");
                return phase >= iterations - 1 || registeredParties == 0;
                // return 값이 true이면 phaser를 종료시킨다.
            }
        };

        for (final Entity entity : entities) {
            final String member = entity.toString();
            System.out.println(member + " joined the game");
            phaser.register(); // 새로운 unarrived party 등록. 이미 맨우에 1개가 등록돼 있기 때문에 최초 이메소드 호출시 2개가 등록됨.

            new Thread() {
                @Override
                public void run() {
                    do {
                        System.out.println(member + " starting run");
                        entity.run();
                        System.out.println(member + " waiting for the remaining participants during phase " + phaser.getPhase());
                        phaser.arriveAndAwaitAdvance();
                    } while (!phaser.isTerminated());
                }
            }.start();

        }
        while (!phaser.isTerminated()) {
            // 각 Phase가 초기값이 1이라서 총 3개의 register가 필요한데 위에서는 Player #1, Zombie #1 로 2개만
            // register된다. 나머지 한개의 party를 여기서 register해서 3개를 맞춰주어 쓰레드 코드가 advance되게(실행되게)한다.
            phaser.arriveAndAwaitAdvance();

            // 여기서 phaser.arriveAndDeregister() 를 호출하면 onAdvance의 registeredParties 인자가 0이되어 한번의 Phase만 실행되고 종료돼 버림.
        }
        System.out.println("Phaser continuing");
    }
}

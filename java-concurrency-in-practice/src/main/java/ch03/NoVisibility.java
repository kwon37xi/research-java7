package ch03;

// Bad Code - 따라하지 말 것
/*
ReaderThread는 number를 영영 못 읽을 수도 있다.
혹은 ready 변수의 값을 먼저 읽고 number를 나중에 읽을 수도 있다. - reordering 현상 특정 메소드의 소스코드가 100% 코딩된 순서로 동작함을 보장하지 않음.
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }


    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}

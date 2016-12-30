package ch02;

/*

재진입성 : 쓰레드 단위로 락을 얻는다.
x 쓰레드가 a 객체의 lock을 가진상태에서 다시 x 쓰레드에서 a 객체의 lock을 요청할 수 있다.
이게 허용되지 않으면 객체 지향 프로그래밍에서의 lock 코드 작성이 어렵다.
 */
public class ReentrantLockExplained {

    public static class Widget {
        public synchronized void doSomething() { // 2.super.doSomething()에 의해 호출되면서 다시한던 this lock 확보
            System.out.println("in Widget");
        }
    }

    public static class LoggingWidget extends Widget {
        public synchronized void doSomething() { // 1. 여기서 this lock 확보
            System.out.println("LoggingWidget calling super.doSometing()");
            super.doSomething(); // Reentrant 하지 않으면 이 호출에서 동일 객체에 lock을 요청하기 때문에 dead lock이 걸리게 됨.
        }
    }

    public static void main(String[] args) {

        // 재진입성 Reentrant 때문에 아래코드 잘 작동함.
        LoggingWidget lw = new LoggingWidget();
        lw.doSomething();
    }
}

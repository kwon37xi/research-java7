package wait_notify_notify_all.notify_notifyall_difference;

/**
 * <a href="http://javarevisited.blogspot.kr/2012/10/difference-between-notify-and-notifyall-java-example.html">Difference between notify and notifyAll in Java - When and How to use</a>
 */
public class NotificationTest {
    private volatile boolean go = false;

    public static void main(String[] args) throws Exception {
        final NotificationTest test = new NotificationTest();

        Runnable waitTask = new Runnable() {

            @Override
            public void run() {
                try {
                    test.shouldGo();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(Thread.currentThread() + " finished Execution.");
            }
        };

        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread() + " finished Execution.");
            }
        };

        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask, "NT1");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(200);

        t4.start();

    }

    private synchronized void shouldGo() throws InterruptedException {
        while (go != true) {
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            wait();
            System.out.println(Thread.currentThread() + " is woke up");
        }

        go = false;
    }

    private synchronized void go() {
        while (go == false) {
            System.out.println(Thread.currentThread() + " is going to notify all or one thread waiting on this object");
            go = true;
            notify();
//            notifyAll();
        }
    }

}

/*

# notifyAll() 실행결과 - 모두 다 깨어나지만 하나만 Lock을 잡고 실행됨

Thread[WT1,5,main] is going to wait on this object
Thread[WT2,5,main] is going to wait on this object
Thread[WT3,5,main] is going to wait on this object
Thread[NT1,5,main] is going to notify all or one thread waiting on this object # notifyAll() 호출
Thread[WT3,5,main] is woke up # 깨어남
Thread[WT2,5,main] is woke up # 깨어남
Thread[WT3,5,main] finished Execution. # WT3 이 Lock을 잡고 코드를 실행하고 빠져나감
Thread[WT2,5,main] is going to wait on this object # Lock 을 못잡고 다시 while문 안으로
Thread[WT1,5,main] is woke up # 깨어남
Thread[WT1,5,main] is going to wait on this object # Lock 을 못잡고 다시 while문 안으로
Thread[NT1,5,main] finished Execution. # notify 호출자측은 종료함.

# notify() - 하나만 깨어나고 Lock을 잡고 실행됨
Thread[WT1,5,main] is going to wait on this object
Thread[WT3,5,main] is going to wait on this object
Thread[WT2,5,main] is going to wait on this object
Thread[NT1,5,main] is going to notify all or one thread waiting on this object # notify() 호출
Thread[WT1,5,main] is woke up # WT1만 깨어남
Thread[NT1,5,main] finished Execution. # Producer 실행 후 종료
Thread[WT1,5,main] finished Execution. # WT1 Consumer 실행 후 종료

 */

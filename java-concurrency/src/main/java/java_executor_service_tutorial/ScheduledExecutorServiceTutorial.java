package java_executor_service_tutorial;

import java.util.Date;
import java.util.concurrent.*;

/**
 * ScheduledExecutorService는 일정 시간의 지정된 지연시간 마다 혹은 주기적으로 태스크를 실행한다.
 */
public class ScheduledExecutorServiceTutorial {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // SingleThreadSecheduleExecutor를 사용하면 스케줄이 여러개라도 하나의 쓰레드에서 스케줄링한다.
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // 스케줄 상황에 따라 여러 쓰레드를 할당한다.
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);

        Callable<String> callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                final int callableCode = (int) (Math.random() * 100);
                System.out.println(Thread.currentThread().getName() + " Starting callable code " + callableCode + " : " + new Date());
                TimeUnit.MILLISECONDS.sleep(3000);
                return "Task's execution. " + callableCode;
            }
        };

        // schedule은 지정된 지연시간 이후에 태스크 실행
        final ScheduledFuture<String> resultFuture = executorService.schedule(callableTask, 3, TimeUnit.SECONDS);
        System.out.println("-- just after schdule() : " + new Date());
        System.out.println("schedule result : " + resultFuture.get());

        // scheduleAtFixedRate는 지정된 초기 지연시간 이후 태스크를 실행하고 매 지정된 시간이 흐른뒤에 지속적으로
        // 태스크를 실행한다.
        // 만약 앞서 실행된 태스크가 매 지정된 시간이 지나도 끝이 안나면 끝날때까지 기다렸다가 실행한다.
        // Runnable 태스크만 받는다.
        // == 태스크 시작시간 기준 delay ==
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " $$ Running with scheduleAtFixedRate " + new Date());
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " $$ scheduleAtFixedRate ended" + new Date());
            }
        }, 100, 1000, TimeUnit.MILLISECONDS);

        // 앞선 태스크의 종료된 뒤부터 Delay시간만큼 쉰 뒤에 태스크를 실행하려면 scheduleWithFixedDelay를 사용한다.
        // == 태스크 종료시간 기준 delay ==
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ## Running with scheduleWithFixedDelay " + new Date());
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ## scheduleWithFixedDelay ended " + new Date());
            }
        }, 100, 1000, TimeUnit.MILLISECONDS);

        // 반복 스케줄을 ExecutorService가 종료되거나 태스크에서 예외가 발생하며 끝난다.
        TimeUnit.SECONDS.sleep(30);
        // shtudown
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
        }
    }
}

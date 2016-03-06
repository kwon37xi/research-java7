package java_executor_service_tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTutorial {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 이미존재하는 Executor
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 직접 Executor 생성도가능. newSingleThreadExecutor() 와 거의 같은 역할.
//        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Runnling RunnableTask");
                    TimeUnit.MILLISECONDS.sleep(3000);
                    System.out.println("End Runnling RunnableTask");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Callable<String> callableTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                final int callableCode = (int) (Math.random() * 100);
                System.out.println("Starting callable code " + callableCode);
                TimeUnit.MILLISECONDS.sleep(3000);
                return "Task's execution. " + callableCode;
            }
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        // Executor.execute() for void
        executorService.execute(runnableTask);

        // Executor.submit() submits a Callable or a Runnable task to an ExecutorService and returns a invokeAnyResult of type Future.
        // Fugure.get()은 블로킹 메스드이다. Callable일 때는 리턴값 혹은  Runnable일 경우에는 null을 리턴한다.
        // 이를 호출하면 모든 해당 쓰레드가 실행이 완료될 때까지 블록킹된다.

        Future<String> future = executorService.submit(callableTask);
        try {
//            System.out.println("Single Callable result : " + future.get());
            // 너무 오래블록킹될 가능성이 있고, 별로 중요한게 아니라면 타임아웃을 줄것
            System.out.println("Single Callable isDone before get : " + future.isDone()); // 종료됐나 확인.
            System.out.println("Single Callable result : " + future.get(3100, TimeUnit.MILLISECONDS));
            // 200 ms 보다 오래 걸리면 TimeoutException 발생한다.
            System.out.println("Single Callable isDone after get : " + future.isDone()); // 종료됐나 확인.
            // cancel(true)와 isCancelled()로 태스크 취소 가능.
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            ex.printStackTrace();
        }

        // invokeAny() assigns a collection of tasks to an ExecutorService, causing each to be executed, and returns the invokeAnyResult
        // of a successful execution of one task(if there was a successful execution).
        String invokeAnyResult = executorService.invokeAny(callableTasks); // 여러개가 실행되지만 결과는 하나만 받는다.
        System.out.println("invokeAny invokeAnyResult : " + invokeAnyResult);

        // invokeAll() assigns a collection of tasks to an ExecutorService, causing each to be executing, and
        // returns the result of all task executions in the form of a list of objects of type Future.
        List<Future<String>> futuresOfInvokeAll = executorService.invokeAll(callableTasks);
        for (Future<String> callResult : futuresOfInvokeAll) {
            System.out.println("invokeAll result : " + callResult.get());
        }

        // 보통 ExecutorService 처리할 태스크가 없어도 자동으로 파괴되지 않는다. 계속 살아서 새로운 태스크를 기다린다.
        // 일반적으로는 좋은 것이지만, 애플리케이션을 종료할 때 계속 wating 상태에 빠지는 문제가 있다.

        // shutdown() : 새로운 태스크 받기를 멈추가 모든 실행 쓰레드가 일을 끝내면 그 때 shutdown 한다.

        // shutdownNow() : 즉시 ExecutorService를 종료시키려고 시도한다. 하지만 모든 실행중인 쓰레드가 종료됨을 보장하지 않는다.
        // 처리를 기다리는 태스크의 리스트를 리턴한다.
        // List<Runnable> notExecutedTasks = executorService.shutDownNow();

        // ExecutorSerivce 종료 권장 패턴
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

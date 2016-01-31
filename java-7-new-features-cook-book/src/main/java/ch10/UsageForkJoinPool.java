package ch10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class UsageForkJoinPool {
    private static int[] numbers = new int[100000000];

    private static class SumOfSquareTask extends RecursiveTask<Long> {
        private final int THRESHOLD = 50000000;
        private int from;
        private int to;

        public SumOfSquareTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            long sum = 0L;
            int mid = (to + from) >>> 1;
            if ((to - from) < THRESHOLD) {
                for (int i = from; i < to; i++) {
                    sum += numbers[i] * numbers[i];
                }
                return sum;
            } else {
                List<RecursiveTask<Long>> forks = new ArrayList<>();
                SumOfSquareTask task1 = new SumOfSquareTask(from, mid);
                SumOfSquareTask task2 = new SumOfSquareTask(mid, to);
                forks.add(task1);
                task1.fork();
                forks.add(task2);
                task2.fork();

                for (RecursiveTask<Long> task : forks) {
                    sum += task.join();
                }
                return sum;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        long startTime;
        long stopTime;

        long sum = 0L;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i] * numbers[i];
        }
        System.out.println("Sum of squares: " + sum);
        stopTime = System.currentTimeMillis();
        System.out.println("Iterative solution time: " + (stopTime - startTime));

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        long result = forkJoinPool.invoke(new SumOfSquareTask(0, numbers.length));
        System.out.println("forkJoinPool: " + forkJoinPool.toString());
        stopTime = System.currentTimeMillis();
        System.out.println("Sum of squares: " + result);
        System.out.println("Fork/join solution time: " + (stopTime - startTime));
    }
}

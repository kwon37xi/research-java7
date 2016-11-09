package read_write_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int threadCount = 2;
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final ScoreBoard scoreBoard = new ScoreBoard();

        executorService.execute(new ScoreUpdateThread(scoreBoard));
        executorService.execute(new ScoreHealthThread(scoreBoard));
        executorService.shutdown();
    }
}

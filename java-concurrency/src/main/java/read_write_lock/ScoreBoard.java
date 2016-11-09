package read_write_lock;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ScoreBoard {
    private boolean scoreUpdated = false;
    private int score = 0;
    String health = "Not available";
    final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    public String getMatchHealth() {
        rrwl.readLock().lock();

        if (scoreUpdated) {
            rrwl.readLock().unlock();
            rrwl.writeLock().lock();
            try {
                if (scoreUpdated) {
                    score = fetchScore();
                    scoreUpdated = false;
                }
                rrwl.readLock().lock(); // lock downgrading
            } finally {
                rrwl.writeLock().unlock();
            }
        }
        try {
            if (score % 2 == 0) {
                health = "Bad score";
            } else {
                health = "Good score";
            }
        } finally {
            rrwl.readLock().unlock();
        }
        return health;
    }

    public void updateScore() {
        try {
            rrwl.writeLock().lock();
            scoreUpdated = true;
        } finally {
            rrwl.writeLock().unlock();
        }
    }

    private int fetchScore() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MILLISECOND);
    }

}

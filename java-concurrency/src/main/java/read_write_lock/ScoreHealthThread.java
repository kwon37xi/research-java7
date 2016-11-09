package read_write_lock;

public class ScoreHealthThread implements Runnable {
    private ScoreBoard scoreBoard;
    public ScoreHealthThread(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Match Health: " + scoreBoard.getMatchHealth());

            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

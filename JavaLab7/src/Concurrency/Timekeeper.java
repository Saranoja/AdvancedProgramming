/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

public class Timekeeper implements Runnable {
    public volatile boolean stop = false; //guarantees visibility of changes across threads
    private int seconds = 0;
    private int maxSeconds; //maximum time for a game
    private boolean gameOver = false;

    public Timekeeper(int howLong) {
        this.maxSeconds = howLong;
    }

    public void stopGame() {
        this.stop = true;
    }

    public boolean isGameOver(){
        return this.gameOver;
    }

    public int getSeconds(){
        return this.seconds;
    }

    @Override
    public void run() {
        while(seconds<maxSeconds && !gameOver) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
            ++seconds;
        }
        gameOver = true;
    }
}

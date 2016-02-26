package src.game;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Richard
 */
public class GameThread implements Runnable  {

    private final Game game;
    private boolean run = true;
    private final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock(true);
    
    public GameThread(Game game) {
        this.game = game;
    }
    
    public boolean isRunning() {
        rrwl.readLock().lock();
        try {
            return run;
        } finally {
            rrwl.readLock().unlock();
        }
    }
    
    public void setRunning(boolean run) {
        rrwl.writeLock().lock();
        try {
            this.run = run;
        } finally {
            rrwl.writeLock().unlock();
        }
    }

    @Override
    public void run(){
        while (isRunning()) {
            game.updateGame();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

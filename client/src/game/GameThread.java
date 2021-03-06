package src.game;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Is the game thread, this class updates the game in even intervals.
 * @author Richard, ludwigfriborg
 * @version 2016-03-01
 */
public class GameThread implements Runnable  {

    private final Game game;
    private boolean run = true;
    private final ReentrantReadWriteLock rrwl_running_boolean = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_time_long = new ReentrantReadWriteLock(true);
    private long startupTimestampInMillis = 0;
    
    public GameThread(Game game) {
        this.game = game;
    }
    
    public boolean isRunning() {
        rrwl_running_boolean.readLock().lock();
        try {
            return run;
        } finally {
            rrwl_running_boolean.readLock().unlock();
        }
    }
    
    public void setRunning(boolean run) {
        rrwl_running_boolean.writeLock().lock();
        try {
            this.run = run;
        } finally {
            rrwl_running_boolean.writeLock().unlock();
        }
    }

    public int getTimeRunningInHundredthsOfSeconds() {
        rrwl_time_long.readLock().lock();
        try {
            return (int) ((System.currentTimeMillis() - startupTimestampInMillis)/10);
        } finally {
            rrwl_time_long.readLock().unlock();
        }
    }
    
    private void setTimeRunning(long time) {
        rrwl_time_long.writeLock().lock();
        try {
            startupTimestampInMillis = time;
        } finally {
            rrwl_time_long.writeLock().unlock();
        }
    }

    @Override
    public void run(){
        setTimeRunning(System.currentTimeMillis());
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

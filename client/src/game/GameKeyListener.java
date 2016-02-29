package src.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author ludwigfriborg
 *
 * This KeyListner is based on toggles wich ar toggled when a key is pressed
 * and released when the information is retreaved
 */

public class GameKeyListener implements KeyListener {	
    
    private boolean keyPressed_left;
    private boolean keyPressed_right;
    private boolean keyPressed_up;
    private boolean keyPressed_down;
    private boolean keyPressed_space;

    public GameKeyListener() {
        keyPressed_left = false;
        keyPressed_right = false;
        keyPressed_up = false;
        keyPressed_down = false;
        keyPressed_space = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyPressed_left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyPressed_right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyPressed_up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keyPressed_down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keyPressed_space = true;
        }
    }

    /**
     * Detoggles the keystatus
     * @return true if the left key had been pressed
     */
    public boolean getKeyLeft(){
        boolean temp = keyPressed_left;
        keyPressed_left = false;
        return temp;
    }

    /**
     * Detoggles the keystatus
     * @return true if the right key had been pressed
     */
    public boolean getKeyRight(){
        boolean temp = keyPressed_right;
        keyPressed_right = false;
        return temp;
    }

    /**
     * Detoggles the keystatus
     * @return true if the up key had been pressed
     */
    public boolean getKeyUp(){
        boolean temp = keyPressed_up;
        keyPressed_up = false;
        return temp;
    }

    /**
     * Detoggles the keystatus
     * @return true if the down key had been pressed
     */
    public boolean getKeyDown(){
        boolean temp = keyPressed_down;
        keyPressed_down = false;
        return temp;
    }

    /**
     * Detoggles the keystatus
     * @return true if the spacebar had been pressed
     */
    public boolean getKeySpace(){
        boolean temp = keyPressed_space;
        keyPressed_space = false;
        return temp;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
}

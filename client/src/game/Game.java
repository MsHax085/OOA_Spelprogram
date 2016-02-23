package src.game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import src.frame.DefaultFrameState;
import src.game.entities.*;


/**
 * 
 * @author ludwigfriborg
 *
 *	MVC - for the game, not the whole client
 *	Game = Controller
 *	Update = Model
 *	JFrame frame, draw = View
 *
 */

public class Game implements DefaultFrameState, Observer {
    
    private JFrame frame;
    private JPanel superPanel;
    private Draw draw;
    
	private Update update;
	private MultiplayerHandler multiplayerHandler;
	//private Timer timer;
	

    @Override
    public void setup() {
        frame = new JFrame("P�g�ende spel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(superPanel);
    }
	
	/**
	 * @param blockSize
	 */
	public Game(int blockSize, int mapNumber){
        init(blockSize, mapNumber);
	}
	public Game(){
		init(32, 1);
	}
	
	private void init(int blockSize, int mapNumber){
		superPanel = new JPanel();
		update = new Update(mapNumber);
		multiplayerHandler = new MultiplayerHandler(superPanel);
		
		draw = new Draw(update.getList(), blockSize);
        draw.setFocusable(true);
        draw.addKeyListener(new gameListner());
        superPanel.add(draw);

        multiplayerHandler.addPlayer("sweg", mapNumber);
        multiplayerHandler.addPlayer("sweg1", mapNumber);
	}
	
	public MultiplayerHandler getMultiplayerHandler(){
		return multiplayerHandler;
	}

    @Override
    public void view() {
    	frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

    @Override
    public void dispose() {
        // Clear all
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class gameListner implements KeyListener{
    	int i = 0;
		@Override
		public void keyReleased(KeyEvent e) {
			update.doSomeThing(e);
			draw.drawList(update.getList());
	        multiplayerHandler.updatePlayer("sweg1", i, 1);
	        
	        i++;
			
			if(update.isDone()){
				frame.dispose();
				System.out.println("You have won");
				System.exit(0);
			}
		}
	
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

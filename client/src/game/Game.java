package src.game;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import src.frame.DefaultFrameState;


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
    private GameListener gl;
    private GameThread gt;
    
	private Update update;
	private MultiplayerHandler multiplayerHandler;
	//private Timer timer;
	

    @Override
    public void setup() {
        frame = new JFrame("Pågående spel");
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
		gl = new GameListener();
		gt = new GameThread();
		
		draw = new Draw(update.getList(), blockSize);
        draw.setFocusable(true);
        draw.addKeyListener(gl);
        superPanel.add(draw);

        multiplayerHandler.addPlayer("sweg", mapNumber);
        multiplayerHandler.addPlayer("sweg1", mapNumber);
        
        gt.start();
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
    
    private class GameThread extends Thread{
    	private boolean running = true;
    	public void run(){
    		while(running){
    			update.doSomeThing(gl);
    			draw.drawList(update.getList());
    			
    			if(update.isDone()){
    				frame.dispose();
    				System.out.println("You have won");
    				running = false;
    			}
    			try {
					this.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}

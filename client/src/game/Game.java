package src.game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import src.Core;
import src.frame.DefaultFrameState;
import src.gui.UserInterface;
import src.network.NetworkBuffer;


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

public class Game implements WindowListener, DefaultFrameState, Observer {
    
    private JFrame frame;
    private JPanel superPanel;
    private Draw draw;
    private GameListener gl;
    private GameThread gt;
    private TimerTask countTime;
    
	private Update update;
	private MultiplayerHandler multiplayerHandler;
	private int time;
	//private Timer timer;
	

    @Override
    public void setup() {
        frame = new JFrame("Pågående spel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(this);
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

        //Här ska de andra spelarna initieras
        multiplayerHandler.addPlayer("sweg", mapNumber);
        multiplayerHandler.addPlayer("sweg1", mapNumber);
        
        multiplayerHandler.playerIsDone("sweg", 23);
        
        gt.start();
        time = 0;
    	countTime = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				time++;
			}	
    	};
    	new Timer().schedule(countTime, 0, 1000);
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
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        gt.running = false;
        Core.getInstance().setStateObserver(new UserInterface());
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
    
    private class GameThread extends Thread{
    	private boolean running = true;
    	
    	public void run(){
    		while(running){
                        if (NetworkBuffer.getInstance().hasNext()) {
                            NetworkBuffer.getInstance().getNext().handlePacket();
                        }
    			update.doSomeThing(gl);
    			draw.drawList(update.getList(), time);
    			
    			//här ska de andra spelarna uppdateras
    			
    			if(update.isDone()){
    				System.out.println("You have won");
    				running = false;
    				countTime.cancel();
    				
    				//här ska vinnst text visas och paket till andra spelare skickas ut
    				draw.setIsDone();
    				draw.repaint();
                                //Core.getInstance().setStateObserver(new UserInterface());
    			}
    			try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}

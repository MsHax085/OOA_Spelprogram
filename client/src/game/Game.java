package src.game;

import java.util.Observable;
import java.util.Observer;

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

public class Game implements DefaultFrameState, Observer {
    
    private JFrame frame;
    private JPanel superPanel;
    private Draw draw;
    private GameKeyListener gameKeyListener;
    private GameThread gameThread;
    
    private Update update;
    private MultiplayerHandler multiplayerHandler;
	
    @Override
    public void setup() {
        frame = new JFrame("Pågående spel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new GameWindowListener(this));
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

    private void init(int blockSize, int mapNumber) {
        superPanel = new JPanel();
        update = new Update(mapNumber);
        multiplayerHandler = new MultiplayerHandler(superPanel);
        gameKeyListener = new GameKeyListener();
        gameThread = new GameThread(this);

        draw = new Draw(update.getList(), "me", blockSize);
        draw.setFocusable(true);
        draw.addKeyListener(gameKeyListener);
        superPanel.add(draw);

        new Thread(gameThread).start();
    }

    public MultiplayerHandler getMultiplayerHandler(){
        return multiplayerHandler;
    }

    @Override
    public void view() {
    	frame.pack();
        frame.setLocationRelativeTo(null);
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

    /**
     * Updaterar spelet, tar nästa steget update, hämtar upateringar från network samt updaterar sina egna förändringar.
     */
    public void updateGame() {
        if (NetworkBuffer.getInstance().hasNext()) {
            NetworkBuffer.getInstance().getNext().handlePacket();
        }

        //Här ska de andra spelarna initieras
        multiplayerHandler.addPlayer(1, "sweg", 1);
        multiplayerHandler.addPlayer(2, "yolo", 1);

        update.updateMovement(gameKeyListener);
        draw.drawList(update.getList(), gameThread.getTimeRunningInSeconds());

        //här ska de andra spelarna uppdateras förmodligen med egen metod
        multiplayerHandler.updatePlayer(1, 1, 2);
        multiplayerHandler.updateSlab(2, 8, 4);
        multiplayerHandler.playerHasFinished(1, 23);
        multiplayerHandler.playerHasFinished(2, 44);

        if (update.hasFinished()) {
            if(!draw.getHasFinished()){
	        	System.out.println("You are done.");	
	            draw.setHasFinished();
	            if (!multiplayerHandler.getAnyOneHasFinished()) {
	                draw.setIsWinner();
	            }
	            
	            draw.repaint();
            }
            
            //här ska vinnst text visas och paket till andra spelare skickas ut
            //time, IsWinner

            if(multiplayerHandler.hasEveryBodyFinished()){
                stopGame();
                Core.getInstance().setStateObserver(new UserInterface());
            }
        }
    }
    
    public void stopGame() {
        gameThread.setRunning(false);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}

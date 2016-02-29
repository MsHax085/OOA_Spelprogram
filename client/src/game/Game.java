package src.game;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import src.Core;
import src.frame.DefaultFrameState;
import src.gui.UserInterface;
import src.network.Connection;
import src.network.NetworkBuffer;
import src.network.PacketBuilder;


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
    private static Game currentGame;
    private JFrame frame;
    private JPanel superPanel;
    private Draw draw;
    private GameKeyListener gameKeyListener;
    private GameThread gameThread;
    
    private Update update;
    private MultiplayerHandler multiplayerHandler;
    private int mapNumber;
	
    @Override
    public void setup() {
        frame = new JFrame("Pågående spel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new GameWindowListener(this));
        frame.add(superPanel);
    }
	
    /**
     * getCurrentInstance
     * 
     * 
     * 
     * @return current instance
     */
    public static Game getCurrentInstance(){
    	if(currentGame == null){
    		return null;
    	}
    	return currentGame;
    }
    
    /**
     * @param blockSize
     */
    public Game(int blockSize, int mapNumber){
        init(blockSize, mapNumber);
        currentGame = this;
    }

    public Game(){
        init(32, 1);
    }

    private void init(int blockSize, int mapNumber) {
    	this.mapNumber = mapNumber;
        superPanel = new JPanel();
        update = new Update(mapNumber);
        multiplayerHandler = new MultiplayerHandler(superPanel);
        gameKeyListener = new GameKeyListener();
        gameThread = new GameThread(this);

        draw = new Draw(update.getList(), "me", blockSize);
        draw.setFocusable(true);
        draw.addKeyListener(gameKeyListener);
        superPanel.add(draw);

        //some way to add players from lobby
        
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

    public void UpdateMultiplayer(int id, int pX, int pY, int sX, int sY){
        multiplayerHandler.updatePlayer(id, 1, 2);
        multiplayerHandler.updateSlab(id, 8, 4);
    }
    
    public void AddMultiplayers(int id, String name){
    	multiplayerHandler.addPlayer(id, name, mapNumber);
    }
    
    public void SetDoneMultiplayer(int id, int time){
    	multiplayerHandler.playerHasFinished(id, time);
    }
    
    public void ResetMultiplayer(int id, int mapNumber){
    	multiplayerHandler.resetPlayer(id, mapNumber);
    }
    
    /**
     * Updaterar spelet, tar nästa steget update, hämtar upateringar från network samt updaterar sina egna förändringar.
     */
    public void updateGame() {
        if (NetworkBuffer.getInstance().hasNext()) {
            NetworkBuffer.getInstance().getNext().handlePacket();
        }
    	
        update.updateMovement(gameKeyListener);
        draw.drawList(update.getList(), gameThread.getTimeRunningInSeconds());

        if (update.hasFinished()) {
            if(!draw.getHasFinished()){
	        	System.out.println("You are done.");	
	            draw.setHasFinished();
	            if (!multiplayerHandler.getAnyOneHasFinished()) {
	                draw.setIsWinner();
	            }
	            
	            draw.repaint();
            }
            
        	try {
    			Connection.getInstance().sendPacket(
    					PacketBuilder.getInstance().create06PlayerWonPacket(gameThread.getTimeRunningInSeconds()));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
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

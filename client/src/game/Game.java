package src.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import src.Changes;
import src.frame.DefaultFrameState;
import src.network.Connection;
import src.network.NetworkBuffer;
import src.network.PacketBuilder;
import src.resourceManager.client.ServerClient;


/**
 * Game holds and handles the controll over the Game Frame aswell as the different views and a gamethread. This ties the Game-segment together. The class works exactly as a singleton with the exeption that only extern forces can build new Game objects
 *
 * MVC - for the game Package. 
 * Game and MultiplayerHandler = Controller, 
 * Update = Model, 
 * JFrame frame and draw = View
 *
 * @author ludwigfriborg
 * @version 2016-03-01
 * @param mapNumber - desired map
 * @param clientList - a list containing the lobby's ServerClients
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
     * @return current instance if no current instance return null, 
     * some kind of "catch" may be needed for protection againts mean nullpointers
     */
    public static Game getCurrentInstance(){
    	if(currentGame == null){
    	    return null;
    	}
    	return currentGame;
    }
    
    public Game(int mapNumber, ArrayList<ServerClient> clientList){
        currentGame = this;
        
    	this.mapNumber = mapNumber;
        superPanel = new JPanel();
        update = new Update(mapNumber);
        multiplayerHandler = new MultiplayerHandler(superPanel);
        gameKeyListener = new GameKeyListener();
        gameThread = new GameThread(this);

        draw = new Draw(update.getList(), "me", 32);
        draw.setFocusable(true);
        draw.addKeyListener(gameKeyListener);
        superPanel.add(draw);

        for(ServerClient obj : clientList){
            AddMultiplayers(obj.getClientId(), obj.getUsername());
            System.out.println(obj.getUsername() + " Has been added to the game");
        }
        
        new Thread(gameThread).start();
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
        if(!update.hasFinished()){
	    try {
	 	    Connection.getInstance().sendPacket(
			PacketBuilder.getInstance().create0ALeaveLobbyPacket());
		} catch (IOException e) {
		    e.printStackTrace();
		  }
        }
    }

    /**
     * Public method that can be accessed from the networkhandlerclasses.
     * Updates the position of the slap and player for the multiplayer with 
     * the assigned id.
     */
    public void UpdateMultiplayer(int id, int pX, int pY, int sX, int sY){
        if(pX != -1 && pY != -1){
            multiplayerHandler.updatePlayer(id, pX, pY);
        }
        if(sX != -1 && sY != -1){
            multiplayerHandler.updateSlab(id, sX, sY);
        }
    }

    /**
     * Public method that can be accessed from the networkhandlerclasses.
     * Adds a multiplayer with the name and id
     */
    public void AddMultiplayers(int id, String name){
    	multiplayerHandler.addPlayer(id, name, mapNumber);
    }

    /**
     * Public method that can be accessed from the networkhandlerclasses.
     * Sets the multiplayer to done
     */
    public void SetDoneMultiplayer(int id, int time){
    	multiplayerHandler.playerHasFinished(id, time);
    }

    /**
     * Public method that can be accessed from the networkhandlerclasses.
     * Resets the player with the assigned id
     */
    public void ResetMultiplayer(int id){
    	multiplayerHandler.resetPlayer(id, mapNumber);
    }
    
    /**
     * Updates the game, reads packets and checks if the game is game is finished.
     * If the game is finished the thread will be stopped and gamestate will be switched
     */
    public void updateGame() {
        if (NetworkBuffer.getInstance().hasNext()) {
            NetworkBuffer.getInstance().getNext().handlePacket();
        }
    	
        int time = gameThread.getTimeRunningInHundredthsOfSeconds();
        update.updateMovement(gameKeyListener);
        
        if (update.hasFinished()) {
            if(!draw.getHasFinished()){
            	try {
        	    Connection.getInstance().sendPacket(
        		PacketBuilder.getInstance().create06PlayerWonPacket(time));
        	    } catch (IOException e) {
        	          e.printStackTrace();
        	      }
            	
	        	System.out.println("You are done.");	
	            draw.setHasFinished();
	            if (!multiplayerHandler.getAnyOneHasFinished()) {
	            	multiplayerHandler.setAnyOneHasFinished();
	                draw.setIsWinner();
	            }

	        draw.drawList(update.getList(), time);
            }
            
        	
            
            if(multiplayerHandler.hasEveryBodyFinished()){
                stopGame();
            }
        }else{
             draw.drawList(update.getList(), time);
        }
    }
    
    public void stopGame() {
        gameThread.setRunning(false);
    }

    @Override
    public void update(Observable o, Object arg) {
    	if(Changes.CLIENTLIST_CHANGE.getValue()==(int)arg){
    	    multiplayerHandler.refreshList();
    	}
    }
}

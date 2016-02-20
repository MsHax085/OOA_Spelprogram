package src.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.Timer;
import src.frame.DefaultFrameState;
import src.game.entities.Entity;
import src.game.entities.Player;
import src.game.entities.Start;


/**
 * 
 * @author ludwigfriborg
 *
 */
public class Game implements DefaultFrameState, Observer {
    
    private JFrame frame;
    private Draw draw;
    
	private Update update;
	private ArrayList<Entity> list;
	private Player player;
	private boolean gameOn;
	Timer timer;
	

    @Override
    public void setup() {
        frame = new JFrame("P�g�ende spel");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//frame.setSize(new Dimension(500, 500));//atm f�r att tvingaa
        frame.add(draw);
    }
	
	/**
	 * @param blockSize
	 */
	public Game(int blockSize){
        init(blockSize);
	}
	public Game(){
		init(32);
	}
	
	private void init(int blockSize){
		gameOn = true;
		
		update = new Update(list , player, draw);
		list = (new MapHandler(blockSize).getMap(1));
		player = new Player(1, 1);
		list.add(player);
		
		//finds and sets the player at the starting object
		for(Entity ent : list){
			if(ent.getClass() == Start.class){
				player.setX(ent.getX());
				player.setY(ent.getY());
			}
		}
		
		draw = new Draw(list, blockSize, 10, 10);
		draw.setPreferredSize(new Dimension(300, 300));//tvingar rutan till panelens storlek inget perma...
        draw.setFocusable(true);
	}
	
	public ArrayList<Entity> getList(){
		return list;
	}

	/*private void isDone(){
		//Server.sendTotaltime(time); dummy code atm :=)
	}*/

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
}

package src.game;

import java.awt.Dimension;
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
 *MVC - for the game, not the whole client
 *Game = Controller
 *Update = Model
 *JFrame frame, draw = View
 */
public class Game implements DefaultFrameState, Observer {
    
    private JFrame frame;
    private Draw draw;
    
	private Update update;
	private ArrayList<Entity> list;
	//private Timer timer;
	

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
		list = (new MapHandler(blockSize).getMap(1));
		update = new Update(list);
		draw = new Draw(list, blockSize, 10, 10);
		draw.setPreferredSize(new Dimension(320, 320));//tvingar rutan till panelens storlek inget perma...
        draw.setFocusable(true);
        draw.addKeyListener(new gameListner());
	}

	public ArrayList<Entity> getList(){
		return list;
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
		@Override
		public void keyReleased(KeyEvent e) {
			update.doSomeThing(e);
			draw.drawList(update.getList());
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

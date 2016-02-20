package src.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Update implements KeyListener{
	Draw draw;
	ArrayList<Entity> list;
	Player player;
	
	public Update(ArrayList<Entity> list, Player player, Draw draw){
		this.player = player;
		this.list = list;
		
		this.draw = draw;
		//this.draw.addKeyListener(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.setX(player.getX() + 1);
		draw.drawList(list);
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

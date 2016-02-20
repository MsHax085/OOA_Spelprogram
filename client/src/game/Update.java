package src.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Update {
	public ArrayList<Entity> upt(ArrayList<Entity> list, Player player, KeyEvent e){
		//ska hantera massor av skit här i framtiden
		player.setX(player.getX() + 1);
		System.out.println("Supdate");
		return list;
	}
}

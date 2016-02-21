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
public class Update{
	ArrayList<Entity> list;
	boolean isDone;
	Player player;
	
	public Update(ArrayList<Entity> list){
		this.list = list;
		isDone = false;
		
		player = new Player(0,0);
		
		for(Entity ent : list){
			if(ent.getClass() == Start.class){
				player.setX(ent.getX());
				player.setY(ent.getY());
			}
		}
		
		list.add(player);
	}
	
	public void doSomeThing(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			move(-1, 0);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			move(1, 0);
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			move(0, -1);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			move(0, 1);
		}
	}
	
	private void move(int x, int y){
		boolean moveAllowed = true;
		for(Entity ent : list){
			if(intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.isSolid()){
				moveAllowed = false;
			}else if(intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.getClass() == Goal.class){
				isDone = true;
			}
		}
		if(moveAllowed)
			player.move(x, y);
	}
	
	private boolean intersect(int x1, int x2, int y1, int y2){
		if(x1 == x2 && y1 == y2){
			return true;
		}
		return false;
	}
	
	public boolean isDone(){
		return isDone;
	}

	public ArrayList<Entity> getList(){
		return list;
	}
}

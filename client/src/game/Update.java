package src.game;

import java.util.ArrayList;
import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 */

public class Update{
	ArrayList<Entity> entities;
	int mapNumber;
	boolean gameFinished;
	Player player;
	
	public Update(int mapNumber){
		this.mapNumber = mapNumber;
		gameFinished = false;
		
		init();
	}
	
	public void init(){
		entities = new MapHandler().getMap(mapNumber);
		
		player = new Player(0,0);
		
		for(Entity ent : entities){
			if(ent.getClass() == Start.class){
				player.setX(ent.getX());
				player.setY(ent.getY());
			}
		}
		
		entities.add(player);
	}
	
	public void updateMovement(GameListener gl){
		if(gl.getKeyLeft()){
			move(-1, 0);
			System.out.println("sweg");
		}else if(gl.getKeyRight()){
			move(1, 0);
		}else if(gl.getKeyUp()){
			move(0, -1);
		}else if(gl.getKeyDown()){
			move(0, 1);
		}else if(gl.getKeySpace()){
			init();
		}
	}
	
	private void move(int x, int y){
		boolean moveAllowed = true;
		Slab slab = null;
		for(Entity ent : entities){
			if(intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.isSolid()){
				moveAllowed = false;
			}else if(intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.getClass() == Slab.class){
				moveAllowed = isSlabMoveable((Slab)ent, x, y);
				slab = (Slab)ent;
			}
		}
		if(moveAllowed){
			player.move(x, y);
			if(slab != null)
				slab.move(x, y);
		}
	}
	
	private boolean isSlabMoveable(Slab slab, int x, int y){
		for(Entity ent : entities){
			if(intersect(slab.getX() + x, ent.getX(), slab.getY() + y, ent.getY()) && ent.getClass() != Goal.class){
				return false;
			}else if(intersect(slab.getX() + x, ent.getX(), slab.getY() + y, ent.getY()) && ent.getClass() == Goal.class){
				gameFinished = true;
			}
		}
		return true;
	}
	
	private boolean intersect(int x1, int x2, int y1, int y2){
		if(x1 == x2 && y1 == y2){
			return true;
		}
		return false;
	}
	
	public boolean isGameFinished(){
		return gameFinished;
	}

	public ArrayList<Entity> getListOfEntities(){
		return entities;
	}
}

/*
		
*/

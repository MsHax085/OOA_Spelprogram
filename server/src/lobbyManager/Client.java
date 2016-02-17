package src.lobbyManager;
/**
 * 
 * @author ludwigfriborg
 *
 */
public class Client {
	private String name;
	private int id;
	private float gameTime;
	
	public Client(String name, int id) {
		this.name = name;
		this.id = id;
		gameTime = 0;
	}
	
	public float getGameTime(){
		return gameTime;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public void setGameTime(float gameTime){
		this.gameTime = gameTime;
	}
}

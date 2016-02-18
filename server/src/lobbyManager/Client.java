package src.lobbyManager;

import java.net.InetAddress;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Client extends ManagerItem{
	private float gameTime;
	private InetAddress ip;
	private int port;
	
	public Client(String name, int id, InetAddress ip, int port) {
		super(name, id);
		gameTime = 0;
		this.ip = ip;
		this.port = port;
	}
	
	public float getGameTime(){
		return gameTime;
	}
	
	public void setGameTime(float gameTime){
		this.gameTime = gameTime;
	}
	
	public InetAddress getIp(){
		return ip;
	}
	
	public int getPort(){
		return port;
	}
}

package src.lobbyManager;

import java.util.ArrayList;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Lobby {
	private ArrayList<Client> list;
	private Client winner;
	private String name;
	
	private boolean lobbyVacancy;
	private int id;
	private int maxClients;
	
	public Lobby(String name, int maxClients, int id) {
		list = new ArrayList<Client>();
		lobbyVacancy = false;
		winner = null;
		
		this.id = id;
		this.maxClients = maxClients;
		this.name = name;
	}
	
	/*
	 * Adds a client to the lobby list
	 * Parameters	the client you wish to add or 
	 * 				the name and id of the client you wish to add
	 */
	public void add(Client client){
		if(list.size() >= maxClients){
			lobbyVacancy = true;
			System.out.println("["+name+"] Lobby is full.");
		}
		else if(containsById(client.getId()) != null){
			System.out.println("["+name+"] A client with the Id: "+client.getId()+" has allready joined.");
		}
		else{
			list.add(client);
			System.out.println("["+name+"] "+client.getName()+" joined lobby.");
		}
	}
	public void add(String name, int id){
		if(list.size() >= maxClients){
			lobbyVacancy = true;
			System.out.println("["+this.name+"] Lobby is full.");
		}
		else if(containsById(id) != null){
			System.out.println("["+this.name+"] A client with the Id: "+id+" has allready joined.");
		}
		else{
			list.add(new Client(name, id));
			System.out.println("["+this.name+"] "+name+" joined lobby.");
		}
	}
	
	/*
	 * Removes a client from the lobby list
	 * Parameters	the client you wish to remove or
	 * 				the id of the client you wish to remove
	 */
	public void remove(Client client){
		if(list.contains(client)){
			list.remove(client);
			System.out.println("["+name+"] "+client.getName()+" left lobby.");
		}else{
			System.out.println("["+name+"] "+client.getName()+" is not a member of the lobby.");
		}
	}
	public void remove(int id){
		Client client = containsById(id);
		if(client != null){
			remove(client);
		}else{
			System.out.println("["+name+"] No client with the id of "+id+" is a member of the lobby.");
		}
	}
	
	public ArrayList<Client> getClientList(){
		return list;
	}
	
	public boolean isLobbyFull(){
		return lobbyVacancy;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	/*
	 * Returns the client if it exists
	 */
	public Client containsById(int id){
		for(Client c : list){
			if(c.getId() == id){
				return c;
			}
		}
		return null;
	}
	
	/*
	 * Gets the winner, if no winner: return null.
	 */
	public Client getWinner(){
		return winner;
	}
	
	public void setWinner(Client client){
		winner = client;
	}
	
	/*
	 * Returns a string with the lobby and its members(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String temp = "\n  =="+name+"====";
		for(Client c : list){
			temp = temp + "\n  -" +c.getName()+" id: "+c.getId()+ " time: "+c.getGameTime();
		}
		temp = temp + "\n";
		return temp;
	}

}
